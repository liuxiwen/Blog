package ssm.blog.dao;

import ssm.blog.entity.BlogType;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxiwen on 2017/1/15.
 */
public interface BlogTypeDao {

    // 获取博客类别信息
    List<BlogType> getBlogTypeData();

    // 根据id查找博客类型信息
    BlogType findById(Integer id);

    // 分页查询博客类别信息
    List<BlogType> listBlogType(Map<String, Object> map);

    // 获取总记录数
    Long getTotal(Map<String, Object> map);

    // 添加博客类别
    Integer addBlogType(BlogType blogType);

    // 更新博客类别
    Integer updateBlogType(BlogType blogType);

    // 删除博客类别
    Integer deleteBlogType(Integer id);
}
