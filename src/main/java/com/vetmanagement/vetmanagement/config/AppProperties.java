package com.vetmanagement.vetmanagement.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
	
	private final Auth auth = new Auth();
	
	private final OAuth2 oAuth2 = new OAuth2();
	
	public Auth getAuth() {
		return auth;
	}

	public OAuth2 getoAuth2() {
		return oAuth2;
	}

	public static class Auth {
		
		private String tokenSecret;
		
		private Long tokenExp;

		public String getTokenSecret() {
			return tokenSecret;
		}

		public void setTokenSecret(String tokenSecret) {
			this.tokenSecret = tokenSecret;
		}

		public Long getTokenExp() {
			return tokenExp;
		}

		public void setTokenExp(Long tokenExp) {
			this.tokenExp = tokenExp;
		}		
	}
	
	public static class OAuth2{
		
		private List<String> authorizedRedirectUris = new ArrayList<>();
		
		public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }
		
		public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
			this.authorizedRedirectUris = authorizedRedirectUris;
			return this;
		}
		
	}
	
}
