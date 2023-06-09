package com.example.service.impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Ikun
 */
@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Value("${spring.mail.username}")
    String from;
    @Resource
    UserMapper mapper;
    @Resource
    MailSender mailSender;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (username == null) {
//            throw new UsernameNotFoundException("用户名不能为空");
//        }
//        Account account = mapper.findAccountByNameOrEmail(username);
//        if (account == null) {
//            throw new UsernameNotFoundException("用户名或密码错误");
//        }
//        return User
//                .withUsername(account.getUsername())
//                .password(account.getPassword())
//                .roles("user")
//                .build();
//    }

    public User findUserByUsername(String username) {
        return mapper.findUserByUsername(username);
    }

    public User findUserByEmail(String email) {
        return mapper.findUserByUserEmail(email);
    }

    /**
     * 1.先生成对应的验证码
     * 2.把邮箱和对应的验证码直接放在Redis里面(过期时间三分钟，如果此时重新要求发邮件，
     * 那么只要剩余时间低于两分钟，就可以重新发送一次，并重复此流程)
     * 3.发送验证码到指定邮箱
     * 4.如果发送失败，把redis里面的刚刚插入的删除
     * 5.用户在注册时，再从Redis里面取出对应键值对，然后看验证码是否一致
     */
    @Override
    public boolean sendValidateEmail(String email) {
        String realCode = mapper.findEmailCode(email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("您的验证邮件");

        if (realCode != null){
            message.setText("验证码：" + realCode);
            mailSender.send(message);
            return true;
        }

        Random random = new Random();
        int code = random.nextInt(899999) + 100000;
        message.setText("验证码：" + code);
        try {
            mailSender.send(message);
            mapper.saveEmailCode(email, String.valueOf(code));
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int saveUser(User newUser) {
        return mapper.saveUser(newUser);
    }

    @Override
    public int updateUser(User user) {
        return mapper.updateUser(user);
    }

    @Override
    public int saveEmailCode(String email, String code) {
        return mapper.saveEmailCode(email, code);
    }

    @Override
    public String findEmailCode(String email) {
        return mapper.findEmailCode(email);
    }

    @Override
    public int deleteEmailCode(String email) {
        return mapper.deleteEmailCode(email);
    }


}
