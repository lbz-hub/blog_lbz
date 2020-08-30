package com.blog.blog_lbz.service;

import com.blog.blog_lbz.entity.Article.Article;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 文章业务层接口
 */
public interface ArticleService {
    void add(Article article);
    void delete(Integer aid);
    void edit(Article article);
    Article check(String atitle);
    PageInfo<Article> all(Integer page, Integer pageSize);
    PageInfo<Article> search(String search, Integer page, Integer pageSize);
    Article checkByAid(Integer aid);
}
