package ssm.blog.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm.blog.entity.Blog;
import ssm.blog.entity.PageBean;
import ssm.blog.service.BlogService;
import ssm.blog.util.PageUtil;
import ssm.blog.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页Controller
 * Created by liuxiwen on 2017/1/17.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Resource(name = "blogService")
    private BlogService blogService;

    // 请求主页
    @RequestMapping("/index.do")
    public ModelAndView index(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
            HttpServletRequest request
    ) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if (StringUtil.isEmpty(page)) {
            page = "1";
        }

        // 获取分页的bean
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10);// 每页显示10条数据

        // map中封装起始页和每页的记录
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr);

        // 获取博客信息
        List<Blog> blogList = blogService.listBlog(map);

        for (Blog blog : blogList) {
            List<String> imageList = blog.getImageList();
            String blogInfo = blog.getContent();// 获取博客内容
            Document document = Jsoup.parse(blogInfo);// 将博客内容（网页中也就是一些html）转为jsoup的Document
            Elements jpgs = document.select("img[src$=.jpg]");// 获取<img>标签中所有后缀是.jpg的元素
            for (int i = 0; i < jpgs.size(); i++) {
                Element jpg = jpgs.get(i);// 获取到单个元素
                imageList.add(jpg.toString());// 把图片信息存到imageList中
                if (i == 2) {
                    break;// 只存3张图片信息
                }
            }
        }

        // 分页
        StringBuffer param = new StringBuffer();
        // 拼接参数，主要对于点击文章分类或日期分类后，查出来的分页，要拼接具体参数
        if (StringUtil.isNotEmpty(typeId)) {
            param.append("typeId=" + typeId + "&");
        }
        if (StringUtil.isNotEmpty(releaseDateStr)) {
            param.append("releaseDatestr=" + releaseDateStr + "&");
        }
        modelAndView.addObject("pageCode", PageUtil.genPagination(
                request.getContextPath() + "/index.html",
                blogService.getTotal(map),
                Integer.parseInt(page),
                10,
                param.toString()));

        modelAndView.addObject("blogList", blogList);
        modelAndView.addObject("commonPage", "foreground/blog/blogList.jsp");
        modelAndView.addObject("title", "博客主页 - 刘锡文的博客");
        modelAndView.setViewName("mainTemp");

        return modelAndView;
    }
}
