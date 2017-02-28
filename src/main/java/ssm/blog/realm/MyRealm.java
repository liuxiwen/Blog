package ssm.blog.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import ssm.blog.entity.Blogger;
import ssm.blog.service.BloggerService;

import javax.annotation.Resource;

/**
 * Created by liuxiwen on 2017/1/16.
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private BloggerService bloggerService;

    /**
     * 为当前登录的用户授予角色和权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 个人博客没有这一项，因为只有一个用户
        return null;
    }

    /**
     *验证当前登录用户，获取认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();// 获取用户名
        Blogger blogger = bloggerService.getByUsername(username);// 根据用户名获取博主信息

        if (blogger != null) {
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);// 把当前用户存到session中
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(blogger.getUsername(), blogger.getPassword(), "MyRealm");
            return  authenticationInfo;
        }
        return null;
    }
}
