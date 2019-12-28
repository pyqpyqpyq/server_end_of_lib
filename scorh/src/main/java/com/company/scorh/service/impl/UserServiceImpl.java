package com.company.scorh.service.impl;

import com.company.scorh.common.ServerResponse;
import com.company.scorh.common.SimpleCache;
import com.company.scorh.dao.GroupDao;
import com.company.scorh.dao.NoticeDao;
import com.company.scorh.dao.UserDao;
import com.company.scorh.domain.Group;
import com.company.scorh.domain.Notice;
import com.company.scorh.domain.User;
import com.company.scorh.service.UserService;
import com.company.scorh.util.CodeUtil;
import com.company.scorh.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.UUID;

import static com.company.scorh.common.Constant.ERROR;
import static com.company.scorh.common.Constant.SUCCESS;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private GroupDao groupDao;

    @Override
    public ServerResponse login(HttpSession session, String username, String password) {
        String encryptedPassword = CodeUtil.encrypt(password);
        User user = userDao.selectLogin(username, encryptedPassword);
        if (user == null)
            return ServerResponse.createResponse(ERROR, "用户名或密码错误!");
        if (!user.isActive())
            return ServerResponse.createResponse(ERROR, "请激活账号!");
        session.setAttribute("user", user);
        return ServerResponse.createResponse(SUCCESS, "登陆成功!");
    }

    @Override
    public ServerResponse register(String username, String password, String email) {
        User user = userDao.select(username);
        if (user != null)
            return ServerResponse.createResponse(ERROR, "该用户名已存在!");
        String encryptedPassword = CodeUtil.encrypt(password);
        user = new User(username, encryptedPassword, email, false);
        int result = userDao.insert(user);
        if (result == 1) {
            String activeCode = UUID.randomUUID().toString();
            SimpleCache.setAttribute(username, activeCode);
            String msg = "<h2>hi," + username +
                    "</h2><hr><h4>thanks for reading this email and " +
                    "<a href='http://" + "localhost" + ":8080/page/active.html?username=" + username +
                    "&activecode=" + activeCode + "'>click this link </a>" + "to active your account.</h4>";
            MailUtil mailUtil = new MailUtil();
            try {
                mailUtil.sendMail(email, "Active Account", msg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return ServerResponse.createResponse(SUCCESS,
                "邮件已经发送请检查邮箱激活!");
    }

    @Override
    public String activeAccount(String username, String activeCode) {
        String cacheCode = (String) SimpleCache.getAttribute(username);
        if (cacheCode.equals(activeCode)) {
            userDao.updateActive(username, true);
            SimpleCache.remove(username);
        }
        return "/page/index.html";
    }

    @Override
    public ServerResponse getUserInfo(User user) {
        return ServerResponse.createResponse(SUCCESS, user);
    }

    @Override
    public ServerResponse getReservation(User user) {
        int userId = user.getId();
        List<Notice> notices = noticeDao.selectByUserId(userId);
        return ServerResponse.createResponse(SUCCESS, notices);
    }

    @Override
    public ServerResponse getNotice() {
        List<Notice> notices = noticeDao.selectAll();
        return ServerResponse.createResponse(SUCCESS, notices);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ServerResponse reserve(User user, int noticeId) {
        int userId = user.getId();
        boolean isReserved = noticeDao.selectReserved(noticeId);
        if (isReserved)
            return ServerResponse.createResponse(ERROR, "已借出，借阅失败！");
        try {
            noticeDao.updateReserved(noticeId, true);
            Group group = new Group(userId, noticeId);
            groupDao.insert(group);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//rollback for update();
            return ServerResponse.createResponse(ERROR, "借阅失败，请重试！");
        }
        return ServerResponse.createResponse(SUCCESS, "借阅成功！");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ServerResponse logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        try {
            List<Notice> notices=noticeDao.selectByUserId(userId);
            for (Notice item:notices){
                int noticeId=item.getId();
                noticeDao.updateReserved(noticeId, false);
            }
            groupDao.deleteByUserId(userId);
            userDao.delete(userId);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServerResponse.createResponse(ERROR, "登出失败，请重试！");
        }
        session.invalidate();
        return ServerResponse.createResponse(SUCCESS, "登出成功！");
    }

    @Override
    public ServerResponse update(User user, String oldPassword, String newPassword) {
        String password = user.getPassword();
        if (!password.equals(CodeUtil.encrypt(oldPassword)))
            return ServerResponse.createResponse(ERROR, "密码错误！");
        user.setPassword(CodeUtil.encrypt(newPassword));
        userDao.update(user);
        return ServerResponse.createResponse(SUCCESS, "更改密码成功！");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ServerResponse cancelReservation(User user, int noticeId) {
        int userId = user.getId();
        Group group = groupDao.select(userId, noticeId);
        if (group == null)
            return ServerResponse.createResponse(ERROR, "退还失败！");
        try {
            groupDao.delete(noticeId);
            noticeDao.updateReserved(noticeId, false);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServerResponse.createResponse(ERROR, "归还失败！");
        }
        return ServerResponse.createResponse(SUCCESS, "归还成功！");
    }
}
