package com.ui.product.zokudo.controller;

import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ui.product.zokudo.util.AESEncryption;
import com.ui.product.zokudo.util.CommonUtil;
import com.ui.product.zokudo.util.Constants;
import com.ui.product.zokudo.util.UrlMetaData;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/auth")
public class AuthenticationController {

	private final UrlMetaData urlMetaData;

	public AuthenticationController(final UrlMetaData urlMetaData) {
		this.urlMetaData = urlMetaData;
	}

	@GetMapping(value = { "/login" })
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) {

		String programUrl = (String) request.getSession().getAttribute("program_url");
//		log.info("Program URL : {}", programUrl );
		if (programUrl == null || "".equals(programUrl))
			return "redirect:https://admin.zokudo.com/zokudo-ui/mss/auth/program";
		return "login";
	}

	@GetMapping(value = "/index")
	public String getlandingPage(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		String programUrl = (String) request.getSession().getAttribute("program_url");
		model.addAttribute("cardCountUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DASHBOARD_COUNT));
		model.addAttribute("transactionGraphUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_TRANSACTION_GRAPH_DATA));
		model.addAttribute("cardGraphUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARDS_GRAPH_DATA));
		model.addAttribute("customerGraphUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CUSTOMERS_GRAPH_DATA));
		return "index";
	}

	@GetMapping(value = "/error")
	public String getErrorPage(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model) {
		return "errorPage";
	}

	@GetMapping(value = "/logout")
	public void authProgram(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Stream.of(request.getCookies()).forEach((eachCookie) -> {
			eachCookie.setMaxAge(0);
		});

		String program_url = (String) request.getSession().getAttribute("program_url");
		String programLogo = (String) request.getSession().getAttribute("programLogo");

		request.logout();
		request.getSession().invalidate();

		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		SecurityContextHolder.clearContext();

		HttpSession session = request.getSession(true);

		request.getSession().setAttribute("programLogo", programLogo);
		session.setAttribute("program_url", program_url);
//		log.info("program_url :{}", program_url);
//		log.info("sendOtpUrl :{}", urlMetaData.SEND_OTP.replaceAll(Constants.urlEscapeConstant, program_url));
		session.setAttribute("sendOtpUrl", urlMetaData.SEND_OTP.replaceAll(Constants.urlEscapeConstant, program_url));
		session.setAttribute("forgotPasswordUrl",
				urlMetaData.FORGET_PASSWORD.replaceAll(Constants.urlEscapeConstant, program_url));
		session.setAttribute("customLoginUrl",
				urlMetaData.CUSTOM_LOGIN_URL.replaceAll(Constants.urlEscapeConstant, program_url));
		session.setAttribute("updatePasswordUrl",
				urlMetaData.UPDATE_PASSWORD.replaceAll(Constants.urlEscapeConstant, program_url));
		response.sendRedirect(request.getContextPath() + "/auth/login?success=true");

	}

	@GetMapping(value = "/resetPassword")
	public String resetPassword(@RequestParam String username, HttpServletRequest request, HttpServletResponse resposne,
			Model model) {
		model.addAttribute("username", username);
		// model.addAttribute("tokenCheckUrl",
		// urlMetaData.PROGRAM_CHANGE_PASSWORD_TOKEN_STATUS);
		// model.addAttribute("resetPasswordUrl",urlMetaData.PROGRAM_RESET_PASSWORD);
		return "resetPassword";
	}
}
