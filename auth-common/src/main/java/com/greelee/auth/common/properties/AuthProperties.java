package com.greelee.auth.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: gl
 * @Email: 110.com
 * @version: 1.0
 * @Date: 2019/3/23
 * @describe:
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gl.auth")
public class AuthProperties {

    /**
     * 默认管理员名称
     */
    private String adminName = "gl";

    /**
     * 默认管理员密码
     */
    private String adminPassword = "124356";

    /**
     * Jwt接收方:定为项目名
     */
    private String audience = "defaultAudience";

    /**
     * Jwt签发方:定为开发者名
     */
    private String issuer = "geLin";

    /**
     * JWT签名过期时间(毫秒)
     */
    private long jwtTimeToLive = 3600000L;

    /**
     * 开启缓存
     */
    private boolean cachingEnabled = true;

    /**
     * 开启授权缓存
     */
    private boolean authorizationCachingEnabled = true;
    /**
     * 授权缓存名称
     */
    private String authorizationCacheName = "authorizationCache";
    /**
     * 开启认证缓存
     */
    private boolean authenticationCachingEnabled = true;
    /**
     * 认证缓存名称
     */
    private String authenticationCacheName = "authenticationCache";


}
