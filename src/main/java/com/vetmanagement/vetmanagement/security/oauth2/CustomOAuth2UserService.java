package com.vetmanagement.vetmanagement.security.oauth2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vetmanagement.vetmanagement.dao.UserRepository;
import com.vetmanagement.vetmanagement.dojo.Provider;
import com.vetmanagement.vetmanagement.dojo.User;
import com.vetmanagement.vetmanagement.exception.OAuth2AuthenticationProcessingException;
import com.vetmanagement.vetmanagement.security.UserPrincipal;
import com.vetmanagement.vetmanagement.security.oauth2.user.OAuth2UserInfo;
import com.vetmanagement.vetmanagement.security.oauth2.user.OAuth2UserInfoFactory;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User auth2User = super.loadUser(userRequest);
		
		try {
			// Yazılacak
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return super.loadUser(userRequest);
	}
	
	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
		
		if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("E-Mail Not Found By Provider");
		}
		
		Optional<User> optional = this.userRepository.findByEmail(oAuth2UserInfo.getEmail());
		
		User user;
		
		if(optional.isPresent()) {
			user = optional.get();
			if(!user.getProvider().equals(Provider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
				throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		}else {
			user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
		}
		
		return UserPrincipal.create(user, oAuth2User.getAttributes());
		
	}
	
	private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();

        user.setProvider(Provider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }
	
}
