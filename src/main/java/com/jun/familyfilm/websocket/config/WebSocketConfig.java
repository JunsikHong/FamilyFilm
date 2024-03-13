package com.jun.familyfilm.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import com.jun.familyfilm.security.jwt.JwtTokenProvider;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("http://localhost:3000").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/familyfilm");
        registry.enableSimpleBroker("/invite", "/notification"); //초대메시지보냄
    }

//	@Override
//	public void configureClientInboundChannel(ChannelRegistration registration) {
//		registration.interceptors(new ChannelInterceptor() {
//			@Override
//			public Message<?> preSend(Message<?> message, MessageChannel channel) {
//				StompHeaderAccessor accessor =
//						MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//				if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//					Authentication authentication = jwtTokenProvider.getAuthentication(accessor.getLogin().split(" ")[1]);
//					Authentication realAuthentication = jwtTokenProvider.authenticate(authentication);
//					SecurityContextHolder.getContext().setAuthentication(realAuthentication);
//					accessor.setUser(realAuthentication);
//				}
//				return message;
//			}
//		});
//	}
    
    
}
