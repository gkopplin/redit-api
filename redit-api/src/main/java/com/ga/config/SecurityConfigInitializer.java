package com.ga.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class SecurityConfigInitializer extends AbstractSecurityWebApplicationInitializer {
	public SecurityConfigInitializer() {
        super(SecurityConfig.class);
    }
}
