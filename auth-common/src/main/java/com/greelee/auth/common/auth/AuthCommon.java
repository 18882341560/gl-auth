package com.greelee.auth.common.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.greelee.auth.common.properties.AuthProperties;
import gl.tool.component.exception.ServiceException;
import gl.tool.util.secret.JwtConst;
import gl.tool.util.secret.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

/**
 * @author: gl
 * @Email: 110.com
 * @version: 1.0
 * @Date: 2019/3/23
 * @describe:
 */
public class AuthCommon {

    private AuthProperties authProperties;

    @Autowired
    public AuthCommon(AuthProperties authProperties){
        this.authProperties = authProperties;
    }


    /**
     * shiro 登陆认证
     * @param username 账号
     * @param password 密码
     * @return token
     */
    public String getTokenByLogin(String username,String password) throws ServiceException{

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        Map<String, Object> tokenMap = JSON.parseObject(JSON.toJSONString(token), new TypeReference<Map<String, Object>>() {
        });
        /** 有效时间 */
        long effectiveMillis = authProperties.getJwtTimeToLive();
        /** 接收方 */
        String audience = authProperties.getAudience();
        /** 签发方 */
        String issuer = authProperties.getIssuer();
        return JwtUtil.createJwt(tokenMap, username, audience, issuer, effectiveMillis, JwtConst.JWT_SECRET);
    }



    /**
     * 获取主要验证信息:用户表自增 id
     */
    public Object getPrimaryPrincipal() throws ServiceException {
        Subject subject = SecurityUtils.getSubject();
        if (Objects.nonNull(subject)) {
            PrincipalCollection principalCollection = subject.getPrincipals();
            if (Objects.nonNull(principalCollection) && !principalCollection.isEmpty()) {
                return principalCollection.getPrimaryPrincipal();
            }
        }
        return null;
    }

}
