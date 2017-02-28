package ssm.blog.service;

import ssm.blog.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * Created by liuxiwen on 2017/1/16.
 */
public interface CommentService {

    // 获取评论信息
    List<Comment> getCommentData(Map<String, Object> map);

    // 添加评论
    Integer addComment(Comment comment);

    // 获取总记录数
    Long getTotal(Map<String, Object> map);

    // 修改评论信息
    Integer update(Comment comment);

    // 删除评论信息
    Integer deleteComment(Integer id);

    // 根据博客id删除评论信息，用于删除谋篇博客前，先删除该博客的评论，因为有外键
    Integer deleteCommentByBlogId(Integer blogId);
}
