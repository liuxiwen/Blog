package ssm.blog.controller.admin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssm.blog.entity.BlogType;
import ssm.blog.entity.PageBean;
import ssm.blog.service.BlogService;
import ssm.blog.service.BlogTypeService;
import ssm.blog.util.ResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员博客类别Controller层
 * Created by liuxiwen on 2017/1/19.
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

    @Resource(name = "blogTypeService")
    private BlogTypeService blogTypeService;

    @Resource(name = "blogService")
    private BlogService blogService;

    // 分页查询博客类别
    @RequestMapping("/listBlogType.do")
    public String listBlogType(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            HttpServletResponse response
    ) throws Exception {

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());

        List<BlogType> blogTypeList = blogTypeService.listBlogType(map);
        Long total = blogTypeService.getTotal(map);

        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(blogTypeList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    // 添加或更新博客类别
    @RequestMapping("/save.do")
    public String save(BlogType blogType, HttpServletResponse response) throws Exception {

        int resultTotal = 0;// 接收返回结果的记录数
        if (blogType.getId() == null) {// 第一次插入
            resultTotal = blogTypeService.addBlogType(blogType);
        } else {// 有id表示更新
            resultTotal = blogTypeService.updateBlogType(blogType);
        }

        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        ResponseUtil.write(response, result);
        return null;
    }

    // 删除博客类别信息
    @RequestMapping("/delete.do")
    public String deleteBlog(
            @RequestParam(value = "ids", required = false) String ids,
            HttpServletResponse response
    ) throws Exception {

        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            if (blogService.getBlogByTypeId(id) > 0) {//说明该类别中有博客
                result.put("exist", "该类别下有博客，不能删除");
            } else {
                blogTypeService.deleteBlogType(id);
            }
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
