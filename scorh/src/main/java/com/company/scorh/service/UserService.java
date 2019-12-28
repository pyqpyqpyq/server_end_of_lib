package com.company.scorh.service;

import com.company.scorh.common.ServerResponse;
import com.company.scorh.domain.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    ServerResponse login(HttpSession session, String username, String password);
    ServerResponse  register(String username,String password,String email);
    String activeAccount(String username,String activeCode);
    ServerResponse getUserInfo(User user);
    ServerResponse getReservation(User user);
    ServerResponse getNotice();
    ServerResponse reserve(User user,int noticeId);
    ServerResponse logout(HttpSession session);
    ServerResponse update(User user,String oldPassword,String newPassword);
    ServerResponse cancelReservation(User user,int noticeId);
}
