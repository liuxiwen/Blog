package ssm.blog.dao;

import ssm.blog.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxiwen on 2017/1/15.
 */
public interface BlogDao {

    // 获取博客信息，根据日期月份分组查询
    List<Blog> getBlogData();

    // 分页查询博客
    List<Blog> listBlog(Map<String, Object> map);

    // 获取总记录数
    Long getTotal(Map<String, Object> map);

    // 根据id获取博客
    Blog findById(Integer id);

    // 更新博客信息
    Integer update(Blog blog);

    // 获取上一篇博客
    Blog getPrevBlog(Integer id);

    // 获取下一篇博客
    Blog getNextBlog(Integer id);

    // 添加博客
    Integer addBlog(Blog blog);

    // 删除博客
    Integer deleteBlog(Integer id);

    // 根据博客类型的id查询该类型下的博客数量
    Integer getBlogByTypeId(Integer typeId);
}
