package com.blog.blog_lbz.service.impl;

import com.blog.blog_lbz.dao.UserDao;
import com.blog.blog_lbz.entity.Article.Article;
import com.blog.blog_lbz.entity.User.User;
import com.blog.blog_lbz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务层实现类
 */
@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl() {
        super();
    }
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public List<Article> findByUid(Integer uid) {
        return userDao.findByUid(uid);
    }

    @Override
    public void changeImg(User user) {
        userDao.changeImg(user);
    }
}
