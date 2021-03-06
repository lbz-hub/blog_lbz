package com.blog.blog_lbz.controller;

import com.blog.blog_lbz.entity.Article.Article;
import com.blog.blog_lbz.entity.Classify.Classify;
import com.blog.blog_lbz.entity.User.User;
import com.blog.blog_lbz.service.ArticleService;
import com.blog.blog_lbz.service.ClassifyService;
import com.blog.blog_lbz.utils.RespCode;
import com.blog.blog_lbz.utils.RespEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String search(String search, Integer page, Model model, HttpServletRequest request) {
        PageInfo<Article> pageInfo = articleService.search(search, page, 3);
        model.addAttribute("pageInfo_search", pageInfo);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("users");
        if (user == null) {
            return "article/search_user";
        }
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
     * @param aid
     * @param username
     * @param atitle
     * @param cname
     * @param atext
     * @return
     */
    @RequestMapping(value = "/article/edit", method = RequestMethod.POST)
    @ResponseBody
//    public String edit(Article article, Model model) {
//        articleService.edit(article);
//        model.addAttribute("msg_article_edit", "文章修改成功");
//
//        Article articled = articleService.look(article.getAtitle());
//        model.addAttribute("article_edit", articled);
//        List<Classify> clist = classifyService.all();
//        model.addAttribute("clist_all", clist);
//        return "article/edit";
//    }
    public RespEntity edit(@RequestParam("aid")Integer aid, @RequestParam("username")String username, @RequestParam("atitle")String atitle,
                           @RequestParam("cname")String cname, @RequestParam("atext")String atext) {
        Article article = new Article();
        article.setAid(aid);
        article.setUsername(username);
        article.setAtitle(atitle);
        article.setCname(cname);
        article.setAtext(atext);
        articleService.edit(article);
        RespEntity respEntity = new RespEntity(RespCode.SUCCESS);
        return respEntity;
    }

    /**
     * 分类查询
     * @param cname
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/article/classify")
    public String classifycheck(String cname, Integer page, Model model) {
        PageInfo<Article> pageInfo = articleService.classifycheck(cname, page, 3);
        model.addAttribute("article_classify", pageInfo);
        return "article/classify";
    }
}
