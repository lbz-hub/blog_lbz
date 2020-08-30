package com.blog.blog_lbz.entity.Article;

import com.blog.blog_lbz.entity.User.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 */
@Data
public class Article implements Serializable {
    private Integer aid;
    private String atitle;
    private String atext;
    private Date atime;
    private String status;
    private User user;

}
