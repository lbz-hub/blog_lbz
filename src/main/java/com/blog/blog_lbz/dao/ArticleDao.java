package com.blog.blog_lbz.dao;

import com.blog.blog_lbz.entity.Article.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章持久层接口
 */
@Mapper
public interface ArticleDao {
    void add(Article article);
    void delete(Integer aid);
    void edit(Article article);
    Article check(String atitle);
    List<Article> all();
    List<Article> search(String search);
    Article checkByAid(Integer aid);
}
