package com.ui.product.zokudo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ui.product.zokudo.util.AESEncryption;
import com.ui.product.zokudo.util.CommonUtil;
import com.ui.product.zokudo.util.Constants;
import com.ui.product.zokudo.util.UrlMetaData;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class MainController implements ErrorController {

	@Autowired
	private UrlMetaData urlMetaData;
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.GET, value = "{page}")
	public String getPage(@PathVariable(value = "page") final String page, final Model model,
			HttpServletRequest request, HttpSession session) {
		String programUrl = "";
		try {
			programUrl = (String) request.getSession().getAttribute("program_url");
		} catch (NullPointerException ee) {
			logger.error(ee.getMessage());
			return "redirect:https://admin.zokudo.com/zokudo-ui/mss/auth/program";
		}
		Locale.setDefault(Locale.ENGLISH);
		logger.info("Page - " + page.toLowerCase());
		String role=session.getAttribute("roles").toString();
		switch (page.toLowerCase()) {
		case "index":
			model.addAttribute("cardCountUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DASHBOARD_COUNT));
			model.addAttribute("transactionGraphUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_TRANSACTION_GRAPH_DATA));
			model.addAttribute("cardGraphUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARDS_GRAPH_DATA));
			model.addAttribute("customerGraphUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CUSTOMERS_GRAPH_DATA));
			break;
		case "cardlist":
			if(!role.contains("ROLE_CARD_LIST")) {
				log.info("User has no permission to access cardlist page");
				return "404";
			}
			model.addAttribute("cardListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CARD_LIST));
			model.addAttribute("blockCardUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BLOCK_CARD));
			// String balanceUrl = urlMetaData.FETCH_CARD_BALANCE;
			// balanceUrl = balanceUrl.replaceAll("/"+Constants.urlEscapeConstant, "");

			model.addAttribute("fetchBalanceUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_CARD_BALANCE));
			model.addAttribute("getCvvUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CVV));
			model.addAttribute("setPinUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.SET_PIN));
			model.addAttribute("getCardDetails",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARD_DETAILS));
			model.addAttribute("cardListBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARD_BASED_ON_FILTER));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("cardListDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_CARD_LIST));
			model.addAttribute("fetchAllCustomerUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_ALL_CUSTOMERS));
			break;
		case "customerlist":
			if(!role.contains("ROLE_FETCH_CUSTOMER_BY_PROGRAM")) {
				log.info("User has no permission to access customerlist page");
				return "404";
			}
			model.addAttribute("customerListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CUSTOMER_LIST));
			model.addAttribute("blockUnblockCustomer",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BLOCK_UNBLOCK_CUSTOMER));
			model.addAttribute("customerListBasedOnFilterUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CUSTOMER_BASED_ON_FILTER));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("downloadListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_LIST_URL));
			model.addAttribute("fetchWalletByCustUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_WALLET_BY_CUST_ID));
			model.addAttribute("updateKycUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_KYC));
			break;
		case "kyclist":
			if(!role.contains("ROLE_FETCH_CUSTOMER_BY_PROGRAM")) {
				log.info("User has no permission to access kyclist page");
				return "404";
			}
			model.addAttribute("customerListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CUSTOMER_LIST));
			model.addAttribute("kycListDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_KYC_LIST));
			model.addAttribute("kycListBasedOnFilterUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_KYC_LIST_FILTERED));
			break;

		case "prefundrequestlist":
			if(!role.contains("ROLE_FETCH_PREFUND_TRANSACTION")) {
				log.info("User has no permission to access prefundrequestlist page");
				return "404";
			}
			model.addAttribute("prefundListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PREFUND_LIST));
			model.addAttribute("acceptOrRejectUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ACCEPT_REJECT_PREFUND_REQUEST));
			model.addAttribute("prefundRequestListBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PREFUND_REQUEST_BASED_ON_FILTER));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("prefundDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_PREFUND_LIST));
			break;

		case "prefundhistorylist":
			if(!role.contains("ROLE_FETCH_PREFUND_HISTORY")) {
				log.info("User has no permission to access prefundhistorylist page");
				return "404";
			}
			model.addAttribute("prefundListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PREFUND_LIST));
			model.addAttribute("prefundRequestListBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PREFUND_REQUEST_BASED_ON_FILTER));
			model.addAttribute("prefundDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_PREFUND_LIST));
			break;

		case "transactionlist":
			if(!role.contains("TRANSACTION_REPORT")) {
				log.info("User has no permission to access transactionlist page");
				return "404";
			}
			model.addAttribute("transactionListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_TRANSACTION));
			model.addAttribute("transactionListBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_TRANSACTION));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_TRANSACTION_REPORT));
			model.addAttribute("getClientListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_CLIENT_LIST));
			model.addAttribute("listRetailerUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_RETAILER_LIST));
			model.addAttribute("getDistributorListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DIST_LIST));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_GEN_TRAN_REPORT));
			break;
		case "prefundrequest":
			if(!role.contains("ROLE_PREFUND_REQUSET")) {
				log.info("User has no permission to access prefundrequest page");
				return "404";
			}
			model.addAttribute("prefundRequestUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_PREFUND_REQ));
			model.addAttribute("agentPrefundRequestUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_AGENT_PREFUND_REQ));
			break;

		case "changepassword":
			//no role
			model.addAttribute("secreteKey", AESEncryption.passEncoderKey);
			model.addAttribute("changePasswordUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CHANGE_PASSWORD));
			model.addAttribute("changePasswordOtpUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FORGET_PASSWORD));
			break;
		case "onboardclient":
			if(!role.contains("ROLE_ONBOARD_CLIENT")) {
				log.info("User has no permission to access onboardclient page");
				return "404";
			}
			model.addAttribute("onboardclientUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ON_BOARD_CLIENT));
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("getCorporateProcessors",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_CORPORATE_PROCESSOR));
			model.addAttribute("commercialUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.COMMERCIAL));
			model.addAttribute("onboardprogramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ONBORAD_PROGRAM));
			model.addAttribute("sendOtpUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CLIENT_OTP_VERIFICATION));
			break;

		case "clientadmin":
//			no option sidebar and no role
			model.addAttribute("getClientListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_CLIENT_LIST));
			break;
		case "getclientlist":
			if(!role.contains("ROLE_GET_CLIENT_LIST")) {
				log.info("User has no permission to access this page");
				return "404";
			}
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("blockUnblockClient",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BLOCK_UNBLOCK_CLIENT));
			model.addAttribute("clientListDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_CLIENT_LIST));
			model.addAttribute("clientBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BASED_ON_FILTER));
			break;
		case "userlist":
			if(!role.contains("ROLE_GET_USERS")) {
				log.info("User has no permission to access userlist page");
				return "404";
			}
			model.addAttribute("getUsersList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_USERS_LIST));
			model.addAttribute("blockUnblockUsers",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BLOCK_UNBLOCK_USERS));
			model.addAttribute("getFilterUsersListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_FILTER_USERS_LIST));
			break;
		case "onboardprogram":
			if(!role.contains("ROLE_ONBOARD_PROGRAM")) {
				log.info("User has no permission to access onboardprogram page");
				return "404";
			}
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("onboardprogramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ONBORAD_PROGRAM));
			model.addAttribute("getCorporateProcessors",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_CORPORATE_PROCESSOR));
			model.addAttribute("getBinNumber", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_BIN_NUMBER));
			model.addAttribute("getSubBinNumber",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_SUB_BIN_NUMBER));
			model.addAttribute("checkProgramForClientUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CHECK_PROG_FOR_CLIENT));
			break;

		case "bulkuploadkit":
			if(!role.contains("ROLE_BULK_KIT_UPLOAD")) {
				log.info("User has no permission to access bulkuploadkit page");
				return "404";
			}
			model.addAttribute("bulkUploadKitUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_UPLOAD_KIT));
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("getCardByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARD_TYPE_BY_PROGRAM));
			model.addAttribute("getInvKitByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KIT_BY_PROGRAM));
			model.addAttribute("clientByClientHashUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BY_CLIENT_HASH));
			model.addAttribute("distributorsByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DISTLIST_BY_PROGRAM_HASH));
			model.addAttribute("getAssignedkitByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KITS_BY_PROGRAM));
			model.addAttribute("clientByClientIdUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BY_ID));
			model.addAttribute("getDistByUserHashUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DIST_BY_USER_HASH));
			model.addAttribute("getAgentListByDistHashIdURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_AGENT_BY_DIST_HASH));
			model.addAttribute("getKitByDistributorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KITS_BY_DISTRIBUTOR));
			break;

		case "addcorporateprocessor":
			if(!role.contains("ROLE_ADD_CORPORATE_PROCESSOR")) {
				log.info("User has no permission to access addcorporateprocessor page");
				return "404";
			}
			model.addAttribute("addCorporateProcessorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ADD_CORPORATE_PROCESSOR));
			model.addAttribute("fetchProcessors",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_PROCESSORS));
			break;

		case "programlist":
			if(!role.contains("ROLE_LIST_PROGRAM")) {
				log.info("User has no permission to access programlist page");
				return "404";
			}
			model.addAttribute("programlistUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("blockUnblockProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BLOCK_UNBLOCK_PROGRAM));
			model.addAttribute("programListDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_PROGRAM_LIST));
			model.addAttribute("ProgramListWithFiltersUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST_WITH_FILTERS));
			model.addAttribute("hostAddUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_HOST_URL));
			model.addAttribute("getCorporateProcessorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CORPORATE_PROCESSOR_LIST));
			model.addAttribute("fetchAllClientUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("getSubBinByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_SUB_BIN_BY_PROGRAMID));
			break;

		case "fetchtransaction":
//			 no side bar and no role
			String customerHashId = request.getParameter("customer");
			String cardHashId = request.getParameter("card");
			model.addAttribute("fetchTransactionUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_FETCH_TRANSACTION));
			model.addAttribute("customerHashId", customerHashId);
			model.addAttribute("cardHashId", cardHashId);
			break;
		case "getactivitylogs":
			if(!role.contains("ROLE_ACTIVITY_LOGS")) {
				log.info("User has no permission to access getactivitylogs page");
				return "404";
			}
			model.addAttribute("activityLogsUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_ACTIVITY_LOGS));
			break;
		case "getcommerciallist":
			if(!role.contains("ROLE_GET_COMMERCIAL_LIST")) {
				log.info("User has no permission to access getcommerciallist page");
				return "404";
			}
			model.addAttribute("getcommercialURl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_COMMERCIAL_LIST));
			model.addAttribute("fetchAllClientUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			break;
		case "editclient":
//			no side bar and no role
			String email = request.getParameter("email");
			String userHashId = request.getParameter("userHashId");
			model.addAttribute("editClientUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.EDIT_CLIENT_DETAILS));
			model.addAttribute("getClientByIdUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BY_ID));
			model.addAttribute("clientDetails", email);
			model.addAttribute("userHashId", userHashId);
			break;

		case "editcommercial":
//			no side bar no role
			String commercialHashId = request.getParameter("hashId");
			model.addAttribute("editCommercialUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.EDIT_COMMERCIAL));
			model.addAttribute("getCommercialByHashId",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_COMMERCIAL));
			model.addAttribute("commercialHashId", commercialHashId);
			break;

		case "editprogram":
//			no side bar no role
			String programHashId = request.getParameter("programHashId");
			model.addAttribute("editProgramUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.EDIT_PROGRAM));
			model.addAttribute("getProgramByHashIdUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_BY_PROGRAMHASHID));
			model.addAttribute("programHashId", programHashId);
			break;

		case "getcorporateprocessor":
			if(!role.contains("ROLE_LIST_CORPORATE_PROCESSOR")) {
				log.info("User has no permission to access getcorporateprocessor page");
				return "404";
			}
			model.addAttribute("getCorporateProcessorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CORPORATE_PROCESSOR_LIST));
			model.addAttribute("getFilteredCorporateProcessorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_FILTERED_CORPORATE_PROCESSOR_LIST));
			break;

		case "clientkitnumbers":
			if(!role.contains("ROLE_CLIENT_KIT_NUMBERS")) {
				log.info("User has no permission to access clientkitnumbers page");
				return "404";
			}
			model.addAttribute("clientKitNumbers",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CLIENT_KIT_NUMBERS));
			model.addAttribute("cardListBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_KIT_BY_FILTER));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("getDistributorListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DIST_LIST));
			model.addAttribute("getDistByUserHashUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DIST_BY_USER_HASH));
			model.addAttribute("getAgentListByDistHashIdURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_AGENT_BY_DIST_HASH));
			model.addAttribute("getRetailerDetails",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_AGENT_BY_DIST_HASH));
			model.addAttribute("distributorsByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DISTLIST_BY_PROGRAM_HASH));
			model.addAttribute("clientKiListDownload",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_ASSIGNED_KIT_LIST));
			break;
		case "bulkupload":
			if(!role.contains("ROLE_BULK_REGISTER") || !role.contains("ROLE_BULK_CARD_LOAD") || !role.contains("ROLE_BULK_CARD_CREATE")) {
				log.info("User has no permission to access bulkupload page");
				return "404";
			}
			model.addAttribute("bulkRegister", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_REGISTER));
			model.addAttribute("bulkCardLoad", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_CARD_LOAD));
			model.addAttribute("bulkCardCreate",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_CARD_CREATE));

			model.addAttribute("processBulkRegister",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROCESS_BULK_REGISTER));
			model.addAttribute("processBulkCardCreation",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROCESS_BULK_CARD_CREATION));
			model.addAttribute("processBulkCardLoad",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROCESS_BULK_CARD_LOAD));
			model.addAttribute("fetchClientIdUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_ID));

		case "customerbulkreport":
			if(!role.contains("ROLE_BULK_REGISTER") || !role.contains("ROLE_BULK_CARD_LOAD") || !role.contains("ROLE_BULK_CARD_CREATE")) {
				log.info("User has no permission to access customerbulkreport page");
				return "404";
			}
		    model.addAttribute("bulkReport", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_REPORT));
			model.addAttribute("bulkReportOfflineUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_BULK_CUSTOMER_REPORT));
			break;
		case "bulkcardreport":
			if(!role.contains("ROLE_BULK_REGISTER") || !role.contains("ROLE_BULK_CARD_LOAD") || !role.contains("ROLE_BULK_CARD_CREATE")) {
				log.info("User has no permission to access bulkcardreport page");
				return "404";
			}
		    model.addAttribute("bulkCardReport",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_CARD_REPORT));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("bulkReportOfflineUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_BULK_CARD_CREATE_REPORT));
			break;
		case "bulkuploadreport":
			if(!role.contains("ROLE_BULK_REGISTER") || !role.contains("ROLE_BULK_CARD_LOAD") || !role.contains("ROLE_BULK_CARD_CREATE")) {
				log.info("User has no permission to access bulkuploadreport page");
				return "404";
			}
		    model.addAttribute("bulkUploadReport",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_UPLOAD_REPORT));
			model.addAttribute("bulkDownloadReport",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_DOWNLOAD_REPORT));
			break;
		case "cardissuance":
			if(!role.contains("ROLE_CREATE_CARD")) {
				log.info("User has no permission to access cardissuance page");
				return "404";
			}
			model.addAttribute("customerListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CUSTOMER_LIST));
			model.addAttribute("createCardUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CREATE_CARD));
			model.addAttribute("getCardByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARD_TYPE_BY_PROGRAM));
			model.addAttribute("getDefaultKitNumberByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KIT_NUMBER_BY_PROGRAM));
			model.addAttribute("getRetailerDetails",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_AGENT_BY_DIST_HASH));
			model.addAttribute("getKitNumberByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KIT_BY_RETAILER));
			break;
		case "onboardcustomer":
			if(!role.contains("ROLE_CUSTOMER_REGISTRATION")) {
				log.info("User has no permission to access onboardcustomer page");
				return "404";
			}
			model.addAttribute("customerRegistrationUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CUSTOMER_REGISTRATION));
			model.addAttribute("pincodeUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PINCODE_DETAILS));
			break;
		case "loadamount":
			if(!role.contains("ROLE_LOAD_AMOUNT")) {
				log.info("User has no permission to access loadamount page");
				return "404";
			}
			model.addAttribute("customerListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CUSTOMER_LIST));
			model.addAttribute("loadAmountUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LOAD_AMOUNT));
			model.addAttribute("cardListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARDS_BY_CUSTOMER));
			model.addAttribute("fetchClientIdUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_ID));
			model.addAttribute("pocketListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_ALL_POCKETS_BY_CLIENTCODE));
			model.addAttribute("getWalletDetailsUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_WALLET_DETAILS));
			model.addAttribute("loadWalletUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LOAD_WALLET_AMOUNT));
			model.addAttribute("fetchWalletByCustUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_WALLET_BY_CUST_ID));
			break;
		case "cardloadbulkreport":
			if(!role.contains("ROLE_BULK_REGISTER") || !role.contains("ROLE_BULK_CARD_LOAD") || !role.contains("ROLE_BULK_CARD_CREATE")) {
				log.info("User has no permission to access cardloadbulkreport page");
				return "404";
			}	
			model.addAttribute("bulkReport",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_LOAD_CARD_REPORT));
			model.addAttribute("loadCardOfflineUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_BULK_CARD_LOAD_REPORT));
			break;
		case "bulkcarduploadreport":
			if(!role.contains("ROLE_BULK_REGISTER") || !role.contains("ROLE_BULK_CARD_LOAD") || !role.contains("ROLE_BULK_CARD_CREATE")) {
				log.info("User has no permission to access bulkcarduploadreport page");
				return "404";
			}
			model.addAttribute("bulkUploadReport",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_CARD_UPLOAD_REPORT));
			model.addAttribute("bulkDownloadReport",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_CARD_DOWNLOAD_REPORT));
			break;

		case "ipwhitelist":
			if(!role.contains("ROLE_IP_WHITE_LIST")) {
				log.info("User has no permission to access ipwhitelist page");
				return "404";
			}
			model.addAttribute("ipWhiteListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.IP_WHITE_LIST_URL));
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			break;

		case "getiplist":
			if(!role.contains("ROLE_GET_IP_LIST")) {
				log.info("User has no permission to access getiplist page");
				return "404";
			}
			model.addAttribute("getIPListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_IP_LIST_URL));
			model.addAttribute("ipBlackList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.IP_BLACK_LIST));
			break;
		case "merchantlist":
			if(!role.contains("ROLE_LIST_MERCHANT")) {
				log.info("User has no permission to access merchantlist page");
				return "404";
			}
			model.addAttribute("merchantListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_MERCHANT_LIST));
			model.addAttribute("merchantListBasedOnFilterUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_MERCHANT_LIST_ON_FILTER));
			break;
		case "addmerchant":
			if(!role.contains("ROLE_ADD_MERCHANT")) {
				log.info("User has no permission to access addmerchant page");
				return "404";
			}
			model.addAttribute("merchantAddUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ADD_MERCHANT));
			model.addAttribute("getCashbackTIDUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CASHBACK_TER_LIST));
			model.addAttribute("allBrandListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ALL_BRAND_LIST));
			break;
		case "feemanagement":
			if(!role.contains("ROLE_CLIENT_FEE")) {
				log.info("User has no permission to access feemanagement page");
				return "404";
			}
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("listClientFeesUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_FEES));
			model.addAttribute("updateClientFeesUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_CLIENT_FEES));
			model.addAttribute("clientBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BASED_ON_FILTER));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("ProgramListWithFiltersUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST_WITH_FILTERS));
			model.addAttribute("getCorporateProcessorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CORPORATE_PROCESSOR_LIST));
			model.addAttribute("programDetailsUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_DETAILS));
			break;
		case "addpocket":
			if(!role.contains("ROLE_ADD_POCKET")) {
				log.info("User has no permission to access addpocket page");
				return "404";
			}
			model.addAttribute("mccDataUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_ALL_MCC_CATEGORIES));
			model.addAttribute("addPocketUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ADD_POCKET));
			model.addAttribute("fetchAllClientUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			break;
		case "pocketlist":
			if(!role.contains("ROLE_POCKET_LIST")) {
				log.info("User has no permission to access pocketlist page");
				return "404";
			}
			model.addAttribute("pocketListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_ALL_POCKETS_BY_CLIENTCODE));
			model.addAttribute("fetchAllClientUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("fetchClientIdUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_ID));
			model.addAttribute("updateMaxLimitUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_POCKET_MAX_LIMIT));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("getProgramByIdUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_BY_ID));
			break;

		case "setlimits":
			if(!role.contains("ROLE_FETCH_KYC_LIMIT")) {
				log.info("User has no permission to access setlimits page");
				return "404";
			}
			model.addAttribute("fetchWalletUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_WALLET_LIMIT));
			model.addAttribute("customerKycLimitSetUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.SET_CLIENT_KYC_LIMIT));
			break;
		case "updatelimits":
//			no side bar and no role
			model.addAttribute("fetchAllCustomerUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_ALL_CUSTOMERS));
			model.addAttribute("fetchKycLimitUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_CLIENT_KYC_LIMIT));
			model.addAttribute("updateKycLimitUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_CLIENT_KYC_LIMIT));
			break;
		case "configuremerchantcashback":
			if(!role.contains("ROLE_SET_MER_CASHBACK")) {
				log.info("User has no permission to access configuremerchantcashback page");
				return "404";
			}
			model.addAttribute("getProgramListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_LIST));
			model.addAttribute("getCashbackMIDUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CASHBACK_MER_LIST));
			model.addAttribute("getCashbackTIDUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CASHBACK_TER_LIST));
			model.addAttribute("merchantCashbackUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CONFIGURE_MER_CASHBACK));
			model.addAttribute("allBrandListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ALL_BRAND_LIST));
			model.addAttribute("getMerchantByBrandUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_MERCHANT_BY_BRAND));
			model.addAttribute("getProgramListByPlanUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_BY_PLAN));
			break;
		case "fetchmerchantcashbacklist":
			if(!role.contains("ROLE_GET_MER_CASHBACK")) {
				log.info("User has no permission to access fetchmerchantcashbacklist page");
				return "404";
			}
			model.addAttribute("getMerchantCashbackUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_MER_CASHBACK));
			model.addAttribute("updateMerchantCashbackUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_MER_CASHBACK));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("getMerCashBackOnFilterUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_MER_CASHBACK_BY_FILTER));
			break;

		case "programreport":
			if(!role.contains("ROLE_GET_PROGRAM_TRANSACTION")) {
				log.info("User has no permission to access programreport page");
				return "404";
			}
			model.addAttribute("transactionListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_TRANSACTION));
			model.addAttribute("transactionListBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_TRANSACTION_BASED_ON_FILTER));
			model.addAttribute("getProgramListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_LIST));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_GEN_TRAN_REPORT));
			break;

		case "cardreport":
			if(!role.contains("ROLE_GET_PROGRAM_CARD_TRANSACTION")) {
				log.info("User has no permission to access cardreport page");
				return "404";
			}
			model.addAttribute("transactionListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_TRANSACTION));
			model.addAttribute("transactionListBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_TRANSACTION_BASED_ON_FILTER));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_GEN_TRAN_REPORT));
			break;

		case "clientreport":
			// no role
			model.addAttribute("transactionListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_TRANSACTION));
			model.addAttribute("transactionListBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_TRANSACTION_BASED_ON_FILTER));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_GEN_TRAN_REPORT));
			model.addAttribute("getClientListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_CLIENT_LIST));
			break;
		case "merchantreport":
			if(!role.contains("ROLE_TXN_MERCHANT")) {
				log.info("User has no permission to access merchantreport page");
				return "404";
			}
			model.addAttribute("transactionListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_TRANSACTION));
			model.addAttribute("transactionListBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_TRANSACTION_BASED_ON_FILTER));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_MER_TRAN_REPORT));
			model.addAttribute("getClientListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_CLIENT_LIST));
			break;
		case "setkyclimits":
			if(!role.contains("ROLE_FETCH_KYC_LIMIT")) {
				log.info("User has no permission to access setkyclimits page");
				return "404";
			}
			model.addAttribute("setKycLimitUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.SET_KYC_LIMIT));
			model.addAttribute("listKycLimitUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_LIST_KYC));
			break;
		case "listkyclimits":
//			no role , no side bar
			model.addAttribute("listKycLimitUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_LIST_KYC));
			model.addAttribute("getKycLimitListByFilterUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_KYC_LIMIT_BY_FILTER));
			model.addAttribute("fetchAllCustomerUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_ALL_CUSTOMERS));
			break;
		case "cardinventoryupload":
			if(!role.contains("ROLE_INVENTORY_KIT_UPLOAD")) {
				log.info("User has no permission to access cardinventoryupload page");
				return "404";
			}
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("uploadCardInvUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CARD_INV_BULK_UPLOAD));
			break;
		case "cardinvsummary":
			if(!role.contains("ROLE_GET_CARD_DETAILS")) {
				log.info("User has no permission to access cardinvsummary page");
				return "404";
			}
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("cardInvSummaryUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARD_INV_SUMMARY));
			break;
		case "listcardinventory":
			if(!role.contains("ROLE_CARD_LIST_INV")) {
				log.info("User has no permission to access listcardinventory page");
				return "404";
			}
			model.addAttribute("cardInvListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARD_INV_LIST));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("inventoryKitListDownload",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_CARD_INV_LIST));
			break;
		case "bulkcardinvuploadsummary":
			if(!role.contains("ROLE_CARD_INV_UPLOAD_SUMMARY")) {
				log.info("User has no permission to access bulkcardinvuploadsummary page");
				return "404";
			}
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("bulkUploadReportUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_UPLOAD_INV_FILE_REPORT));
			break;
		case "bulkcardinvreport":
			if(!role.contains("ROLE_BULK_CARD_REPORT")) {
				log.info("User has no permission to access bulkcardinvreport page");
				return "404";
			}
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("bulkCardReport",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARD_INV_UPLOAD_REPORTS));
			break;
		case "feesreport":
			if(!role.contains("FEES_REPORT")) {
				log.info("User has no permission to access feesreport page");
				return "404";
			}
			model.addAttribute("getFeeReportUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_FEE_TRAN_REPORT));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_FEE_TRAN_REPORT));
			break;
		case "revenuemanagement":
			if(!role.contains("REVENUE_LIST")) {
				log.info("User has no permission to access revenuemanagement page");
				return "404";
			}
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("listProgramRevenue",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_PROGRAM_REVENUE));
			model.addAttribute("updateProgramRevUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_PROGRAM_REVENUE));
			model.addAttribute("clientBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BASED_ON_FILTER));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("ProgramListWithFiltersUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST_WITH_FILTERS));
			model.addAttribute("getCorporateProcessorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CORPORATE_PROCESSOR_LIST));
			break;
		case "revenuereport":
			if(!role.contains("REVENUE_REPORT")) {
				log.info("User has no permission to access revenuereport page");
				return "404";
			}
			model.addAttribute("getRevenueReportUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_REV_TRAN_REPORT));
			break;
		case "eodbalancereport":
			if(!role.contains("RECON_REPORT")) {
				log.info("User has no permission to access eodbalancereport page");
				return "404";
			}
			model.addAttribute("eodReportUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_EOD_REPORT));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_EOD_REPORT_OFFLINE));
			break;
		case "customerlegderreport":
			if(!role.contains("RECON_REPORT")) {
				log.info("User has no permission to access customerlegderreport page");
				return "404";
			}
			model.addAttribute("getFeeReportUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_RECON_REPORTS));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_RECON_TRAN_REPORT));
			break;
		case "cardreconreport":
			if(!role.contains("RECON_REPORT")) {
				log.info("User has no permission to access cardreconreport page");
				return "404";
			}
			model.addAttribute("getFeeReportUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_RECON_REPORTS));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_RECON_TRAN_REPORT));
			break;
		case "addrole":
			if(!role.contains("ADD_ROLE")) {
				log.info("User has no permission to access addrole page");
				return "404";
			}
			model.addAttribute("privilegeListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PRIVILEGE_LIST));
			model.addAttribute("validateRoleUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.VALIDATE_ROLE_NAME));
			model.addAttribute("addRoleUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ADD_ROLE));
			break;
		case "onboarddistributor":
			if(!role.contains("ADD_DISTRIBUTOR")) {
				log.info("User has no permission to access onboarddistributor page");
				return "404";
			}
			model.addAttribute("onboardDistributorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ADD_DISTRIBUTOR));
			break;
		case "distributorlist":
			if(!role.contains("LIST_DISTRIBUTOR")) {
				log.info("User has no permission to access distributorlist page");
				return "404";
			}
			model.addAttribute("listOfDistributorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_DISTRIBUTOR));
			model.addAttribute("clientListDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_LIST_DISTRIBUTOR));
			break;
		case "editdistributor":
			//no role no side bar
			String distEmail = request.getParameter("email");
			String distributorId = request.getParameter("userHashId");
			model.addAttribute("editDistributorUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_DIST));
			model.addAttribute("getDistByIdUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DIST_BY_ID));
			model.addAttribute("clientDetails", distEmail);
			model.addAttribute("userHashId", distributorId);
			break;

		case "onboardagent":
			if(!role.contains("ROLE_ADD_AGENT")) {
				log.info("User has no permission to access onboardagent page");
				return "404";
			}
			model.addAttribute("onboardAgentUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ADD_AGENT));
			break;
		case "listagent":
			if(!role.contains("ROLE_LIST_AGENT")) {
				log.info("User has no permission to access listagent page");
				return "404";
			}
			model.addAttribute("listAgentUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_AGENT));
			model.addAttribute("blockUnblockAgent",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BLOCK_UNBLOCK_AGENT));
			break;
		case "adduser":
			if(!role.contains("ADD_ROLE")) {
				log.info("User has no permission to access adduser page");
				return "404";
			}
			model.addAttribute("roleNameUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_ROLE_NAMES));
			model.addAttribute("fetchAllClientUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("addUserUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ADD_USER));
			break;
		case "distcommconfig":
			if(!role.contains("ROLE_GET_DIST_COMM")) {
				log.info("User has no permission to access distcommconfig page");
				return "404";
			}
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("listProgramComm",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DIST_COMM_BY_PROG));
			model.addAttribute("updateProgramCommUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_DIST_COMM));
			model.addAttribute("clientBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BASED_ON_FILTER));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("ProgramListWithFiltersUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST_WITH_FILTERS));
			model.addAttribute("getCorporateProcessorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CORPORATE_PROCESSOR_LIST));
			model.addAttribute("getDistributorListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_DISTRIBUTOR));
			break;
		case "kitassigndistributor":
			if(!role.contains("ROLE_KIT_DIST")) {
				log.info("User has no permission to access kitassigndistributor page");
				return "404";
			}
			model.addAttribute("bulkUploadKitUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_UPLOAD_KIT));
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("getCardByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARD_TYPE_BY_PROGRAM));
			model.addAttribute("getInvKitByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KIT_BY_PROGRAM));
			model.addAttribute("clientByClientHashUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BY_CLIENT_HASH));
			model.addAttribute("distributorsByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DISTLIST_BY_PROGRAM_HASH));
			model.addAttribute("getAssignedkitByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KITS_BY_PROGRAM));
			model.addAttribute("clientByClientIdUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BY_ID));
			model.addAttribute("getDistByUserHashUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DIST_BY_USER_HASH));
			model.addAttribute("getAgentListByDistHashIdURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_AGENT_BY_DIST_HASH));
			model.addAttribute("getKitByDistributorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KITS_BY_DISTRIBUTOR));
			break;

		case "kitassignretailer":
			if(!role.contains("ROLE_KIT_RETAILER")) {
				log.info("User has no permission to access kitassignretailer page");
				return "404";
			}
			model.addAttribute("bulkUploadKitUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.BULK_UPLOAD_KIT));
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("getCardByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CARD_TYPE_BY_PROGRAM));
			model.addAttribute("getInvKitByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KIT_BY_PROGRAM));
			model.addAttribute("clientByClientHashUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BY_CLIENT_HASH));
			model.addAttribute("distributorsByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DISTLIST_BY_PROGRAM_HASH));
			model.addAttribute("getAssignedkitByProgramUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KITS_BY_PROGRAM));
			model.addAttribute("clientByClientIdUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BY_ID));
			model.addAttribute("getDistByUserHashUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DIST_BY_USER_HASH));
			model.addAttribute("getAgentListByDistHashIdURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_AGENT_BY_DIST_HASH));
			model.addAttribute("getKitByDistributorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KITS_BY_DISTRIBUTOR));
			break;
		case "retailercommconfig":
			if(!role.contains("ROLE_GET_RETAILER_COMM")) {
				log.info("User has no permission to access retailercommconfig page");
				return "404";
			}
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("getRetailerComm",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_RETAILER_COMM_BY_PROG));
			model.addAttribute("updateRetailerComm",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_RET_COMM));
			model.addAttribute("clientBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BASED_ON_FILTER));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("ProgramListWithFiltersUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST_WITH_FILTERS));
			model.addAttribute("getCorporateProcessorUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CORPORATE_PROCESSOR_LIST));
			model.addAttribute("getAgentListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_AGENT));
			model.addAttribute("getRetailerDiscountUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_RETAILER_DISCOUNT));
			model.addAttribute("updateRetailerDiscountUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_RETAILER_DISCOUNT));

			break;
		case "retailerbalance":
			if(!role.contains("ROLE_RETAILER_BALANCE")) {
				log.info("User has no permission to access retailerbalance page");
				return "404";
			}
			model.addAttribute("retailerPoolBalanceURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_POOL_BALANCES));
			break;
		case "addmarkup":
			if(!role.contains("ROLE_ADD_MARKUP")) {
				log.info("User has no permission to access addmarkup page");
				return "404";
			}
			model.addAttribute("mccDataUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.FETCH_ALL_MCC_CATEGORIES));
			model.addAttribute("addMccMarkupUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ADD_MCC_MARKUP));
			break;
		case "listmccmarkup":
			if(!role.contains("ROLE_LIST_MARKUP")) {
				log.info("User has no permission to access listmccmarkup page");
				return "404";
			}
			model.addAttribute("listMccMarkupUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_MCC_MARKUP));
			model.addAttribute("deleteMccMarkupUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DEL_MCC_MARKUP));
			break;
		case "markupuploadsummary":
			if(!role.contains("ROLE_MARKUP_UPLOAD_SUMMARY")) {
				log.info("User has no permission to access markupuploadsummary page");
				return "404";
			}
			model.addAttribute("uploadSummaryListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.MARK_UP_UPLOAD_SUMMARY));
			break;
		case "markupuploadreport":
			if(!role.contains("ROLE_MARKUP_UPLOAD_REPORTS")) {
				log.info("User has no permission to access markupuploadreport page");
				return "404";
			}
			model.addAttribute("uploadReportListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.MARK_UP_UPLOAD_REPORTS));
			break;
		case "clientcommconfig":
			if(!role.contains("ROLE_UPDATE_CLIENT_COMM")) {
				log.info("User has no permission to access clientcommconfig page");
				return "404";
			}
			model.addAttribute("fetchAllClientUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("getClientCommissionUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_COMM));
			model.addAttribute("updateCommissionUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_CLIENT_COMM));
			model.addAttribute("getClientDiscountUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_DISCOUNT));
			model.addAttribute("updateDiscountUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_CLIENT_DISCOUNT));
			break;
		case "masteracctransaction":
			if(!role.contains("ROLE_MASTER_TXN")) {
				log.info("User has no permission to access masteracctransaction page");
				return "404";
			}
			model.addAttribute("getMasterAccUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_MASTER_ACC_TXN));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_MASTER_TXN));
			break;
		case "banktransaction":
			if(!role.contains("ROLE_BANK_TXN")) {
				log.info("User has no permission to access banktransaction page");
				return "404";
			}
			model.addAttribute("getBankTxnUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_BANK_TXN));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_BANK_TXN));
			break;
		case "cashbackbanktransaction":
			if(!role.contains("ROLE_CASHBACK_TXN")) {
				log.info("User has no permission to access cashbackbanktransaction page");
				return "404";
			}
			model.addAttribute("getBankTxnUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CASHBACK_TXN));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_CASHBACK_TXN));
			break;
		case "carddetails":
			if(!role.contains("ROLE_CARD_DETAILS")) {
				log.info("User has no permission to access carddetails page");
				return "404";
			}
			model.addAttribute("downloadTxnUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_CARD_DETAILS));
			break;
		case "editmerchant":
//			no role, no side bar
			model.addAttribute("merchantId", request.getParameter("merchantId"));
			model.addAttribute("brandId", request.getParameter("brandId"));
			model.addAttribute("getMerchantDetails",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_MERCHANT_BY_ID_AND_BRAND_ID));
			model.addAttribute("editMerchantUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.EDIT_MERCHANT_DETAILS));
			model.addAttribute("terminalListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_TERMINAL_LIST));
			break;
		case "offersconfig":
			if(!role.contains("ROLE_SET_OFFER")) {
				log.info("User has no permission to access offersconfig page");
				return "404";
			}
			model.addAttribute("getProgramListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_LIST));
			model.addAttribute("merchantListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CASHBACK_MER_LIST));
			model.addAttribute("terminalListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_TERMINAL_LIST));
			model.addAttribute("setOfferUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.SET_MER_OFFER));
			break;
		case "offerlist":
			if(!role.contains("ROLE_GET_OFFER")) {
				log.info("User has no permission to access offerlist page");
				return "404";
			}
			model.addAttribute("offerListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.MER_OFFER_LIST));
			model.addAttribute("editedtable",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.MER_OFFER_LIST_BASED_FILTER));

			break;
		case "transactionlistview":
			if(!role.contains("TRANSACTION_REPORT_VIEW")) {
				log.info("User has no permission to access transactionlistview page");
				return "404";
			}
			model.addAttribute("transactionListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_TRANSACTION_VIEW));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_TRANSACTION_VIEW));
			model.addAttribute("getRetailerDetails",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_AGENT_BY_DIST_HASH));

			break;
		case "onboardbrand":
			if(!role.contains("ONBOARD_BRAND")) {
				log.info("User has no permission to access onboardbrand page");
				return "404";
			}
			model.addAttribute("onBoardBrandUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ONBOARD_BRAND));
			break;
		case "brandlist":
			if(!role.contains("LIST_BRAND")) {
				log.info("User has no permission to access brandlist page");
				return "404";
			}
			model.addAttribute("listBrandUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_BRAND));
			break;
		case "editconfiguremerchantcashback":
//			no role, no side bar
			String offerId = request.getParameter("offerId");
			model.addAttribute("offerId", offerId);
			model.addAttribute("getProgramListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_LIST));
			model.addAttribute("getOfferByIdUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_OFFER_BY_ID));
			model.addAttribute("editMerchantOfferUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.EDIT_MERCHANT_OFFER));
			model.addAttribute("terminateOfferUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.TERMINATE_OFFER_BY_ID));
			model.addAttribute("getProgramListByPlanUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_BY_PLAN));
			model.addAttribute("editProgramListByPlanUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.EDIT_PROGRAM_OFFER));
			break;
		case "addvirtualacc":
			if(!role.contains("ROLE_ADD_VIRTUAL_ACC")) {
				log.info("User has no permission to access addvirtualacc page");
				return "404";
			}
			model.addAttribute("saveVirtualAccUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ADD_VIRTUAL_ACC));
			break;

		case "dailyprefundreport":
			if(!role.contains("ROLE_FETCH_DAILY_PREFUND_TRANSACTION")) {
				log.info("User has no permission to access dailyprefundreport page");
				return "404";
			}
			model.addAttribute("prefundListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PREFUND_LIST));
			model.addAttribute("prefundRequestListBasedOnFilter",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PREFUND_REQUEST_BASED_ON_FILTER));
			model.addAttribute("prefundDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_DAILY_PREFUND_LIST));
			break;
			case "listvirtualaccount":
			if(!role.contains("ROLE_ADD_VIRTUAL_ACC")) {
				log.info("User has no permission to access listvirtualaccount page");
				return "404";
			}
			model.addAttribute("getVirtualAccUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_VIRTUAL_ACCOUNT));
			model.addAttribute("downloadVirtualAccountUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_VIRTUAL_ACCOUNT));
			break;
		case "eodreportlist":
			if(!role.contains("ROLE_GET_EOD_REPORT")) {
				log.info("User has no permission to access eodreportlist page");
				return "404";
			}
			model.addAttribute("eodReportListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_EOD_REPORT_LIST));
			break;
		case "editcustomer":
//			no role, no side bar
			String customerHashIdEdit = request.getParameter("customerHashId");
			model.addAttribute("getCustomerByIdUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CUSTOMER_BY_HASH_ID));
			model.addAttribute("editCustomerUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.EDIT_CUSTOMER));
			model.addAttribute("customerHashId", customerHashIdEdit);
			break;
		case "dashboard":
			if(!role.contains("ROLE_ADMIN_DASHBOARD")) {
				log.info("User has no permission to access dashboard page");
				return "404";
			}
			model.addAttribute("getClientList",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_BY_PROGRAM_PLAN));
			model.addAttribute("getDistributorListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DIST_LIST));
			model.addAttribute("getAgentListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_AGENT));
			model.addAttribute("getDashboardCountTransaction",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_DASHBOARD_TRANSACTION));
			break;
		case "distributorbalance":
			if(!role.contains("ROLE_DISTRIBUTOR_BALANCE")) {
				log.info("User has no permission to access distributorbalance page");
				return "404";
			}
			model.addAttribute("retailerPoolBalanceURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_POOL_BALANCES));
			break;
		case "sorcardlist":
			if(!role.contains("ROLE_GET_CARDS_SOR")) {
				log.info("User has no permission to access sorcardlist page");
				return "404";
			}
			model.addAttribute("cardListSorURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_SOR_CARD_LIST));
			model.addAttribute("cardListDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_SOR_CARD_LIST));
			break;
		case "sorcustomerlist":
			if(!role.contains("ROLE_GET_CUSTOMER_SOR")) {
				log.info("User has no permission to access sorcustomerlist page");
				return "404";
			}
			model.addAttribute("customerListSorURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_SOR_CUSTOMER_LIST));
			model.addAttribute("downloadListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_SOR_CUSTOMER_LIST));
			break;
		case "sortransactionlist":
			if(!role.contains("ROLE_GET_TXN_SOR")) {
				log.info("User has no permission to access sortransactionlist page");
				return "404";
			}
			model.addAttribute("transactionListSorURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_SOR_TXN_LIST));
			model.addAttribute("downloadListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_SOR_TRANSACTION_LIST));
			break;
		case "customereodbalance":
			if(!role.contains("ROLE_GET_CUSTOMER_EOD_BALANCE")) {
				log.info("User has no permission to access customereodbalance page");
				return "404";
			}
			model.addAttribute("customerBalanceURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CUSTOMER_BALANCE_EOD));
			model.addAttribute("customerBalanceDownloadURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_CUSTOMER_BALANCE));
			break;
		case "blockmerchant":
			if(!role.contains("SAVE_BLOCK_MERCHANT")) {
				log.info("User has no permission to access blockmerchant page");
				return "404";
			}
			model.addAttribute("saveBlockMerchantURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.SAVE_BLOCK_MERCHANT));
			break;
		case "listvalidmerchant":
			if(!role.contains("LIST_VALID_MERCHANT")) {
				log.info("User has no permission to access listvalidmerchant page");
				return "404";
			}
			model.addAttribute("listBlockMerchantURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_VALID_MERCHANT));
			model.addAttribute("blockEachMerchantURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.EDIT_BLOCK_MERCHANT));
			break;
		case "offlineprefundrequest":
			if(!role.contains("ROLE_OFFLINE_PREFUND_REQ")) {
				log.info("User has no permission to access offlineprefundrequest page");
				return "404";
			}
			model.addAttribute("prefund_request_url",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.SAVE_OFFLINE_PREFUND));
			break;
		case "reconupload":
			if(!role.contains("RECON_REPORT")) {
				log.info("User has no permission to access reconupload page");
				return "404";
			}
			model.addAttribute("reconUploadURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPLOAD_RECONE_PROCESS));
			break;
		case "customereodbalancesum":
			if(!role.contains("ROLE_GET_CUSTOMER_EOD_BALANCE_SUM")) {
				log.info("User has no permission to access customereodbalancesum page");
				return "404";
			}
			model.addAttribute("customerBalanceURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CUSTOMER_BALANCE_EOD_TOTAL));
			model.addAttribute("customerBalanceSumDownloadURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_CUSTOMER_SUM_BALANCE));
			break;
		case "reconsummaryreport":
			if(!role.contains("RECON_REPORT")) {
				log.info("User has no permission to access reconsummaryreport page");
				return "404";
			}
			model.addAttribute("getFeeReportUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_RECON_SUMMARY_REPORT));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_RECON_SUMMARY_REPORT));
			break;
		case "recondetailsreport":
			if(!role.contains("RECON_REPORT")) {
				log.info("User has no permission to access recondetailsreport page");
				return "404";
			}
			model.addAttribute("getFeeReportUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_RECON_DETAIL_REPORT));
			model.addAttribute("transactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_RECON_DETAIL_REPORT));
			break;
		case "kycsorlist":
			if(!role.contains("ROLE_GET_KYC_SOR_LIST")) {
				log.info("User has no permission to access kycsorlist page");
				return "404";
			}
			model.addAttribute("sorKycListURL", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_SOR_KYC_LIST));
			model.addAttribute("kycListDownloadURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_SOR_KYC_LIST));
			break;
		case "conversionrateconfig":
			if(!role.contains("ROLE_CONVERSION_RATE")) {
				log.info("User has no permission to access conversionrateconfig page");
				return "404";
			}
			model.addAttribute("getProgramListByPlanUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_PROGRAM_BY_PLAN));
			model.addAttribute("conversionRateUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ADD_CONVERSION_RATE));
			break;
		case "conversionrateconfiglist":
			if(!role.contains("ROLE_CONVERSION_RATE_LIST")) {
				log.info("User has no permission to access conversionrateconfiglist page");
				return "404";
			}
			model.addAttribute("conversionRateListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.LIST_CONVERSION_RATE));
			model.addAttribute("updateStatusConversionRateUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_CONVERSION_RATE_STATUS));
			break;
		case "editconversionrateconfig":
//			no role, no side bar
			model.addAttribute("conversionRateUpdateUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_CONVERSION_RATE));
			break;
		case "pointbalancelist":
			if(!role.contains("POINT_BALANCE_LIST")) {
				log.info("User has no permission to access pointbalancelist page");
				return "404";
			}
			model.addAttribute("pointBalanceListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.POINT_BALANCE_LIST));
			model.addAttribute("pointBalanceListDownloadUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.POINT_BALANCE_LIST_DOWNLOAD));
			break;
		case "pointbalanceledgerlist":
			if(!role.contains("POINT_BALANCE_LEDGER_LIST")) {
				log.info("User has no permission to access pointbalanceledgerlist page");
				return "404";
			}
			model.addAttribute("pointBalanceLedgerListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.POINT_BALANCE_LEDGER_LIST));
			model.addAttribute("pointBalanceLedgerListDownloadUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.POINT_BALANCE_LEDGER_LIST_DOWNLOAD));
			break;
		case "hostrecontransactionreport":
			if(!role.contains("RECON_REPORT")) {
				log.info("User has no permission to access hostrecontransactionreport page");
				return "404";
			}
			model.addAttribute("hostrecontransactionreport",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.HOST_RECON_TRANSACTION_REPORT));
			model.addAttribute("hostrecontransactionDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_HOST_RECON_TRANSACTION_REPORT));
			break;
		case "ndclimitdefaultset":
			if(!role.contains("NDC_LIMIT_DEFAULT_SET")) {
				log.info("User has no permission to access ndclimitdefaultset page");
				return "404";
			}
			model.addAttribute("ndclimitsetup",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_NDC_CYCYLE_LIST));
			model.addAttribute("updateNdcCycle",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.UPDATE_NDC_CYCYLE));
			break;
		case "ndclimitssetup":
			if(!role.contains("NDC_LIMIT_SET_UP")) {
				log.info("User has no permission to access ndclimitssetup page");
				return "404";
			}
			model.addAttribute("ndccyclesbyretailer",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.NDC_CYCLES_BY_RETAILER));
			model.addAttribute("setndclimit", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.SET_NDC_LIMIT));
			model.addAttribute("getallretailer",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_ALL_RETAILER));
			break;
		case "cardexpirylist":
			if(!role.contains("ROLE_EXPIRY_CARD_LIST")) {
				log.info("User has no permission to access cardexpirylist page");
				return "404";
			}
			model.addAttribute("cardexpirylist",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_EXPIRY_CARD_LIST));
			model.addAttribute("fetchAllCustomerUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_ALL_CUSTOMERS));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("cardListDownlodUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_EXPIRY_CARD_LIST));
		case "kitbalancelist":
			if(!role.contains("KIT_BALANCE_LIST")) {
				log.info("User has no permission to access kitbalancelist page");
				return "404";
			}
			model.addAttribute("kitbalancelistUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.KIT_BALANCE_LIST));
			model.addAttribute("getAgentListByDistHashIdURL",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_AGENT_BY_DIST_HASH));
			model.addAttribute("kitBalanceDownload",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.KIT_BALANCE_DOWNLOAD_LIST));
			break;
		case "carddetailswithtime":
			if(!role.contains("ROLE_CARD_DETAILS")) {
				log.info("User has no permission to access carddetailswithtime page");
				return "404";
			}
			model.addAttribute("downloadTxnUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.DOWNLOAD_CARD_LIST_DETAILS));
			break;

		case "poapoikyclist":
			if(!role.contains("ROLE_POA_POI_KYC_LIST")) {
				log.info("User has no permission to access poapoikyclist page");
				return "404";
			}
			model.addAttribute("customerPoaPoiKyc",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CUSTOMER_POA_POI_KYC_LIST));
			model.addAttribute("viewPoaPoiRequest",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.VIEW_POA_POI_KYC_REQUEST));
			model.addAttribute("actionOnKycRequest",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.ACTION_ON_KYC_REQUEST));
			break;

		case "poacustomerrequest":
			if(!role.contains("ROLE_POA_REQUEST")) {
				log.info("User has no permission to access poacustomerrequest page");
				return "404";
			}
			model.addAttribute("customerPOA", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CUSTOMER_POA));
			model.addAttribute("customerListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CUSTOMER_LIST));
			break;

		case "poicustomerrequest":
			if(!role.contains("ROLE_POI_REQUEST")) {
				log.info("User has no permission to access poicustomerrequest page");
				return "404";
			}
			model.addAttribute("customerPOI", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CUSTOMER_POI));
			model.addAttribute("customerListUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CUSTOMER_LIST));
			break;

		case "getkycmedia":
			if(!role.contains("GET_KYC_MEDIA")) {
				log.info("User has no permission to access getkycmedia page");
				return "404";
			}
			model.addAttribute("getkycMedia", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_KYC_MEDIA));
			break;
		case "wallettowallettransfer":
			if(!role.contains("CARD_TO_CARD")) {
				log.info("User has no permission to access wallettowallettransfer page");
				return "404";
			}
			model.addAttribute("wallettowallettransferUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.WALLET_TO_WALLET_TRANSFER));
			break;
			
		case "programwebhookurl":
			if(!role.contains("ROLE_WEBHOOK_URL_CONFIGURE")) {
				log.info("User has no permission to access programwebhookurl page");
				return "404";
			}
			model.addAttribute("getClientList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.GET_CLIENT_LIST));
			model.addAttribute("getProgramListUrl", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.PROGRAM_LIST));
			model.addAttribute("configureWebhookUrl",
					CommonUtil.getUrlWithProgram(programUrl, urlMetaData.CONFIGURE_WEBHOOK_URL));

			break;
		case "webhookurllist":
			if(!role.contains("ROLE_WEBHOOK_URL_CONFIGURE")) {
				log.info("User has no permission to access webhookurllist page");
				return "404";
			}
			model.addAttribute("webhookUrlList", CommonUtil.getUrlWithProgram(programUrl, urlMetaData.WEBHOOK_URL_LIST));

			break;

		default:

			model.addAttribute("url", "http://localhost:8080");
			break;
		}
		return page;

	}

	@Override
	public String getErrorPath() {
		// log.info("** Error occured. ");
		return "error";
	}

}
