package ssm.blog.controller.admin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssm.blog.entity.Link;
import ssm.blog.entity.PageBean;
import ssm.blog.service.LinkService;
import ssm.blog.util.ResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员友情链接Controller层
 * Created by liuxiwen on 2017/1/19.
 */
@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {

    @Resource(name = "linkService")
    private LinkService linkService;

    // 分页查询友情链接
    @RequestMapping("/linkLink.do")
    public String listLink(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            HttpServletResponse response
    ) throws Exception {

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<Link> linkList = linkService.listLinkData(map);
        Long total = linkService.getTotal(map);

        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(linkList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    // 添加或更新友情链接
    @RequestMapping("/save.do")
    public String save(Link link, HttpServletResponse response) throws Exception {

        int resultTotal = 0;// 接收返回结果记录数
        if (link.getId() == null) {// 第一次插入
            resultTotal = linkService.addLink(link);
        } else {// 有id表示更新
            resultTotal = linkService.updateLink(link);
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

    // 删除友情链接信息
    @RequestMapping("/delete.do")
    public String deleteLink(
            @RequestParam(value = "ids", required = false) String ids,
            HttpServletResponse response
    ) throws Exception {

        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            linkService.deleteLink(id);
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
}
