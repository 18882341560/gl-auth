package com.greelee.cnpc.ad.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: gl
 * @Email: 110.com
 * @version: 1.0
 * @Date: 2019/3/25
 * @describe:
 */

@Data
@Component
@ConfigurationProperties(prefix = "gl.auth.ad-login")
public class AdLoginProperties {
    /**
     * ad 域请求超时时间
     */
    private int requestApiTimeout = 3000;
    /**
     * 请求 API 的 TOKEN
     */
    private String token = "073U6470I03T";

}
