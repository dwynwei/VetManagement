package com.vetmanagement.vetmanagement.security.oauth2.user;

import java.security.AuthProvider;
import java.util.Map;

import com.vetmanagement.vetmanagement.dojo.Provider;
import com.vetmanagement.vetmanagement.exception.OAuth2AuthenticationProcessingException;

public class OAuth2UserInfoFactory {

	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(Provider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        }else {
        	 throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
	
}
