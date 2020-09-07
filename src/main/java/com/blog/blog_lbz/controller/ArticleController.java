package com.blog.blog_lbz.controller;

import com.blog.blog_lbz.entity.Article.Article;
import com.blog.blog_lbz.entity.Classify.Classify;
import com.blog.blog_lbz.service.ArticleService;
import com.blog.blog_lbz.service.ClassifyService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * 问孩子那个控制器
 */
@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ClassifyService classifyService;

    @RequestMapping("/article/toadd")
    public String toadd(Model model) {
        List<Classify> clist = classifyService.all();
        model.addAttribute("clist_all", clist);
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

    /**
     * 查看文章
     * @param atitle
     * @param model
     * @return
     */
    @RequestMapping("/article/looka")
    public String look(String atitle, Model model) {
        Article article = articleService.look(atitle);
//        System.out.println(article);
        model.addAttribute("article_look", article);
        return "article/look";
    }

    @RequestMapping("/article/toedit")
    public String toedit(String atitle, Model model) {
        Article article = articleService.look(atitle);
        model.addAttribute("article_edit", article);
        List<Classify> clist = classifyService.all();
        model.addAttribute("clist_all", clist);
        return "article/edit";
    }

    /**
     * 文章修改
     * @param article
     * @param model
     * @return
     */
    @RequestMapping("/article/edit")
    public String edit(Article article, Model model) {
        articleService.edit(article);
        model.addAttribute("msg_article_edit", "文章修改成功");

        Article articled = articleService.look(article.getAtitle());
        model.addAttribute("article_edit", articled);
        List<Classify> clist = classifyService.all();
        model.addAttribute("clist_all", clist);
        return "article/edit";
    }
}
