package ssm.blog.service;

import ssm.blog.entity.Blogger;

/**
 * Created by liuxiwen on 2017/1/16.
 */
public interface BloggerService {

    // 通过用户名查找博主
    Blogger getByUsername(String username);

    // 获取博主信息
    Blogger getBloggerData();

    // 更新博主信息
    Integer updateBlogger(Blogger blogger);
}
