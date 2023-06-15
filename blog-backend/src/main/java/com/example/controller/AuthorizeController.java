package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.User;
import com.example.service.AuthorizeService;
import com.sun.mail.smtp.DigestMD5;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/register")
    public RestBean<String> registerUser(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         @RequestParam("email") String email,
                                         @RequestParam("code") String code){
        //1、先去数据库查询username是否存在，若存在，返回id和password
        User existingUser = service.findUserByUsername(username);
        User existingEmail = service.findUserByEmail(email);
        //2、判断是否存在，若存在，则直接返回账户已注册
        if (existingUser!=null){
            return RestBean.failure(780,"账户已注册");
        } else if (existingEmail!=null) {
            return RestBean.failure(781,"邮箱已注册");
        } else  {
            //3、创建新用户并设置属性
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            newUser.setEmail(email);
            //4、保存新用户到数据库
            service.saveUser(newUser);
            //5、返回注册成功信息
            return RestBean.success("注册成功");
        }
    }

    @PutMapping("/resetPassword")
    public RestBean<String> resetPassword(@RequestParam("username") String username,
                                          @RequestParam("password") String password,
                                          @RequestParam("email") String email,
                                          @RequestParam("code") String code) {
        User resetUser = service.findUserByUsername(username);
        User resetEmail = service.findUserByEmail(email);
        if (resetUser == null) {
            return RestBean.failure(782, "用户不存在");
        } else if (resetEmail == null) {
            return RestBean.failure(783, "邮箱不存在");
        } else {
            if (code != service.findEmailCode(email)){
                return RestBean.failure(784, "验证码不正确");
            }
            service.deleteEmailCode(email);
            resetUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            int result = service.updateUser(resetUser);
            if (result != 0){
                return RestBean.success("更新成功");
            }else {
                return RestBean.failure(785,"更新失败");
            }
        }
    }



    @GetMapping("/hello")
    public String hello(HttpServletRequest request){
        return request.getSession().getAttribute("id").toString();
    }
}
