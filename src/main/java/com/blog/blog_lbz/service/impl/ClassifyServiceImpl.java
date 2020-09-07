package com.blog.blog_lbz.service.impl;

import com.blog.blog_lbz.dao.ClassifyDao;
import com.blog.blog_lbz.entity.Classify.Classify;
import com.blog.blog_lbz.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章业务层实现
 */
@Service
public class ClassifyServiceImpl implements ClassifyService {
    @Autowired
    private ClassifyDao classifyDao;

    @Override
    public List<Classify> all() {
        return classifyDao.all();
    }
}
