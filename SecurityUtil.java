package com.ui.product.zokudo.util;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SecurityUtil {

   

    @Value("${applicationLevel.user.password}")
    private String springPassword;

    @Value("${applicationLevel.user.name}")
    private String applicationLevelUserName;

    public String getAuthorizationHeader() {
        try {
            final String userNameAndPwd = applicationLevelUserName + ":" + springPassword;
            final String encodedCredentials = Base64.getEncoder().encodeToString(userNameAndPwd.getBytes());
            return "Basic " + encodedCredentials;
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
            return "";
        }
    }

    public String getAuthorizationHeader(final String userName, final String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return "";
        }
        return "Basic " + Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());
    }
}
