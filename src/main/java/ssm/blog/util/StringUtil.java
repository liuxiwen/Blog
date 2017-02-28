package ssm.blog.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 * Created by liuxiwen on 2017/1/16.
 */
public class StringUtil {

    /**
     * 判断是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {

        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {

        if (str != null && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 格式化模糊查询
     * @param str
     * @return
     */
    public static String formatLike(String str) {

        if (isNotEmpty(str)) {
            return "%" + str + "%";
        } else {
            return null;
        }
    }

    /**
     * 过滤掉集合中的空白项
     * @param list
     * @return
     */
    public static List<String> filterWhite(List<String> list) {

        List<String> resultList = new ArrayList<String>();
        for (String str : list) {
            if (isNotEmpty(str)) {
                resultList.add(str);
            }
        }
        return resultList;
    }
}
