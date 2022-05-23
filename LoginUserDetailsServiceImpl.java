
package com.ui.product.zokudo.services.impl;

import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ui.product.zokudo.enums.BizErrors;
import com.ui.product.zokudo.exceptions.BizException;
import com.ui.product.zokudo.util.AESDecryption;
import com.ui.product.zokudo.util.AESEncryption;
import com.ui.product.zokudo.util.CommonUtil;
import com.ui.product.zokudo.util.Constants;
import com.ui.product.zokudo.util.SecurityUtil;
import com.ui.product.zokudo.util.UrlMetaData;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "LoginUserDetailsServiceImpl")
public class LoginUserDetailsServiceImpl implements UserDetailsService {

	private final UserDetailsImpl userDetailsImpl;
	private final HttpServletRequest request;
	private final UrlMetaData urlMetaData;
	private final Client client;
	private final SecurityUtil securityUtil;
	private final String applicationLevelUserName;
    private final String applicationLevelUserPassword;
    private final String secretKey;

	@Autowired
	public LoginUserDetailsServiceImpl(final UserDetailsImpl userDetailsImpl, final HttpServletRequest request,
			final UrlMetaData urlMetaData, final Client client, SecurityUtil securityUtil ,
			 @Value("${applicationLevel.user.name}") final String applicationLevelUserName,
             @Value("${applicationLevel.user.password}") final String applicationLevelUserPassword,
             @Value("${spring.security.user.password}") final String secretKey) {
		this.userDetailsImpl = userDetailsImpl;
		this.request = request;
		this.urlMetaData = urlMetaData;
		this.client = client;
		this.securityUtil = securityUtil;
		this.applicationLevelUserName = applicationLevelUserName;
		this.applicationLevelUserPassword = applicationLevelUserPassword;
		this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	@Override
	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
		try {

			String url = request.getParameter("programUrl");

			String password = AESEncryption.getRsaDecryptedData(request.getParameter("loginPassword"));

			String str = urlMetaData.USER_BY_USERNAME.replaceAll(Constants.urlEscapeConstant, url);

			final Response response = client.target(str).request().header("username", userName)
					.header("program_url", url).get();

			JSONObject responseJson = new JSONObject(response.readEntity(String.class));
			//JSONObject responseJson = responseUserDetailsJson.getJSONObject("users");
//			log.info("Response JSON - " + responseJson);
			if (response.getStatus() == 200) {
				String roles = "";
				userDetailsImpl.setUserName(responseJson.getString("username"));
				userDetailsImpl.setPassword(responseJson.getString("password"));
				
				String jwtToken = getJwtToken(responseJson.getString("username"),password,url);
				Date expiryTime = getExpiryTime(jwtToken);
				long expiryTimeInSeconds = expiryTime.getTime();
				
				request.getSession().setAttribute("jwt_expiry_time", expiryTimeInSeconds);		
				JSONArray userRolesArray = responseJson.getJSONArray("userRoles");
			//	log.info("userRolesArray - " + userRolesArray);
				request.getSession().setAttribute("role_name", userRolesArray.getJSONObject(0).getString("name"));
				request.getSession().setAttribute("category", userRolesArray.getJSONObject(0).getString("category"));
				request.getSession().setAttribute("user_hash_id",responseJson.get("userHashId"));
				request.getSession().setAttribute("program_url", url);
				request.getSession().setAttribute("user_name", responseJson.getString("username"));
				request.getSession().setAttribute("loggedInUserHashId", responseJson.getString("userHashId"));
				request.getSession().setAttribute("productType", responseJson.get("productType"));
				request.getSession().setAttribute("productCode", responseJson.get("productCode"));
				request.getSession().setAttribute("channelId", responseJson.get("channelId"));
				request.getSession().setAttribute("auth_header", jwtToken);
				//request.getSession().setAttribute("auth_header", securityUtil.getAuthorizationHeader(applicationLevelUserName, applicationLevelUserPassword));
				if(userRolesArray.getJSONObject(0).getString("name").equals("ROLE_ADMIN")) {
					request.getSession().setAttribute("first_name", "Admin");
				} else {
					request.getSession().setAttribute("first_name", responseJson.getString("fullName"));
				}
				

				JSONObject programDetails = getProgramDetails(url);
				if (programDetails != null) {
					request.getSession().setAttribute("card_image", programDetails.getString("cardImageUrl"));
					request.getSession().setAttribute("program_id", programDetails.getString("id"));
					request.getSession().setAttribute("program_name", programDetails.get("programName"));
					request.getSession().setAttribute("program_hashId", programDetails.get("programHashId"));
					request.getSession().setAttribute("business_type", programDetails.getJSONObject("corporateProcessor").get("code"));
					request.getSession().setAttribute("client_id", programDetails.get("clientId"));
					request.getSession().setAttribute("program_plan", programDetails.get("programPlan"));
				}

				for (int i = 0; i < userRolesArray.length(); i++) {
					JSONObject userRolesEachElementObject = userRolesArray.getJSONObject(i);
					JSONArray privelegesArray = userRolesEachElementObject.getJSONArray("privileges");
					if (privelegesArray != null && privelegesArray.length() > 0) {
						for (int j = 0; j < privelegesArray.length(); j++) {
							JSONObject privelegesArrayEachElementObject = privelegesArray.getJSONObject(j);
							roles += privelegesArrayEachElementObject.getString("name") + ",";
						}
					}
				}

				if (!"".equals(roles)) {
					roles = roles.substring(0, (roles.length()) - 1);
				}
				
				request.getSession().setAttribute("roles", roles);
				
				 if (responseJson.getString("productType").toLowerCase().contains("client admin")) {
	                	request.getSession().setAttribute("clientCode", responseJson.getString("productCode"));
	                }
				
				userDetailsImpl.setAuthorities(roles);
			} else {
				throw new UsernameNotFoundException(responseJson.getString("message"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return userDetailsImpl;
	}



	public JSONObject getProgramDetails(String programUrl) {
		try {
			final MultivaluedMap<String, Object> headerMap = new MultivaluedHashMap<>();
			headerMap.add("Authorization", securityUtil.getAuthorizationHeader(applicationLevelUserName, applicationLevelUserPassword));
			headerMap.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			String str = urlMetaData.PROGRAM_DETAILS.replaceAll(Constants.urlEscapeConstant, programUrl);
			Response clientResponse = client.target(str).request().headers(headerMap).get();
			String programDetails = clientResponse.readEntity(String.class);
			return new JSONObject(programDetails);
		} catch (Exception e) {
			return null;
		}
	}
	
	private String getJwtToken(String username, String password,String programURL) throws JSONException {
		//log.info(" Get JWT Token for username : {} ",username);
		String authHeader = securityUtil.getAuthorizationHeader(username, password);
		
		String createJwtTokenURL = urlMetaData.GET_JWT_TOKEN;
		
		final Response response = client.target(createJwtTokenURL).request().header("Authorization", authHeader)
				.header("programUrl", programURL)
				.header("Content-Type", "application/json")
				.get();
		
		JSONObject responseJson = new JSONObject(response.readEntity(String.class));
		
		if(response.getStatus() == 200) {
			//log.info("JWT Token found. ");
			String jwtToken = responseJson.getString("authorizationToken");
			return jwtToken;
		}else {
			throw new UsernameNotFoundException(responseJson.getString("message"));
		}
	}
	
	private Date getExpiryTime(String token) {
		if(StringUtils.isEmpty(token)) {
			throw new BizException("JWT token is empty. ");
		}else {
			String jwtToken = token.replaceAll("Bearer ","");
			return extractExpiration(jwtToken);
		}
	}
	
	public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
	
	public <T> T extractClaim(String token , Function<Claims, T> claimResolver) {
        final Claims claim= extractAllClaims(token);
        return claimResolver.apply(claim);
    }
	
	private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

}
