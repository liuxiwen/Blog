package ssm.blog.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by liuxiwen on 2017/1/16.
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object object) throws Exception {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(object.toString());
        out.flush();
        out.close();
    }
}
