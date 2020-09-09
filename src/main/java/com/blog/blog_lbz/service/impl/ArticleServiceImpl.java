package com.blog.blog_lbz.service.impl;

import com.blog.blog_lbz.dao.ArticleDao;
import com.blog.blog_lbz.dao.ClassifyDao;
import com.blog.blog_lbz.entity.Article.Article;
import com.blog.blog_lbz.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 文章业务层实现
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    public ArticleServiceImpl() {
        super();
    }

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void add(Article article) {
        Date atime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        String str = sdf.format(atime);
        article.setAtime(str);
        article.setStatus("1");
        articleDao.add(article);
    }

    @Override
    public void delete(Integer aid) {
        articleDao.delete(aid);
    }

    @Override
    public void edit(Article article) {
        article.setStatus("1");
        Date atime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(atime);
        article.setAtime(str);
        articleDao.edit(article);
    }

    @Override
    public Article check(String atitle) {
        Article article = articleDao.check(atitle);
        return article;
    }

    @Override
    public Article checkByAid(Integer aid) {
        Article article = articleDao.checkByAid(aid);
        return article;
    }

    @Override
    public PageInfo<Article> all(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Article> list = articleDao.all();
        PageInfo<Article> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Article> search(String search, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Article> list = articleDao.search(search);
        PageInfo<Article> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Article look(String atitle) {
        return articleDao.look(atitle);
    }

    @Override
    public PageInfo<Article> classifycheck(String cname, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Article> list = articleDao.classifycheck(cname);
        PageInfo<Article> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
