package ssm.blog.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by liuxiwen on 2017/1/15.
 */
public class Link implements Serializable {

    private Integer id;
    private String linkname;
    private String linkurl;
    private Integer orderNum;

    public Link() {

    }

    public Link(Integer id, String linkname, String linkurl, Integer orderNum) {
        this.id = id;
        this.linkname = linkname;
        this.linkurl = linkurl;
        this.orderNum = orderNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", linkname='" + linkname + '\'' +
                ", linkurl='" + linkurl + '\'' +
                ", orderNum=" + orderNum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(id, link.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
