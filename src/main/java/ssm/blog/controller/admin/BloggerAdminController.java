package ssm.blog.controller.admin;

import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ssm.blog.entity.Blogger;
import ssm.blog.service.BloggerService;
import ssm.blog.util.CryptographyUtil;
import ssm.blog.util.DateUtil;
import ssm.blog.util.ResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 管理员博主Controller层，需要身份认证
 * Created by liuxiwen on 2017/1/17.
 */
@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

    @Resource(name = "bloggerService")
    private BloggerService bloggerService;

    // 查询博主信息
    @RequestMapping("/findBlogger.do")
    public String findBlogger(HttpServletResponse response) throws Exception {

        Blogger blogger = bloggerService.getBloggerData();
        JSONObject jsonObject = JSONObject.fromObject(blogger);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    // 修改博主信息
    @RequestMapping("/save.do")
    public String save(
            @RequestParam("imageFile") MultipartFile imageFile,
            Blogger blogger,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        if (!imageFile.isEmpty()) {// 如果用户传过照片，就更新
            String filePath = request.getServletContext().getRealPath("/");// 获取服务器根路径
            String imageName = DateUtil.getCurrentDateStr() + "." + imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath + "static/userImages/" + imageName));
            blogger.setImagename(imageName);
        }

        int resultTotal = bloggerService.updateBlogger(blogger);
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    // 修改博主密码
    @RequestMapping("/modifyPassword.do")
    public String modifyPassword(
            @RequestParam("password") String password,
            HttpServletResponse response
    ) throws Exception {

        Blogger blogger = new Blogger();
        blogger.setPassword(CryptographyUtil.md5(password, "javacoder"));
        int resultTotal = bloggerService.updateBlogger(blogger);
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    // 退出
    @RequestMapping("/logout.do")
    public String logout() throws Exception {

        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}
