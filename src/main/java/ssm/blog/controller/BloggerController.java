package ssm.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ssm.blog.entity.Blogger;
import ssm.blog.service.BloggerService;
import ssm.blog.util.CryptographyUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 博主Controller层，前台部分，不需要认证
 * Created by liuxiwen on 2017/1/17.
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

    @Resource(name = "bloggerService")
    private BloggerService bloggerService;

    @RequestMapping("/login.do")
    public String login(Blogger blogger, HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();// 获取当前登录的主体
        String newPassword = CryptographyUtil.md5(blogger.getPassword(), "javacoder");// 将密码使用md5加密
        // 将用户信息封装到token中
        UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUsername(), newPassword);
        try {
            subject.login(token);// 会调用MyRealm中的doGetAuthenticationInfo方法进行身份认证
            return "redirect:/admin/main.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("blogger", blogger);
            request.setAttribute("errorInfo", "用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/aboutme.do")
    public ModelAndView aboutMe() {

        ModelAndView modelAndView = new ModelAndView();
        Blogger blogger = bloggerService.getBloggerData();
        modelAndView.addObject("blogger", blogger);
        modelAndView.addObject("commonPage", "foregroup/blogger/bloggerInfo.jsp");
        modelAndView.addObject("title", "关于博主 - 刘锡文的博客");
        modelAndView.setViewName("mainTemp");
        return modelAndView;
    }
}
