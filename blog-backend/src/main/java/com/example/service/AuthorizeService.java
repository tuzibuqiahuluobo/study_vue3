package com.example.service;


import com.example.entity.User;

/**
 * @author Ikun
 */
public interface AuthorizeService {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户模型
     */
    User findUserByUsername(String username);
    User findUserByEmail(String email);

    boolean sendValidateEmail(String email);

    int saveUser(User newUser);

    int updateUser(User user);

    int saveEmailCode(String email, String code);

    String findEmailCode(String email);

    int deleteEmailCode(String email);
}
