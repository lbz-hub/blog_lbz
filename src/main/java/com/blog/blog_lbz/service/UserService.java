package com.blog.blog_lbz.service;

import com.blog.blog_lbz.entity.Article.Article;
import com.blog.blog_lbz.entity.User.User;

import java.util.List;

/**
 * 用户业务层接口
 */
public interface UserService {
    User login(User user);
    void save(User user);
    List<Article> findByUid(Integer uid);
    void changeImg(User user);
}
