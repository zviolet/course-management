package com.zviolet.controller;

import com.zviolet.entity.Userlogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zviolet on 2017/9/11.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    //登录表单显示
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String loginUI() throws Exception {
        return "/login";
    }

    //登录表单处理
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Userlogin userlogin) throws Exception {
        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getUsername(),
                userlogin.getPassword());
        Subject subject = SecurityUtils.getSubject();

        /*
         * 调用login（）方法后，Shiro将进行身份验证，Shiro会自动调用自定义Realm的
         * doGetAuthenticationInfo（）方法。
         */
        subject.login(token);

        //授权，Shiro会自动调用自定义Realm的 doGetAuthorizationInfo（）方法。
        if (subject.hasRole("admin")) {
            return "redirect:/admin/showStudent";
        } else if (subject.hasRole("teacher")) {
            return "redirect:/teacher/showCourse";
        } else if (subject.hasRole("student")) {
            return "redirect:/student/showCourse";
        }
        return "/login";
    }
}
