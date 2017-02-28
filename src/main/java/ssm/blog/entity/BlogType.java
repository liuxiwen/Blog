package ssm.blog.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by liuxiwen on 2017/1/15.
 */
public class BlogType implements Serializable {

    private Integer id;
    private String typeName;
    private String orderNum;
    private Integer blogCount;// 统计不同类型的博客数量

    public BlogType() {

    }

    public BlogType(Integer id, String typeName, String orderNum) {
        this.id = id;
        this.typeName = typeName;
        this.orderNum = orderNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    @Override
    public String toString() {
        return "BlogType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", blogCount=" + blogCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogType blogType = (BlogType) o;
        return Objects.equals(id, blogType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
