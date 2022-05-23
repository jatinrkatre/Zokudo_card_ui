package com.ui.product.zokudo.services.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.ui.product.zokudo.dto.response.ProgramDto;
import com.ui.product.zokudo.enums.BizErrors;
import com.ui.product.zokudo.exceptions.BizException;
import com.ui.product.zokudo.services.inf.ProgramServiceInf;
import com.ui.product.zokudo.util.CommonUtil;
import com.ui.product.zokudo.util.Constants;
import com.ui.product.zokudo.util.UrlMetaData;

@Service
public class ProgramServiceImpl implements ProgramServiceInf {

	private final String applicationLevelUserName;
	private final String applicationLevelUserPassword;
	private final UrlMetaData urlMetaData;
	private final Client client;

	public ProgramServiceImpl(UrlMetaData urlMetaData, @Qualifier(value = "client") Client client,
			@Value("${applicationLevel.user.name}") String applicationLevelUserName,
			@Value("${applicationLevel.user.password}") String applicationLevelUserPassword) {

		this.urlMetaData = urlMetaData;
		this.client = client;
		this.applicationLevelUserName = applicationLevelUserName;
		this.applicationLevelUserPassword = applicationLevelUserPassword;

	}

	@Override
	public ProgramDto authenticateCustomProgram(String programUrl) {
		// TODO Auto-generated method stub

		try {
			final MultivaluedMap<String, Object> headerMap = new MultivaluedHashMap<>();
			headerMap.add("Authorization",
					CommonUtil.getBasicAuthorization(applicationLevelUserName, applicationLevelUserPassword));
			headerMap.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			String str = urlMetaData.PROGRAM_DETAILS.replaceAll(Constants.urlEscapeConstant, programUrl);
			Response clientResponse = client.target(str).request().headers(headerMap).get();
			String programDetails = clientResponse.readEntity(String.class);
			if (clientResponse.getStatus() == 200 && !StringUtils.isBlank(programDetails)) {
				try {
					ProgramDto programDto = new ProgramDto();
					JSONObject jsonObject = new JSONObject(programDetails);
					programDto.setProgramUrl(jsonObject.getString("hostUrl"));
					programDto.setProgramHashId(jsonObject.getString("programHashId"));
					programDto.setProgramLogo(jsonObject.getString("programLogo"));
					return programDto;
				} catch (JSONException e) {
					System.err.println(e.getMessage());
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
