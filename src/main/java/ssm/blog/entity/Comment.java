package ssm.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by liuxiwen on 2017/1/15.
 */
public class Comment implements Serializable {

    private Integer id;
    private String userIp;
    private String content;
    private Date commentDate;
    private Integer state;// 审核状态，0待审核，1审核通过，2审核未通过
    private Blog blog;// 关联的博客

    public Comment() {

    }

    public Comment(Integer id, String userIp, String content, Date commentDate, Integer state, Blog blog) {
        this.id = id;
        this.userIp = userIp;
        this.content = content;
        this.commentDate = commentDate;
        this.state = state;
        this.blog = blog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userIp='" + userIp + '\'' +
                ", content='" + content + '\'' +
                ", commentDate=" + commentDate +
                ", state=" + state +
                ", blog=" + blog +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
