package com.blog.blog_lbz;

import com.blog.blog_lbz.dao.UserDao;
import com.blog.blog_lbz.entity.Article.Article;
import com.blog.blog_lbz.service.ArticleService;
import com.blog.blog_lbz.service.UserService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BlogLbzApplicationTests {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserDao userDao;
    @Test
    void contextLoads() {


    }

    @Test
    public void findByUid() {
        List<Article> p = userDao.findByUid(2);
        System.out.println(p);
    }

}
