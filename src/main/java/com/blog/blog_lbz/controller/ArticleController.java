package com.blog.blog_lbz.controller;

import com.blog.blog_lbz.entity.Article.Article;
import com.blog.blog_lbz.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 问孩子那个控制器
 */
@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/article/toadd")
    public String toadd() {
        return "article/add";
    }

    /**
     * 文章添加
     * @param article
     * @param model
     * @return
     */
    @RequestMapping("/article/add")
    public String add(Article article, Model model) {
        Date atime = new Date();
        article.setAtime(atime);
        articleService.add(article);
        String msg_addSuccess = "文章添加成功！";
        model.addAttribute("msg_addSuccess", msg_addSuccess);
        return "article/add";
    }

    /**
     * 文章查询
     * @param search
     * @param model
     * @return
     */
    @RequestMapping("/article/search")
    public String search(String search, Integer page, Model model) {
        PageInfo<Article> pageInfo = articleService.search(search, page, 3);
        model.addAttribute("pageInfo_search", pageInfo);
        return "article/search";
    }
}
