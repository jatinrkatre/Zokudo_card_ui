
package com.ui.product.zokudo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlMetaData {

	private static final String dynamicUrl = "{}/api/";

	public final String CUSTOM_LOGIN_URL;
	public final String AUTHENTICATE_AND_AUTHORIZE_USER;
	public final String PROGRAM_DETAILS;
	public final String CUSTOMER_BY_ID;
	public final String CARD_LIST;
	public final String BLOCK_CARD;
	public final String SEND_OTP;
	public final String FETCH_PAGE_DETAILS;
	public final String USER_BY_USERNAME;
	public final String GET_PROGRAM_TRANSACTION;
	public final String CUSTOMER_LIST;
	public final String PREFUND_REQUEST;
	public final String DASHBOARD_COUNT;

	public final String PREFUND_LIST;
	public final String BLOCK_UNBLOCK_CUSTOMER;

	public final String ACCEPT_REJECT_PREFUND_REQUEST;
	public final String FETCH_BALANCE;
	public final String SET_PIN;
	public final String GET_CVV;
	public final String GET_CARD_DETAILS;

	public final String CHANGE_PASSWORD;
	public final String GET_FETCH_TRANSACTION;
	public final String PREFUND_HISTORY;
	public final String GET_TRANSACTION_BASED_ON_FILTER;
	public final String GET_PROGRAM_LIST;
	public final String FORGET_PASSWORD;
	public final String UPDATE_PASSWORD;
	public final String ON_BOARD_CLIENT;

	public final String ONBORAD_PROGRAM;
	public final String GET_CLIENT_LIST;
	public final String BLOCK_UNBLOCK_CLIENT;
	public final String GET_USERS_LIST;
	public final String GET_FILTER_USERS_LIST;
	public final String BLOCK_UNBLOCK_USERS;
	public final String PROGRAM_LIST;
	public final String BLOCK_UNBLOCK_PROGRAM;
	public final String ADD_CORPORATE_PROCESSOR;
	public final String GET_CUSTOMER_BASED_ON_FILTER;
	public final String GET_CARD_BASED_ON_FILTER;
	public final String GET_PREFUND_REQUEST_BASED_ON_FILTER;
	public final String LIST_CORPORATE_PROCESSOR;
	public final String BULK_UPLOAD_KIT;
	public final String DOWNLOAD_TRANSACTION_REPORT;
	public final String GET_ACTIVITY_LOGS;
	public final String DOWNLOAD_PREFUND_LIST;
	public final String DOWNLOAD_KYC_LIST;
	public final String DOWNLOAD_KYC_LIST_FILTERED;
	public final String DOWNLOAD_CARD_LIST;
	public final String DOWNLOAD_PROGRAM_LIST;
	public final String PROGRAM_LIST_WITH_FILTERS;
	public final String DOWNLOAD_CLIENT_LIST;
	public final String COMMERCIAL;
	public final String DOWNLOAD_LIST_URL;
	public final String CLIENT_OTP_VERIFICATION;
	public final String GET_COMMERCIAL;
	public final String GET_COMMERCIAL_LIST;
	public final String EDIT_COMMERCIAL;
	public final String EDIT_CLIENT_DETAILS;
	public final String GET_CLIENT_BY_ID;
	public final String ADD_CLIENT_ADMIN;
	public final String EDIT_PROGRAM;
	public final String GET_PROGRAM_BY_PROGRAMHASHID;
	public final String GET_CORPORATE_PROCESSOR_LIST;
	public final String GET_FILTERED_CORPORATE_PROCESSOR_LIST;
	public final String CLIENT_KIT_NUMBERS;
	public final String BULK_CARD_LOAD;
	public final String BULK_REGISTER;
	public final String BULK_CARD_CREATE;
	public final String PROCESS_BULK_REGISTER;
	public final String PROCESS_BULK_CARD_CREATION;
	public final String PROCESS_BULK_CARD_LOAD;
	public final String BULK_REPORT;
	public final String BULK_CARD_REPORT;
	public final String FETCH_PROCESSORS;
	public final String GET_CUSTOMER_LIST;
	public final String CREATE_CARD;
	public final String CUSTOMER_REGISTRATION;
	public final String GET_PINCODE_DETAILS;
	public final String LOAD_AMOUNT;
	public final String BULK_UPLOAD_REPORT;
	public final String BULK_DOWNLOAD_REPORT;
	public final String BULK_LOAD_CARD_REPORT;
	public final String BULK_CARD_UPLOAD_REPORT;
	public final String BULK_CARD_DOWNLOAD_REPORT;
	public final String GET_CARDS_BY_CUSTOMER;
	public final String GET_TRANSACTION_GRAPH_DATA;
	public final String GET_CARDS_GRAPH_DATA;
	public final String GET_CUSTOMERS_GRAPH_DATA;
	public final String FETCH_CLIENT_LIST;
	public final String IP_WHITE_LIST_URL;
	public final String GET_IP_LIST_URL;
	public final String IP_BLACK_LIST;
	public final String ADD_MERCHANT;
	public final String GET_MERCHANT_LIST;
	public final String GET_MERCHANT_LIST_ON_FILTER;
	public final String GET_CLIENT_FEES;
	public final String UPDATE_CLIENT_FEES;
	public final String GET_CLIENT_BASED_ON_FILTER;
	public final String FETCH_ALL_MCC_CATEGORIES;
	public final String ADD_POCKET;
	public final String FETCH_ALL_POCKETS_BY_CLIENTCODE;
	public final String GET_BIN_NUMBER;
	public final String GET_SUB_BIN_NUMBER;
	public final String SET_CLIENT_KYC_LIMIT;
	public final String FETCH_CLIENT_KYC_LIMIT;
	public final String UPDATE_CLIENT_KYC_LIMIT;
	public final String CONFIGURE_MER_CASHBACK;
	public final String FETCH_MER_CASHBACK;
	public final String UPDATE_MER_CASHBACK;
	public final String GET_ALL_CUSTOMERS;
	public final String CASHBACK_MER_LIST;
	public final String CASHBACK_TER_LIST;
    public final String GET_CARD_TYPE_BY_PROGRAM;
    public final String SET_KYC_LIMIT;
    public final String GET_LIST_KYC;
    public final String GET_HOST_URL;
    public final String GET_CLIENT_ID;
    public final String GET_WALLET_DETAILS;
    public final String LOAD_WALLET_AMOUNT;
    public final String UPDATE_POCKET_MAX_LIMIT;
    public final String GET_CLIENT_KIT_BY_FILTER;
    public final String GET_MER_CASHBACK_BY_FILTER;
    public final String FETCH_WALLET_BY_CUST_ID;
    public final String FETCH_KYC_LIMIT_BY_FILTER;
    public final String GET_KIT_NUMBER_BY_PROGRAM;
    public final String GET_PROGRAM_BY_ID;
    public final String GET_PROGRAM_BY_FILTER;
    public final String CARD_INV_BULK_UPLOAD;
    public final String GET_CARD_INV_SUMMARY;
    public final String GET_CARD_INV_LIST;
    public final String GET_UPLOAD_INV_FILE_REPORT;
    public final String GET_CARD_INV_UPLOAD_REPORTS;
    public final String GET_KIT_BY_PROGRAM;
    public final String GET_WALLET_LIMIT;
    public final String FETCH_CARD_BALANCE;
    public final String GET_PREFUND_LIST;
    public final String UPDATE_PREFUND_REQ;
    public final String CHECK_PROG_FOR_CLIENT;
    public final String GET_FEE_TRAN_REPORT;
    public final String LIST_PROGRAM_REVENUE;
    public final String UPDATE_PROGRAM_REVENUE;
    public final String GET_REV_TRAN_REPORT;
    public final String DOWNLOAD_MER_TRAN_REPORT;
    public final String DOWNLOAD_FEE_TRAN_REPORT;
    public final String DOWNLOAD_GEN_TRAN_REPORT;
    public final String GET_RECON_REPORTS;
    public final String DOWNLOAD_RECON_TRAN_REPORT;
    public final String GET_PRIVILEGE_LIST;
    public final String VALIDATE_ROLE_NAME;
    public final String ADD_ROLE;
    public final String ADD_DISTRIBUTOR;
    public final String LIST_DISTRIBUTOR;
    public final String DOWNLOAD_LIST_DISTRIBUTOR;
    public final String GET_DIST_BY_ID;
    public final String UPDATE_DIST;
    public final String ADD_AGENT;
    public final String LIST_AGENT;
    public final String BLOCK_UNBLOCK_AGENT;
    public final String UPDATE_AGENT_PREFUND_REQ;
    

    public final String GET_CLIENT_BY_CLIENT_HASH;
    public final String GET_DISTLIST_BY_PROGRAM_HASH;
    public final String GET_KITS_BY_PROGRAM;
    public final String GET_DIST_LIST;
    public final String GET_DIST_BY_USER_HASH;
    public final String GET_ROLE_NAMES;
    public final String ADD_USER;
    public final String GET_AGENT_BY_DIST_HASH;
    public final String GET_KITS_BY_DISTRIBUTOR;
    public final String GET_DIST_COMM_BY_PROG;
    public final String UPDATE_DIST_COMM;
    public final String GET_RETAILER_COMM_BY_PROG;
    public final String UPDATE_RET_COMM;
    public final String GET_KIT_BY_RETAILER;
    public final String GET_RETAILER_LIST;
    public final String GET_POOL_BALANCES;
    public final String DOWNLOAD_ASSIGNED_KIT_LIST;
    public final String DOWNLOAD_CARD_INV_LIST;
    public final String ADD_MCC_MARKUP;
    public final String LIST_MCC_MARKUP;
    public final String DEL_MCC_MARKUP;
    public final String MARK_UP_UPLOAD_SUMMARY;
    public final String MARK_UP_UPLOAD_REPORTS;
    public final String GET_CLIENT_COMM;
    public final String UPDATE_CLIENT_COMM;
    public final String GET_CLIENT_DISCOUNT;
    public final String UPDATE_CLIENT_DISCOUNT;
    public final String GET_MASTER_ACC_TXN;
    public final String GET_BANK_TXN;
    public final String DOWNLOAD_MASTER_TXN;
    public final String DOWNLOAD_BANK_TXN;
    public final String GET_CASHBACK_TXN;
    public final String DOWNLOAD_CASHBACK_TXN;
    public final String DOWNLOAD_CARD_DETAILS;
    public final String GET_MERCHANT_DETAILS;
    public final String EDIT_MERCHANT_DETAILS;
    public final String GET_TERMINAL_LIST;
    public final String SET_MER_OFFER;
    public final String MER_OFFER_LIST;
    public final String UPDATE_MER_OFFER;
    public final String GET_RETAILER_DISCOUNT;
    public final String UPDATE_RETAILER_DISCOUNT;
    public final String GET_EOD_REPORT;
    public final String GET_EOD_REPORT_OFFLINE;
    public final String GET_TRANSACTION_VIEW;
    public final String DOWNLOAD_TRANSACTION_VIEW;
    public final String DOWNLOAD_BULK_CUSTOMER_REPORT;
    public final String DOWNLOAD_BULK_CARD_CREATE_REPORT;
    public final String DOWNLOAD_BULK_CARD_LOAD_REPORT;
    public final String ONBOARD_BRAND;
    public final String ALL_BRAND_LIST;
    public final String LIST_BRAND;
    public final String GET_MERCHANT_BY_BRAND;
    public final String EDIT_MERCHANT_OFFER;
    public final String GET_OFFER_BY_ID;
    public final String TERMINATE_OFFER_BY_ID;
    public final String GET_MERCHANT_BY_ID_AND_BRAND_ID;
    public final String ADD_VIRTUAL_ACC;
    public final String DOWNLOAD_DAILY_PREFUND_LIST;
    public final String GET_PROGRAM_BY_PLAN;
    public final String GET_VIRTUAL_ACCOUNT;
    public final String DOWNLOAD_VIRTUAL_ACCOUNT;
    public final String UPDATE_KYC;
    public final String GET_EOD_REPORT_LIST;
    public final String CUSTOMER_BY_HASH_ID;
    public final String EDIT_CUSTOMER;
    public final String GET_SUB_BIN_BY_PROGRAMID;
    public final String GET_CLIENT_BY_PROGRAM_PLAN;
    public final String GET_DASHBOARD_TRANSACTION;
    public final String MER_OFFER_LIST_BASED_FILTER;
    public final String GET_SOR_CARD_LIST;
    public final String GET_SOR_CUSTOMER_LIST;
    public final String GET_SOR_TXN_LIST;
    public final String GET_CUSTOMER_BALANCE_EOD;
    public final String SAVE_BLOCK_MERCHANT;
    public final String EDIT_BLOCK_MERCHANT;
    public final String LIST_VALID_MERCHANT ;
    public final String SAVE_OFFLINE_PREFUND;
    public final String UPLOAD_RECONE_PROCESS;
    public final String DOWNLOAD_SOR_CUSTOMER_LIST;
    public final String DOWNLOAD_SOR_CARD_LIST;
    public final String DOWNLOAD_SOR_TRANSACTION_LIST;
    public final String GET_CUSTOMER_BALANCE_EOD_TOTAL;
    public final String GET_RECON_SUMMARY_REPORT;
    public final String DOWNLOAD_CUSTOMER_BALANCE;
    public final String DOWNLOAD_CUSTOMER_SUM_BALANCE;
    public final String GET_RECON_DETAIL_REPORT;
    public final String DOWNLOAD_RECON_DETAIL_REPORT;
    public final String DOWNLOAD_RECON_SUMMARY_REPORT;
    public final String GET_SOR_KYC_LIST;
    public final String DOWNLOAD_SOR_KYC_LIST;
    public final String ADD_CONVERSION_RATE;
    public final String LIST_CONVERSION_RATE;
    public final String UPDATE_CONVERSION_RATE_STATUS;
    public final String UPDATE_CONVERSION_RATE;
    public final String POINT_BALANCE_LIST;
    public final String POINT_BALANCE_LEDGER_LIST;
    public final String POINT_BALANCE_LIST_DOWNLOAD;
    public final String POINT_BALANCE_LEDGER_LIST_DOWNLOAD;
    public final String HOST_RECON_TRANSACTION_REPORT;
    public final String DOWNLOAD_HOST_RECON_TRANSACTION_REPORT;
    public final String EDIT_PROGRAM_OFFER;
    public final String GET_NDC_CYCYLE_LIST;
    public final String UPDATE_NDC_CYCYLE;
    public final String NDC_CYCLES_BY_RETAILER;
    public final String SET_NDC_LIMIT;
    public final String GET_ALL_RETAILER;
    public final String GET_EXPIRY_CARD_LIST;
    public final String KIT_BALANCE_LIST;
    public final String KIT_BALANCE_DOWNLOAD_LIST;
    public final String DOWNLOAD_EXPIRY_CARD_LIST;
    public final String DOWNLOAD_CARD_LIST_DETAILS;
    public final String GET_KYC_MEDIA;
    public final String GET_JWT_TOKEN;
    public final String PRODUCT_CONTEXT_WITHOUT_URL;
    public final String CUSTOMER_POA_POI_KYC_LIST;
    public final String VIEW_POA_POI_KYC_REQUEST;
    public final String ACTION_ON_KYC_REQUEST;
    public final String WALLET_TO_WALLET_TRANSFER;
    public final String CUSTOMER_POA;
    public final String CUSTOMER_POI;
    public final String CONFIGURE_WEBHOOK_URL;
    public final String WEBHOOK_URL_LIST;


    public UrlMetaData(@Value(value = "${version}") String version,
                       @Value(value = "${url.context.authcontext}") String AUTH_CONTEXT,
                       @Value(value = "${url.context.productcontext}") String PRODUCT_CONTEXT,
                       @Value(value = "${url.context.customercontext}") String CUSTOMER_CONTEXT,
                       @Value(value = "${url.context.walletcontext}") String WALLET_CONTEXT,
                       @Value(value = "${url.context.cardcontext}") String CARD_CONTEXT,
                       @Value(value = "${url.context.sorcontext}") String SOR_CONTEXT,
                       @Value(value = "${url.context.autobotsui}") String AUTOBOTS_CONTEXT) {


		AUTH_CONTEXT = AUTH_CONTEXT + version;
		this.PRODUCT_CONTEXT_WITHOUT_URL = PRODUCT_CONTEXT;
		PRODUCT_CONTEXT = PRODUCT_CONTEXT + dynamicUrl + version;
		CUSTOMER_CONTEXT = CUSTOMER_CONTEXT + dynamicUrl + version;
		WALLET_CONTEXT = WALLET_CONTEXT + dynamicUrl + version;
		CARD_CONTEXT = CARD_CONTEXT + dynamicUrl + version;
		SOR_CONTEXT = SOR_CONTEXT + dynamicUrl + version;
		
		this.DASHBOARD_COUNT = CARD_CONTEXT + "/getDashboardCount";
		this.CUSTOM_LOGIN_URL = AUTOBOTS_CONTEXT + "{}/auth/program";

		this.CUSTOMER_BY_ID = CUSTOMER_CONTEXT + "/customerById";
		this.AUTHENTICATE_AND_AUTHORIZE_USER = AUTH_CONTEXT + "/authentication/authrequest";
		this.PROGRAM_DETAILS = PRODUCT_CONTEXT + "/product/programDetails";
		this.CARD_LIST = CARD_CONTEXT + "/getCardList";
		this.SEND_OTP = PRODUCT_CONTEXT + "/product/sendOTP";
		this.FETCH_PAGE_DETAILS = PRODUCT_CONTEXT + "/product/getPageDetails";
		this.USER_BY_USERNAME = PRODUCT_CONTEXT + "/product/findByUsername";
		this.GET_PROGRAM_TRANSACTION = WALLET_CONTEXT + "/getTransactionReport";
		this.CUSTOMER_LIST = CUSTOMER_CONTEXT + "/fetchCustomerByProgram";
		this.BLOCK_CARD = CARD_CONTEXT + "/blockCard";
		this.PREFUND_REQUEST = CARD_CONTEXT + "/prefundRequest";
		this.PREFUND_LIST = CARD_CONTEXT + "/fetchPrefundTranscation";
		this.BLOCK_UNBLOCK_CUSTOMER = CUSTOMER_CONTEXT + "/blockUnblockCustomer";
		this.ACCEPT_REJECT_PREFUND_REQUEST = WALLET_CONTEXT + "/client/";
		this.CHANGE_PASSWORD = PRODUCT_CONTEXT + "/product/changePassword";
		this.SET_PIN = CARD_CONTEXT + "/setPin";
		this.GET_CVV = CARD_CONTEXT + "/getCvv";
		this.FETCH_BALANCE = CARD_CONTEXT + "/fetchBalance";
		this.GET_CARD_DETAILS = CARD_CONTEXT + "/getCardDetails";
		this.GET_FETCH_TRANSACTION = CARD_CONTEXT + "/fetchTransaction";
		this.PREFUND_HISTORY = CARD_CONTEXT + "/prefundHistoryList";
		this.GET_TRANSACTION_BASED_ON_FILTER = WALLET_CONTEXT + "/getProgramTransactionBasedOnfilter";
		this.GET_PROGRAM_LIST = PRODUCT_CONTEXT + "/product/getProgramList";
		this.WEBHOOK_URL_LIST = PRODUCT_CONTEXT + "/product/getWebhookUrls";
		this.FORGET_PASSWORD = PRODUCT_CONTEXT + "/product/forgetPassword";
		this.UPDATE_PASSWORD = PRODUCT_CONTEXT + "/product/updatePassword";
		this.ON_BOARD_CLIENT = PRODUCT_CONTEXT + "/client/onboardClient";
		this.ONBORAD_PROGRAM = PRODUCT_CONTEXT + "/program/create";
		this.GET_CLIENT_LIST = PRODUCT_CONTEXT + "/client/getclient";
		this.BLOCK_UNBLOCK_CLIENT = PRODUCT_CONTEXT + "/client/blockUnblockClient";
		this.GET_USERS_LIST = PRODUCT_CONTEXT + "/client/getUsers";
		this.GET_FILTER_USERS_LIST = PRODUCT_CONTEXT + "/client/getFilteredUsers";
		this.BLOCK_UNBLOCK_USERS = PRODUCT_CONTEXT + "/client/blockUnblockUsers";
		this.PROGRAM_LIST = PRODUCT_CONTEXT + "/program/list";
		this.BLOCK_UNBLOCK_PROGRAM = PRODUCT_CONTEXT + "/program/blockUnblock";
		this.ADD_CORPORATE_PROCESSOR = PRODUCT_CONTEXT + "/program/addCorporateProcessor";
		this.GET_CUSTOMER_BASED_ON_FILTER = CUSTOMER_CONTEXT + "/customerListBasedOnFilter";
		this.GET_CARD_BASED_ON_FILTER = CARD_CONTEXT + "/getCardListBasedOnfilter";
		this.GET_PREFUND_REQUEST_BASED_ON_FILTER = WALLET_CONTEXT + "/getPrefundListBasedOnfilter";
		this.LIST_CORPORATE_PROCESSOR = PRODUCT_CONTEXT + "/program/listCorporateProcessor";
		this.DOWNLOAD_TRANSACTION_REPORT = CARD_CONTEXT + "/downloadTransactionReport";
		this.BULK_UPLOAD_KIT = CARD_CONTEXT + "/bulkUpload";
		this.GET_ACTIVITY_LOGS = AUTH_CONTEXT + "/authentication/getActivityLogs";
		this.DOWNLOAD_PREFUND_LIST = WALLET_CONTEXT + "/downloadPrefundList";
		this.DOWNLOAD_KYC_LIST = CUSTOMER_CONTEXT + "/downloadKycList";
		this.DOWNLOAD_KYC_LIST_FILTERED = CUSTOMER_CONTEXT + "/kycListBasedOnFilter";
		this.DOWNLOAD_CARD_LIST = CARD_CONTEXT + "/downloadCardList";
		this.DOWNLOAD_PROGRAM_LIST = PRODUCT_CONTEXT + "/program/downloadProgramList";
		this.PROGRAM_LIST_WITH_FILTERS = PRODUCT_CONTEXT + "/program/listProgramWithFilters";
		this.DOWNLOAD_CLIENT_LIST = PRODUCT_CONTEXT + "/program/downloadClientList";
		this.COMMERCIAL = PRODUCT_CONTEXT + "/commercial/createCommercial";
		this.DOWNLOAD_LIST_URL = CUSTOMER_CONTEXT + "/downloadCustomer";
		this.CLIENT_OTP_VERIFICATION = PRODUCT_CONTEXT + "/client/verifyOnboardingOTP";
		this.GET_COMMERCIAL_LIST = PRODUCT_CONTEXT + "/commercial/getCommercialList";
		this.GET_COMMERCIAL = PRODUCT_CONTEXT + "/commercial/getCommercialByHashId";
		this.EDIT_COMMERCIAL = PRODUCT_CONTEXT + "/commercial/editCommercial";
		this.EDIT_CLIENT_DETAILS = PRODUCT_CONTEXT + "/client/updateClient";
		this.GET_CLIENT_BY_ID = PRODUCT_CONTEXT + "/client/getClientById";
		this.ADD_CLIENT_ADMIN = PRODUCT_CONTEXT + "/client/addClientAdmin";
		this.GET_PROGRAM_BY_PROGRAMHASHID = PRODUCT_CONTEXT + "/program/programdetails";
		this.EDIT_PROGRAM = PRODUCT_CONTEXT + "/program/updateProgram";
		this.GET_CORPORATE_PROCESSOR_LIST = PRODUCT_CONTEXT + "/program/listcorporate";
		this.GET_FILTERED_CORPORATE_PROCESSOR_LIST = PRODUCT_CONTEXT + "/program/filteredCorporateProcessorList";
		this.CONFIGURE_WEBHOOK_URL = PRODUCT_CONTEXT + "/product/configureWebhookUrl";
		this.CLIENT_KIT_NUMBERS = CARD_CONTEXT + "/clientKitNumbers";
		this.BULK_REGISTER = CUSTOMER_CONTEXT + "/bulkRegister";
		this.PROCESS_BULK_REGISTER = CUSTOMER_CONTEXT + "/processBulkRegister";

		this.BULK_CARD_LOAD = CARD_CONTEXT + "/bulkCardLoad";
		this.PROCESS_BULK_CARD_LOAD = CARD_CONTEXT + "/processBulkCardLoad";
		this.BULK_CARD_CREATE = CARD_CONTEXT + "/bulkCardCreate";
		this.PROCESS_BULK_CARD_CREATION = CARD_CONTEXT + "/processBulkCardCreate";
		this.BULK_REPORT = CUSTOMER_CONTEXT + "/bulkReport";
		this.BULK_CARD_REPORT = CARD_CONTEXT + "/bulkCardReport";
		this.FETCH_PROCESSORS = PRODUCT_CONTEXT + "/program/fetchProcessors";
		this.GET_CUSTOMER_LIST = CUSTOMER_CONTEXT + "/fetchCustomerListByProgram";
		this.CREATE_CARD = CARD_CONTEXT + "/createCard";
		this.CUSTOMER_REGISTRATION = CUSTOMER_CONTEXT + "/customerRegistration";
		this.BULK_UPLOAD_REPORT=  CUSTOMER_CONTEXT +"/bulkUploadReport";

		this.GET_PINCODE_DETAILS = CUSTOMER_CONTEXT + "/checkPincode";
		this.LOAD_AMOUNT = CARD_CONTEXT + "/loadAmount";
		this.BULK_DOWNLOAD_REPORT = CUSTOMER_CONTEXT + "/download";
		this.BULK_LOAD_CARD_REPORT = CARD_CONTEXT + "/bulkLoadCardReport";
		this.BULK_CARD_UPLOAD_REPORT = CARD_CONTEXT + "/bulkCardUploadReport";
		this.BULK_CARD_DOWNLOAD_REPORT = CARD_CONTEXT + "/bulkCardDownload";
		this.GET_CARDS_BY_CUSTOMER = CARD_CONTEXT + "/getCardsByCustomer";
		this.GET_TRANSACTION_GRAPH_DATA = CARD_CONTEXT + "/getTransactionGraphData";
		this.GET_CARDS_GRAPH_DATA = CARD_CONTEXT + "/getCardGraphData";
		this.GET_CUSTOMERS_GRAPH_DATA = CUSTOMER_CONTEXT + "/getCustomerGraphData";
		this.FETCH_CLIENT_LIST = PRODUCT_CONTEXT + "/client/getClientList";
		this.IP_WHITE_LIST_URL= PRODUCT_CONTEXT + "/product/ipwhitelist";
		this.GET_IP_LIST_URL= PRODUCT_CONTEXT + "/product/getIPList";
		this.IP_BLACK_LIST= PRODUCT_CONTEXT + "/product/ipBlackList";
		this.GET_CLIENT_BASED_ON_FILTER = PRODUCT_CONTEXT + "/client/getClientOnfilter";
		this.GET_CLIENT_FEES = WALLET_CONTEXT + "/feemanagement/fees";
		this.UPDATE_CLIENT_FEES = WALLET_CONTEXT + "/feemanagement/updatefees";
		
		this.ADD_MERCHANT = CUSTOMER_CONTEXT + "/addMerchant";
		this.GET_MERCHANT_LIST = CUSTOMER_CONTEXT + "/merchantList";
		this.GET_MERCHANT_LIST_ON_FILTER = CUSTOMER_CONTEXT + "/merchantListOnfilter";
		
		this.FETCH_ALL_MCC_CATEGORIES = WALLET_CONTEXT + "/wallet/fetchAllMccCategories";
		this.ADD_POCKET = WALLET_CONTEXT + "/wallet/addPocket";
		this.FETCH_ALL_POCKETS_BY_CLIENTCODE = WALLET_CONTEXT + "/wallet/listPockets";
		this.GET_BIN_NUMBER = PRODUCT_CONTEXT + "/product/getBinNumber";
		this.GET_SUB_BIN_NUMBER = PRODUCT_CONTEXT + "/product/getSubBinNumber";
		this.SET_CLIENT_KYC_LIMIT = WALLET_CONTEXT + "/wallet/setLimits";
		this.FETCH_CLIENT_KYC_LIMIT = WALLET_CONTEXT + "/client";
		this.UPDATE_CLIENT_KYC_LIMIT = WALLET_CONTEXT +"/client";
		this.CONFIGURE_MER_CASHBACK = WALLET_CONTEXT + "/cashback/configureMerchantCashback";
		this.FETCH_MER_CASHBACK = WALLET_CONTEXT + "/cashback/fetchMerchantCashbackList";
		this.UPDATE_MER_CASHBACK = WALLET_CONTEXT +"/cashback/updateCashbackStatus";
		this.GET_ALL_CUSTOMERS = CUSTOMER_CONTEXT + "/fetchAllCustomer";
		this.CASHBACK_MER_LIST = CUSTOMER_CONTEXT+ "/merchantListWithoutPagination";
		this.CASHBACK_TER_LIST = CUSTOMER_CONTEXT + "/merchant";
		this.GET_CARD_TYPE_BY_PROGRAM = PRODUCT_CONTEXT + "/program";
		this.SET_KYC_LIMIT = CUSTOMER_CONTEXT + "/kyc/setLimits";
		this.GET_LIST_KYC = CUSTOMER_CONTEXT + "/kyc/fetchLimits"; 
		this.CUSTOMER_POA_POI_KYC_LIST = CUSTOMER_CONTEXT + "/poapoi/getPoaPoiKycList";
		this.VIEW_POA_POI_KYC_REQUEST = CUSTOMER_CONTEXT + "/poapoi/viewPoaPoiRequest";
		this.ACTION_ON_KYC_REQUEST = CUSTOMER_CONTEXT + "/poapoi/actionOnKycRequest";
		this.CUSTOMER_POA= CUSTOMER_CONTEXT + "/poapoi/customerPOA";
		this.CUSTOMER_POI= CUSTOMER_CONTEXT + "/poapoi/customerPOI";
		
		this.GET_HOST_URL = AUTOBOTS_CONTEXT;
		this.GET_CLIENT_ID = PRODUCT_CONTEXT + "/client" ;
		this.GET_WALLET_DETAILS = WALLET_CONTEXT + "/getWallet";
		this.LOAD_WALLET_AMOUNT = WALLET_CONTEXT + "/loadWallet";
		this.UPDATE_POCKET_MAX_LIMIT = WALLET_CONTEXT + "/wallet/updatePocketMaxLimit";
		this.GET_CLIENT_KIT_BY_FILTER = CARD_CONTEXT + "/filteredClientKitNumbers";
		this.GET_MER_CASHBACK_BY_FILTER = WALLET_CONTEXT + "/cashback/merchantCashBackListOnfilter";
		this.FETCH_WALLET_BY_CUST_ID = WALLET_CONTEXT + "/customer/";
		this.FETCH_KYC_LIMIT_BY_FILTER = WALLET_CONTEXT + "/kyc/getKYCLimitsByFilters";
		this.GET_KIT_NUMBER_BY_PROGRAM = CARD_CONTEXT + "/getKitNumbersByProgram";
		this.GET_PROGRAM_BY_ID = PRODUCT_CONTEXT + "/product/programDetails";
		this.GET_PROGRAM_BY_FILTER = PRODUCT_CONTEXT + "/program/listProgramWithFilters";
		this.CARD_INV_BULK_UPLOAD = CARD_CONTEXT + "/inventory/bulkUpload";
		this.GET_CARD_INV_SUMMARY = CARD_CONTEXT + "/inventory/getCardDetails";
		this.GET_CARD_INV_LIST = CARD_CONTEXT + "/inventory/getCardList";
		this.GET_UPLOAD_INV_FILE_REPORT = CARD_CONTEXT + "/inventory/bulkCardUploadReport";
		this.GET_CARD_INV_UPLOAD_REPORTS = CARD_CONTEXT + "/inventory/bulkCardReport";
		this.GET_KIT_BY_PROGRAM = CARD_CONTEXT + "/inventory/getKitByProgram";
		this.GET_WALLET_LIMIT = WALLET_CONTEXT + "/wallet/fetchLimits";
		this.FETCH_CARD_BALANCE = WALLET_CONTEXT + "/fetchCardBalance";
		this.GET_PREFUND_LIST = WALLET_CONTEXT + "/client";
		this.UPDATE_PREFUND_REQ = WALLET_CONTEXT + "/client";
		this.UPDATE_AGENT_PREFUND_REQ = WALLET_CONTEXT + "/agent";
		this.CHECK_PROG_FOR_CLIENT = PRODUCT_CONTEXT + "/program/getProgramforClient";
		this.GET_FEE_TRAN_REPORT = WALLET_CONTEXT + "/feemanagement/report/fetchFeeTransactions";
		this.LIST_PROGRAM_REVENUE = WALLET_CONTEXT + "/revenueMngnt/getRevenue";
		this.UPDATE_PROGRAM_REVENUE = WALLET_CONTEXT + "/revenueMngnt/updateRevenue";
		this.GET_REV_TRAN_REPORT = WALLET_CONTEXT + "/revenueMngnt/report/fetchRevenueTransactions";
		this.DOWNLOAD_MER_TRAN_REPORT = WALLET_CONTEXT + "/downloadTransactionList";
		this.DOWNLOAD_FEE_TRAN_REPORT = WALLET_CONTEXT + "/feemanagement/downloadTransactionList";
		this.DOWNLOAD_GEN_TRAN_REPORT = WALLET_CONTEXT + "/general/downloadTransactionList";
		this.GET_RECON_REPORTS = WALLET_CONTEXT + "/recon/getReports";
		this.DOWNLOAD_RECON_TRAN_REPORT = WALLET_CONTEXT + "/recon/generic/downloadTransactionList";
		this.GET_PRIVILEGE_LIST = PRODUCT_CONTEXT + "/role/privileges";
		this.VALIDATE_ROLE_NAME = PRODUCT_CONTEXT+ "/role";
		this.ADD_ROLE = PRODUCT_CONTEXT + "/role/addRole";
		this.ADD_DISTRIBUTOR = PRODUCT_CONTEXT + "/distributor/add";
		this.LIST_DISTRIBUTOR = PRODUCT_CONTEXT +"/distributor/list";
		this.DOWNLOAD_LIST_DISTRIBUTOR = PRODUCT_CONTEXT +"/distributor/downloadlist";
		this.GET_DIST_BY_ID = PRODUCT_CONTEXT + "/distributor/details/id";
		this.UPDATE_DIST = PRODUCT_CONTEXT + "/distributor/update";
		this.ADD_AGENT= PRODUCT_CONTEXT + "/agent/addAgent";
		this.LIST_AGENT= PRODUCT_CONTEXT + "/agent/listAgent";
		this.BLOCK_UNBLOCK_AGENT = PRODUCT_CONTEXT + "/agent/blockUnblockAgent";
		this.GET_CLIENT_BY_CLIENT_HASH = PRODUCT_CONTEXT + "/client/";
		this.GET_DISTLIST_BY_PROGRAM_HASH = PRODUCT_CONTEXT +"/distributor/getDistByProgram";
		this.GET_KITS_BY_PROGRAM = CARD_CONTEXT + "/cardissuance/getKitsByProgram";
		this.GET_DIST_LIST = PRODUCT_CONTEXT + "/distributor/getDistributorList";
		this.GET_DIST_BY_USER_HASH = PRODUCT_CONTEXT+"/distributor";
		this.GET_ROLE_NAMES = PRODUCT_CONTEXT +"/role/allRoles";
		this.ADD_USER = PRODUCT_CONTEXT + "/user/addUser";
		this.GET_AGENT_BY_DIST_HASH = PRODUCT_CONTEXT + "/agent";
		this.GET_KITS_BY_DISTRIBUTOR = CARD_CONTEXT + "/cardissuance/getKitsByDistributorId";
		this.GET_DIST_COMM_BY_PROG = WALLET_CONTEXT + "/commmission/distributor/getDistComm";
		this.UPDATE_DIST_COMM = WALLET_CONTEXT + "/commmission/distributor/updateDistCommission";
		this.GET_RETAILER_COMM_BY_PROG = WALLET_CONTEXT + "/commmission/retailer/getRetailerComm";
		this.UPDATE_RET_COMM = WALLET_CONTEXT + "/commmission/retailer/updateRetailerCommission";
		this.GET_KIT_BY_RETAILER = CARD_CONTEXT + "/cardissuance/getKitsByRetailerId";
		this.GET_RETAILER_LIST = PRODUCT_CONTEXT + "/agent/getRetailerList";
		this.GET_POOL_BALANCES = WALLET_CONTEXT + "/wallet/getListOfPoolBalances";
		this.DOWNLOAD_ASSIGNED_KIT_LIST = CARD_CONTEXT + "/kitAssign/downloadCardList";
		this.DOWNLOAD_CARD_INV_LIST = CARD_CONTEXT + "/inventory/downloadCardList";
		this.ADD_MCC_MARKUP = WALLET_CONTEXT + "/markup/add" ;
		this.LIST_MCC_MARKUP = WALLET_CONTEXT + "/markups";
		this.DEL_MCC_MARKUP = WALLET_CONTEXT;
		this.MARK_UP_UPLOAD_SUMMARY = WALLET_CONTEXT + "/markup/uploadSummary";
		this.MARK_UP_UPLOAD_REPORTS = WALLET_CONTEXT + "/markup/uploadReports";
		this.GET_CLIENT_COMM = WALLET_CONTEXT + "/commission/client/getClientCommission";
		this.UPDATE_CLIENT_COMM = WALLET_CONTEXT + "/commission/client/updateClientCommission";
		this.GET_CLIENT_DISCOUNT =WALLET_CONTEXT + "/commission/client/getClientDiscount";
		this.UPDATE_CLIENT_DISCOUNT = WALLET_CONTEXT + "/commission/client/updateClientDiscount";
		this.GET_MASTER_ACC_TXN = WALLET_CONTEXT + "/mat/transactions";
		this.GET_BANK_TXN = WALLET_CONTEXT + "/bank/transactions";
		this.DOWNLOAD_MASTER_TXN = WALLET_CONTEXT + "/mat/downloadTransactionList";
		this.DOWNLOAD_BANK_TXN = WALLET_CONTEXT + "/bank/downloadTransactionList";
		this.GET_CASHBACK_TXN = WALLET_CONTEXT + "/cashback/transactions";
		this.DOWNLOAD_CASHBACK_TXN =WALLET_CONTEXT + "/cashback/downloadTransactionList";
		this.DOWNLOAD_CARD_DETAILS = CARD_CONTEXT + "/fetchCardDetailsListByAgentHashId";
		this.GET_MERCHANT_DETAILS = CUSTOMER_CONTEXT + "/merchant";
		this.EDIT_MERCHANT_DETAILS = CUSTOMER_CONTEXT +"/merchant/edit";
		this.GET_TERMINAL_LIST = CUSTOMER_CONTEXT + "/merchant";
		this.SET_MER_OFFER = WALLET_CONTEXT +"/merchant/configureBrandOffer";
		this.GET_RETAILER_DISCOUNT = WALLET_CONTEXT + "/commmission/retailer/getRetailerDiscount";
		this.UPDATE_RETAILER_DISCOUNT = WALLET_CONTEXT + "/commmission/retailer/updateRetailerDiscount";
		this.GET_EOD_REPORT = WALLET_CONTEXT + "/report/eodbalance/allrecords";
		this.GET_EOD_REPORT_OFFLINE = WALLET_CONTEXT + "/report/eodbalance/downloadTransactionList";
		this.GET_TRANSACTION_VIEW = WALLET_CONTEXT + "/getTransactionView";
		this.DOWNLOAD_TRANSACTION_VIEW = WALLET_CONTEXT + "/view/downloadTransactionList";
		this.DOWNLOAD_BULK_CUSTOMER_REPORT = CUSTOMER_CONTEXT + "/download";
		this.DOWNLOAD_BULK_CARD_CREATE_REPORT =  CARD_CONTEXT +"/bulk/create/download";
		this.DOWNLOAD_BULK_CARD_LOAD_REPORT =  CARD_CONTEXT +"/bulk/load/download";
		this.ONBOARD_BRAND = CUSTOMER_CONTEXT + "/merchantBrand/onboardBrand"; ///
		this.ALL_BRAND_LIST = CUSTOMER_CONTEXT + "/merchantBrand/all/brandList";
		this.LIST_BRAND = CUSTOMER_CONTEXT + "/merchantBrand/brandList";
		this.GET_MERCHANT_BY_BRAND = CUSTOMER_CONTEXT ;
		this.MER_OFFER_LIST = WALLET_CONTEXT +"/cashback/getOfferList";
	    this.MER_OFFER_LIST_BASED_FILTER = WALLET_CONTEXT +"/cashback/getOfferListBasedOnCondtion";
		this.UPDATE_MER_OFFER = WALLET_CONTEXT +"/merchant/updateBrandOfferStatus";
		this.EDIT_MERCHANT_OFFER = WALLET_CONTEXT +"/cashback/updateCashbackOfferConfigurationDetails";
		this.GET_OFFER_BY_ID = WALLET_CONTEXT +"/cashback/";
		this.TERMINATE_OFFER_BY_ID = WALLET_CONTEXT +"/cashback/";
		this.GET_MERCHANT_BY_ID_AND_BRAND_ID = CUSTOMER_CONTEXT +  "/merchant/fetchMerchantByBrandIdAndMid";
		this.ADD_VIRTUAL_ACC = WALLET_CONTEXT + "/virtualAccount/saveVirtualAccount";
		this.DOWNLOAD_DAILY_PREFUND_LIST = WALLET_CONTEXT + "/downloadDailyPrefundList";
		this.GET_PROGRAM_BY_PLAN = PRODUCT_CONTEXT + "/program/getListOfProgramsByProgramPlans";
		this.GET_VIRTUAL_ACCOUNT = WALLET_CONTEXT + "/virtualAccount/getVirtualAccounts";
		this.DOWNLOAD_VIRTUAL_ACCOUNT = WALLET_CONTEXT + "/virtualAccount/downloadVirtualAccountList";
		this.UPDATE_KYC = CUSTOMER_CONTEXT + "/updateKYC";
		this.GET_EOD_REPORT_LIST = WALLET_CONTEXT + "/report/eodbalance/getEODReportList";
		this.CUSTOMER_BY_HASH_ID = CUSTOMER_CONTEXT + "/customerByHashId";
		this.EDIT_CUSTOMER = CUSTOMER_CONTEXT +"/updateCustomerDetail";
		this.GET_SUB_BIN_BY_PROGRAMID = PRODUCT_CONTEXT + "/product/getBinByProgram";
		this.GET_CLIENT_BY_PROGRAM_PLAN = PRODUCT_CONTEXT + "/client/clientListByProgramPlan";
		this.GET_DASHBOARD_TRANSACTION = WALLET_CONTEXT + "/getDashboardCountTransaction";
		this.GET_SOR_CARD_LIST = SOR_CONTEXT +"/cards/list";
		this.GET_SOR_CUSTOMER_LIST = SOR_CONTEXT + "/customer/fetch";
		this.GET_SOR_TXN_LIST = SOR_CONTEXT + "/transaction/fetch";
		this.GET_CUSTOMER_BALANCE_EOD = SOR_CONTEXT +"/transaction/eod/balance";
		this.SAVE_BLOCK_MERCHANT = WALLET_CONTEXT + "/AuthValidator/saveMerchant";
		this.EDIT_BLOCK_MERCHANT = WALLET_CONTEXT + "/AuthValidator/editMerchant";
		this.LIST_VALID_MERCHANT = WALLET_CONTEXT + "/AuthValidator/fetchMerchantAuthValidatorList";
		this.SAVE_OFFLINE_PREFUND = WALLET_CONTEXT + "/client/savePrefundRequestByAdmin";
		this.UPLOAD_RECONE_PROCESS = WALLET_CONTEXT + "/reconservice/transactionRecon";
		this.DOWNLOAD_SOR_CUSTOMER_LIST = SOR_CONTEXT + "/customer/downloadCustomer";
		this.DOWNLOAD_SOR_CARD_LIST = SOR_CONTEXT + "/cards/carddownloadList";
		this.DOWNLOAD_SOR_TRANSACTION_LIST = SOR_CONTEXT + "/transaction/downloadTransaction";
	    this.GET_CUSTOMER_BALANCE_EOD_TOTAL = SOR_CONTEXT +"/transaction/eod/sum/balance";
        this.GET_RECON_SUMMARY_REPORT = WALLET_CONTEXT + "/recon/getReconSummaryReport";
        this.DOWNLOAD_CUSTOMER_BALANCE = SOR_CONTEXT + "/transaction/downloadCustomerBalance";
	    this.DOWNLOAD_CUSTOMER_SUM_BALANCE = SOR_CONTEXT + "/transaction/downloadCustomerSumBalance";
        this.GET_RECON_DETAIL_REPORT = WALLET_CONTEXT + "/recon/getReconReportBasedOnfilter";
        this.DOWNLOAD_RECON_DETAIL_REPORT = WALLET_CONTEXT +"/recon/view/downloadReconReport";
        this.DOWNLOAD_RECON_SUMMARY_REPORT =WALLET_CONTEXT+"/recon/downloadReconSummaryReport";
        this.GET_SOR_KYC_LIST = SOR_CONTEXT +"/customer/getCustomerKycLimitListBasedOnfilters";
        this.DOWNLOAD_SOR_KYC_LIST =SOR_CONTEXT+"/customer/view/downloadCustomerKycLimitReport";
        this.ADD_CONVERSION_RATE=WALLET_CONTEXT+"/coversionRate/ConversionRateCashbackConfig";
        this.LIST_CONVERSION_RATE=WALLET_CONTEXT+"/coversionRate/getConversionRateList";
        this.UPDATE_CONVERSION_RATE=WALLET_CONTEXT+"/coversionRate/UpdateConversionRate";
        this.UPDATE_CONVERSION_RATE_STATUS =WALLET_CONTEXT+"/coversionRate/UpdateConversionRateStatus";
        this.POINT_BALANCE_LIST =WALLET_CONTEXT+"/point/list";
        this.POINT_BALANCE_LEDGER_LIST=WALLET_CONTEXT+"/pointRepository/list";
        this.POINT_BALANCE_LIST_DOWNLOAD =WALLET_CONTEXT+"/point/downloadPointBalanceList";
        this.POINT_BALANCE_LEDGER_LIST_DOWNLOAD =WALLET_CONTEXT+"/pointRepository/downloadPointBalanceLedgerList";
        this.HOST_RECON_TRANSACTION_REPORT = WALLET_CONTEXT + "/hostReconTransactionReport";
        this.DOWNLOAD_HOST_RECON_TRANSACTION_REPORT = WALLET_CONTEXT + "/view/downloadHostReconTransactionReport";
        this.EDIT_PROGRAM_OFFER =WALLET_CONTEXT +"/cashback/editProgram";
        this.GET_NDC_CYCYLE_LIST =WALLET_CONTEXT+"/ndclimit/cycleList";
        this.UPDATE_NDC_CYCYLE =WALLET_CONTEXT+"/ndclimit/updateCycle";
        this.NDC_CYCLES_BY_RETAILER =WALLET_CONTEXT+"/ndclimit/getNDCCyclesByRetailer";
        this.SET_NDC_LIMIT =WALLET_CONTEXT+"/ndclimit/setNDCLimit";
        this.GET_ALL_RETAILER =PRODUCT_CONTEXT+"/agent/getAllRetailer";
        this.GET_EXPIRY_CARD_LIST=CARD_CONTEXT+"/getExpiryCardListBasedOnfilter"; 
        this.KIT_BALANCE_LIST =CARD_CONTEXT+"/getKitBalanceListOnfilter";
        this.KIT_BALANCE_DOWNLOAD_LIST =CARD_CONTEXT+"/downloadKitBalance";
        this.DOWNLOAD_EXPIRY_CARD_LIST =CARD_CONTEXT +"/downloadExpiryCardList";
        this.DOWNLOAD_CARD_LIST_DETAILS = CARD_CONTEXT + "/fetchCardDetailsListbyDateTimeAndByAgentHashId";
        this.GET_KYC_MEDIA = CUSTOMER_CONTEXT+"/digioKyc/getkycMedia";   
        this.GET_JWT_TOKEN = this.PRODUCT_CONTEXT_WITHOUT_URL + "token/createToken";
        this.WALLET_TO_WALLET_TRANSFER=WALLET_CONTEXT+"/walletToWalletTransfer";
    }

}