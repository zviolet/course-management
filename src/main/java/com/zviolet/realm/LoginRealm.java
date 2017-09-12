package com.zviolet.realm;

import com.zviolet.entity.Role;
import com.zviolet.entity.Userlogin;
import com.zviolet.service.RoleService;
import com.zviolet.service.UserloginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zviolet on 2017/9/12.
 */

@Component
public class LoginRealm extends AuthorizingRealm {

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    //进行授权时，Shiro会自动调用该方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) getAvailablePrincipal(principals);
        Role role = null;

        try {
            Userlogin userlogin = userloginService.findByName(username);
            //获取用户所属角色
            role = roleService.findById(userlogin.getRoleId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过用户名从数据库获取 权限/角色 信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> r = new HashSet<String>();
        if (role != null) {
            r.add(role.getRoleName());
            info.setRoles(r);
        }
        return info;
    }

    /*
     * 进行身份验证时，Shiro会自动调用该方法。
     * 若提交的 身份/凭证 与数据库中取出的 身份/凭证 相匹配，则身份验证通过，否则
     * 身份验证失败。
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获得提交的用户名
        String username = (String) token.getPrincipal();
        //获得提交的密码
        String password = new String((char[]) token.getCredentials());

        Userlogin userlogin = null;
        try {
            userlogin = userloginService.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userlogin == null) {
            //该用户名不存在
            throw new UnknownAccountException();
        } else if (!password.equals(userlogin.getPassword())) {
            //密码错误
            throw new IncorrectCredentialsException();
        }
        //身份验证通过,返回一个身份信息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(username, password, getName());
        return aInfo;
    }
}
