package com.company.scorh.controller;

import com.company.scorh.common.ServerResponse;
import com.company.scorh.domain.User;
import com.company.scorh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.company.scorh.common.Constant.NEED_LOGIN;

@Controller
@RequestMapping("user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    @ResponseBody
    public ServerResponse login(HttpSession session, String username, String password){
            return userService.login(session,username,password);
    }

    @PostMapping("register")
    @ResponseBody
    public ServerResponse register(String username,String password,String email){
        return userService.register(username,password,email);
    }

    @GetMapping("active")
    public String activeAccount(String username,String activeCode){
        return userService.activeAccount(username,activeCode);
    }

    @GetMapping("user_info")
    @ResponseBody
    public ServerResponse getUserInfo(HttpSession session){
        User user=(User)session.getAttribute("user");
        if (user==null) return ServerResponse.createResponse(NEED_LOGIN,"请登陆!");
        return userService.getUserInfo(user);
    }

    @GetMapping("reservation")
    @ResponseBody
    public ServerResponse getReservation(HttpSession session){
        User user=(User)session.getAttribute("user");
        if (user==null) return ServerResponse.createResponse(NEED_LOGIN,"请登陆!");
        return userService.getReservation(user);
    }

    @GetMapping("notice")
    @ResponseBody
    public ServerResponse getNotice(HttpSession session){
        User user=(User)session.getAttribute("user");
        if (user==null) return ServerResponse.createResponse(NEED_LOGIN,"请登陆!");
        return userService.getNotice();
    }

    @PostMapping("reserve")
    @ResponseBody
    public ServerResponse reserve(HttpSession session,Integer noticeId){
        User user=(User)session.getAttribute("user");
        if (user==null) return ServerResponse.createResponse(NEED_LOGIN,"请登陆!");
        return userService.reserve(user,noticeId);
    }

    @GetMapping("logout")
    @ResponseBody
    public ServerResponse logout(HttpSession session){
        User user=(User)session.getAttribute("user");
        if (user==null) return ServerResponse.createResponse(NEED_LOGIN,"请登陆!");
        return userService.logout(session);
    }

    @PostMapping("update_user")
    @ResponseBody
    public ServerResponse update(HttpSession session,String oldPassword,String newPassword){
        User user=(User)session.getAttribute("user");
        if (user==null) return ServerResponse.createResponse(NEED_LOGIN,"请登陆!");
        return userService.update(user,oldPassword,newPassword);
    }

    @PostMapping("cancel")
    @ResponseBody
    public ServerResponse cancelReservation(HttpSession session,int noticeId){
        User user=(User)session.getAttribute("user");
        if (user==null) return ServerResponse.createResponse(NEED_LOGIN,"请登陆!");
        return userService.cancelReservation(user,noticeId);
    }
}
