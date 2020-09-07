package com.blog.blog_lbz.service;

import com.blog.blog_lbz.entity.Classify.Classify;

import java.util.List;

/**
 * 文章分类业务层
 */
public interface ClassifyService {
    List<Classify> all();
}
