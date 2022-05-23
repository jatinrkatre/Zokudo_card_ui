package com.ui.product.zokudo.util;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import com.ui.product.zokudo.enums.BizErrors;
import com.ui.product.zokudo.exceptions.BizException;

public class CommonUtil {

	public static String[] getProgramAndRequestUrl(HttpServletRequest request) {
		return request.getRequestURI().split("/");
	}

	public static String getBasicAuthorization(String applicationLevelUserName, String applicationLevelUserPassword) {
		String result = "Basic ";
		String credentials = applicationLevelUserName + ":" + applicationLevelUserPassword;
		result = result + DatatypeConverter.printBase64Binary(credentials.getBytes(StandardCharsets.UTF_8));
		return result;
	}

	public static String convertToEmptyIfNull(String input) {
		if (input == null) {
			return "";
		} else {
			return input;
		}
	}

	public static String getString(String description) {
		if (description != null) {
			return description.replaceAll("\\W", " ");
		}
		return null;
	}

	public static boolean isValidMobileNumber(String str) {
		try {
			return str.trim().matches("^(?=(?:[6-9]){1})(?=[0-9]{10}).*");
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isValidTitle(String str) {
		try {
			switch (str) {
			case "Mr":
			case "Ms":
			case "Mrs":
				return true;
			}
		} catch (Exception e) {
			throw new BizException(BizErrors.NULL_ERROR.getValue(), "title should not be empty");
		}
		return false;
	}

	public static boolean isValidGender(String str) {
		try {
			switch (str) {
			case "M":
			case "F":
				return true;
			}
		} catch (Exception e) {
			throw new BizException(BizErrors.NULL_ERROR.getValue(), "Gender should not be empty");
		}
		return false;
	}

	public static boolean validIdType(String idType) {
		switch (idType) {
		case "aadhaar":
		case "pan":
		case "driver_id":
			return true;
		}
		return false;
	}

	public static boolean validCountry(String countryOfIssue) {
		return "India".equals(countryOfIssue);
	}

	public static String getUrlWithProgram(String programUrl, String url) {
		System.out.println("url"+url);
		url = url.replaceAll(Constants.urlEscapeConstant, programUrl);
		return url;
	}

}
