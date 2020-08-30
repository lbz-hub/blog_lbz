package com.blog.blog_lbz.entity.User;

import com.blog.blog_lbz.entity.Article.Article;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户
 */
@Data
public class User implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String email;
    private List<Article> alist;
    private UserStatus status = UserStatus.NORMAL;
}
