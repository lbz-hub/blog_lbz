package com.blog.blog_lbz.dao;

import com.blog.blog_lbz.entity.Article.Article;
import com.blog.blog_lbz.entity.User.User;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户持久层接口
 */
@Mapper
public interface UserDao {
    User login(User user);
    void save(User user);
    List<Article> findByUid(Integer uid);
    void changeImg(User user);
}
