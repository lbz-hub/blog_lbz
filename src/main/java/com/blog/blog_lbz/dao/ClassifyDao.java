package com.blog.blog_lbz.dao;

import com.blog.blog_lbz.entity.Classify.Classify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章持久层接口
 */
@Mapper
public interface ClassifyDao {
    List<Classify> all();
}
