package ssm.blog.service.impl;

import org.springframework.stereotype.Service;
import ssm.blog.dao.BloggerDao;
import ssm.blog.entity.Blogger;
import ssm.blog.service.BloggerService;

import javax.annotation.Resource;

/**
 * Created by liuxiwen on 2017/1/16.
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {

    @Resource
    private BloggerDao bloggerDao;

    @Override
    public Blogger getByUsername(String username) {
        return bloggerDao.getByUsername(username);
    }

    @Override
    public Blogger getBloggerData() {
        return bloggerDao.getBloggerData();
    }

    @Override
    public Integer updateBlogger(Blogger blogger) {
        return bloggerDao.updateBlogger(blogger);
    }
}
