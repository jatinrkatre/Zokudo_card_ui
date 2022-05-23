package com.ui.product.zokudo.error;

import lombok.Data;

@Data
public class RegisterError {
	
	private boolean valid;

	
	private double register;
	
	private String clientRemarks;
	
	private String toAccountNumber;
	
	private String toBank;
	
	private String paymentMethod;
	
	private String emailId;
	
	private String existingPassword;
	
	private String newPassword;
	
	private  String confirmPassword;
	

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getExistingPassword() {
		return existingPassword;
	}

	public void setExistingPassword(String existingPassword) {
		this.existingPassword = existingPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public double getRegister() {
		return register;
	}

	public void setRegister(double register) {
		this.register = register;
	}

	public String getClientRemarks() {
		return clientRemarks;
	}

	public void setClientRemarks(String clientRemarks) {
		this.clientRemarks = clientRemarks;
	}

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public String getToBank() {
		return toBank;
	}

	public void setToBank(String toBank) {
		this.toBank = toBank;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}



	}
	
	


