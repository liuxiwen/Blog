package ssm.blog.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ssm.blog.entity.Blog;
import ssm.blog.entity.BlogType;
import ssm.blog.entity.Blogger;
import ssm.blog.entity.Link;
import ssm.blog.service.BlogService;
import ssm.blog.service.BlogTypeService;
import ssm.blog.service.BloggerService;
import ssm.blog.service.LinkService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by liuxiwen on 2017/1/16.
 */
@Component
public class InitBloggerData implements ServletContextListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println(applicationContext);
        // 获取servlet上下文
        ServletContext application = sce.getServletContext();

        // 根据Spring上下文获取bloggerService这个bean
        BloggerService bloggerService = applicationContext.getBean("bloggerService", BloggerService.class);
        // 获取博主信息
        Blogger blogger = bloggerService.getBloggerData();
        // 由于密码也获取到了，比较敏感，并且不需要此信息，所以把密码清空
        blogger.setPassword(null);
        // 将博主信息存入application域中
        application.setAttribute("blogger", blogger);

        // 根据Spring上下文获取linkService这个bean
        LinkService linkService = applicationContext.getBean("linkService", LinkService.class);
        List<Link> linkList = linkService.getLinkData();
        application.setAttribute("linkList", linkList);

        BlogTypeService blogTypeService = applicationContext.getBean("blogTypeService", BlogTypeService.class);
        List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
        application.setAttribute("blogTypeList", blogTypeList);

        BlogService blogService = applicationContext.getBean("blogService", BlogService.class);
        List<Blog> blogList = blogService.getBlogData();
        application.setAttribute("blogList", blogList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        InitBloggerData.applicationContext = applicationContext;
    }
}
