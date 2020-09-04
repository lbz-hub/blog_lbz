package com.blog.blog_lbz.entity.Classify;

import lombok.Data;

import java.io.Serializable;

/**
 * 分类
 */
@Data
public class Classify implements Serializable {
    private Integer cid;
    private String cname;
}
