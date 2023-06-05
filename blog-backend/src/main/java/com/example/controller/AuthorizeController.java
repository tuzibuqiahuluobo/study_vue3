package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.User;
import com.example.service.AuthorizeService;
import com.sun.mail.smtp.DigestMD5;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

import static com.example.util.JwtUtil.generateToken;

/**
 * @author Ikun
 */
@Validated
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthorizeController {
    private final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";

    @Autowired
    private AuthorizeService service;

    @PostMapping("/login")
    public RestBean<String> login(@RequestParam("username") String username, @RequestParam("password") String password){
        //1、去数据库查询username是否存在，若存在，返回id和password
        User user = service.findUserByUsername(username);
        //2、判断是否存在,若存在，则判断密码是否正确
        if (user != null){
            if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
                return RestBean.failure(10021, "密码错误");
            }else{
                //若密码正确，则根据id、username、password等信息生产token
                String token = generateToken(user);
                return RestBean.success(token);
            }

        }else{
            return RestBean.failure(10020, "用户不存在");
        }
    }



    @PostMapping("/valid/email")
    public RestBean<String> validateEmail(@RequestParam("email") String email){
        if (service.sendValidateEmail(email)){
            return RestBean.success("邮件已发送，请注意查收");
        }else {
            return RestBean.failure(10010,"邮件发送失败，请联系管理员");
        }
    }


    //



    @GetMapping("/hello")
    public String hello(HttpServletRequest request){
        return request.getSession().getAttribute("id").toString();
    }
}
