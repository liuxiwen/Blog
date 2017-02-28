package ssm.blog.entity;

import java.io.Serializable;

/**
 * Created by liuxiwen on 2017/1/15.
 */
public class PageBean implements Serializable {

    private int page;// 第几页
    private int pageSize;// 每页记录数
    private int start;// 起始页

    public PageBean() {

    }

    public PageBean(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        start = (page - 1) * pageSize;
        return start;
    }

}
