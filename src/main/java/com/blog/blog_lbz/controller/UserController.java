package com.blog.blog_lbz.controller;

import com.blog.blog_lbz.entity.Article.Article;
import com.blog.blog_lbz.entity.Classify.Classify;
import com.blog.blog_lbz.entity.User.User;
import com.blog.blog_lbz.entity.User.UserStatus;
import com.blog.blog_lbz.service.ArticleService;
import com.blog.blog_lbz.service.ClassifyService;
import com.blog.blog_lbz.service.UserService;
import com.blog.blog_lbz.utils.MD5Utils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 主页控制器
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ClassifyService classifyService;

    /**
     * 主页面
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("/tohome")
    public String index(Model model, Integer page, HttpServletRequest request) {
        List<Classify> clist = classifyService.all();
        PageInfo<Article> pageInfo = articleService.all(page, 3);
        model.addAttribute("pageInfo_all", pageInfo);
        model.addAttribute("clist_all", clist);
        HttpSession session = request.getSession();
        User users = (User) session.getAttribute("users");
        if (users != null) {
            return "user/home";
        }
        return "user/home_user";
    }

    @RequestMapping("/user/tologin")
    public String tologin() {
        return "user/login";
    }

    /**
     * 用户登录
     * @param user
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/user/login")
    public String login(User user, Model model, HttpSession session) {
        User users = userService.login(user);
        if (users == null) {
            String msg_error = "用户不存在";
            model.addAttribute("msg_error", msg_error);
            return "user/regist";
        }

        if (MD5Utils.getSaltverifyMD5(user.getPassword(), users.getPassword())) {
            String msg_success = "登录成功！";
            model.addAttribute("msg_success", msg_success);
            session.setAttribute("users", users);
            PageInfo<Article> pageInfo = articleService.all(1, 3);
            List<Classify> clist = classifyService.all();
            model.addAttribute("pageInfo_all", pageInfo);
            model.addAttribute("clist_all", clist);
            return "user/home";
        }
        System.out.println("====" + MD5Utils.getSaltverifyMD5(user.getPassword(), users.getPassword()));
        model.addAttribute("msg_error_login", "用户名或密码错误");
        return "user/login";
    }

    @RequestMapping("/user/toRegist")
    public String toregist() {
        return "user/regist";
    }

    /**
     * 用户注册
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/user/regist")
    public String regist(User user, MultipartFile filepath, Model model) throws IOException {
        user.setStatus(UserStatus.NORMAL);
        String realPath = "E:\\JavaSE_workspace\\blog_lbz\\src\\main\\resources\\static\\image\\brand";
        File filePath = new File(realPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        String oFileName = filepath.getOriginalFilename();
        String suffix = oFileName.substring(oFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + suffix;
        File targetFile = new File(filePath, newFileName);
        filepath.transferTo(targetFile);
        String ulogoPath = "/image/brand/" + newFileName;
        user.setUlogo(ulogoPath);
        String pass = MD5Utils.hash(user.getPassword());
        user.setPassword(pass);
        userService.save(user);
        String msg_regist = "注册成功！";
        model.addAttribute("msg_regist", msg_regist);
        return "user/login";
    }

    /**
     * 用户登出
     * @param session
     * @param sessionStatus
     * @param model
     * @return
     */
    @RequestMapping("/user/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus, Model model) {
        session.invalidate();
        sessionStatus.setComplete();
        PageInfo<Article> pageInfo = articleService.all(1, 3);
        model.addAttribute("pageInfo_all", pageInfo);
        return "user/home";
    }

    /**
     * 我的文章
     * @param uid
     * @param model
     * @return
     */
    @RequestMapping("/user/myarticle")
    public String myarticle(Integer uid, Model model) {
        List<Article> list = userService.findByUid(uid);
        System.out.println(list.toString());
        model.addAttribute("list", list);
        return "user/myarticle";
    }

    /**
     * 跳转头像更换页面
     * @return
     */
    @RequestMapping("/user/tochangeimg")
    public String tochangeimg() {
        return "user/changeimg";
    }

    /**
     * 修改头像
     * @param request
     * @param userlogo
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("/user/changeImg")
    public String changeimg(HttpServletRequest request, MultipartFile userlogo, Model model) throws IOException {
        String realPath = "E:\\JavaSE_workspace\\blog_lbz\\src\\main\\resources\\static\\image\\brand";
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String oFileName = userlogo.getOriginalFilename();
        String suffix = oFileName.substring(oFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + suffix;
        File targetFile = new File(file, newFileName);
        userlogo.transferTo(targetFile);
        String ulogoPath = "/image/brand/" + newFileName;
        HttpSession session = request.getSession();
        User exUser = (User) session.getAttribute("users");
//        System.out.println(exUser);
        exUser.setUlogo(ulogoPath);
        userService.changeImg(exUser);
        model.addAttribute("changeimg", "修改头像成功");
        return "user/changeimg";
    }
}
