package ssm.blog.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * md5加密工具类
 * Created by liuxiwen on 2017/1/16.
 */
public class CryptographyUtil {

    /**
     * 使用Shiro中的md5加密
     * @param str
     * @param salt
     * @return
     */
    public static String md5(String str, String salt) {

        // Md5Hash是Shiro中的一个方法
        return new Md5Hash(str, salt).toString();
    }

    public static void main(String[] args) {

        String password = "123456";
        System.out.println("Md5加密：" + CryptographyUtil.md5(password, "javacoder"));
    }
}
