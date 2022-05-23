package com.ui.product.zokudo.controller;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ui.product.zokudo.dto.response.ProgramDto;
import com.ui.product.zokudo.services.inf.ProgramServiceInf;
import com.ui.product.zokudo.util.Constants;
import com.ui.product.zokudo.util.UrlMetaData;

@Controller
@RequestMapping("/{programUrl}/auth")
public class CustomAuthController {

	@Autowired
	ProgramServiceInf programInf;

	@Autowired
	UrlMetaData urlMetaData;

	@GetMapping(value = "/program")
	public String authProgram(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session, Model model, @PathVariable("programUrl") String programUrl,
			RedirectAttributes redirectAttributes, @QueryParam("logout") String logout, @QueryParam("url") String url) {

		System.err.println("url:" + request.getRequestURL());
		System.err.println("remot:" + request.getRemotePort());

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String s = headerNames.nextElement();
			System.err.println("Heade name is -> " + s + ", value is -> " + request.getHeader(s));
		}

		if ("true".equals(logout)) {
			redirectAttributes.addFlashAttribute("logout", true);
			programUrl = url;
		}

		Locale.setDefault(Locale.ENGLISH);

		ProgramDto programDto = programInf.authenticateCustomProgram(programUrl.toLowerCase());

		if (programDto == null)
			return "redirect:https://admin.zokudo.com/zokudo-ui/mss/auth/program";

		request.getSession().setAttribute("program_url", programUrl);
		redirectAttributes.addFlashAttribute("programHashId", programDto.getProgramHashId());
		redirectAttributes.addFlashAttribute("progarmName", programDto.getProgramName());

		request.getSession().setAttribute("sendOtpUrl",
				urlMetaData.SEND_OTP.replaceAll(Constants.urlEscapeConstant, programUrl));
		request.getSession().setAttribute("customLoginUrl",
				urlMetaData.CUSTOM_LOGIN_URL.replaceAll(Constants.urlEscapeConstant, programUrl));
		request.getSession().setAttribute("forgotPasswordUrl",
				urlMetaData.FORGET_PASSWORD.replaceAll(Constants.urlEscapeConstant, programUrl));
		request.getSession().setAttribute("updatePasswordUrl",
				urlMetaData.UPDATE_PASSWORD.replaceAll(Constants.urlEscapeConstant, programUrl));

		request.getSession().setAttribute("programLogo", programDto.getProgramLogo());

		return "redirect:/auth/login";
	}

	@GetMapping(value = "/programLogout")
	public String authProgram(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model, @PathVariable("programUrl") String programUrl, RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("programUrl", programUrl);

		return "redirect:/auth/login";

	}

}
