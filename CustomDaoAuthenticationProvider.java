package com.ui.product.zokudo.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ui.product.zokudo.util.AESEncryption;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
    		UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    	
    	final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
    			new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                AESEncryption.getRsaDecryptedData(authentication.getCredentials().toString()));
    	
    	
    	super.additionalAuthenticationChecks(userDetails, usernamePasswordAuthenticationToken);
    }

    @Override
    protected void doAfterPropertiesSet() {
        super.doAfterPropertiesSet();
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
    	
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        		new UsernamePasswordAuthenticationToken(principal,
                AESEncryption.getRsaDecryptedData(authentication.getCredentials().toString()));
        
        return super.createSuccessAuthentication(principal, usernamePasswordAuthenticationToken, user);
    }

    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected PasswordEncoder getPasswordEncoder() {
        return super.getPasswordEncoder();
    }

    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Override
    protected UserDetailsService getUserDetailsService() {
        return super.getUserDetailsService();
    }

    @Override
    public void setUserDetailsPasswordService(UserDetailsPasswordService userDetailsPasswordService) {
        super.setUserDetailsPasswordService(userDetailsPasswordService);
    }
}