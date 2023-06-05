package com.example.controller;

import com.example.entity.RestBean;
import com.example.service.AuthorizeService;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/valid/email")
    public RestBean<String> validateEmail(@RequestParam("email") String email){
        if (service.sendValidateEmail(email)){
            return RestBean.success("邮件已发送，请注意查收");
        }else {
            return RestBean.failure(10010,"邮件发送失败，请联系管理员");
        }
    }


}
