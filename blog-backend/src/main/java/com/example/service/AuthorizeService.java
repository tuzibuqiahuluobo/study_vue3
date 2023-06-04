package com.example.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Ikun
 */
public interface AuthorizeService extends UserDetailsService {
    boolean sendValidateEmail(String email);
}
