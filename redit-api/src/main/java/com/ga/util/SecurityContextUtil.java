package com.ga.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextUtil {
	public Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
