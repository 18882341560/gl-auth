package com.greelee.cnpc.ad.constant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.greelee.gllog.manager.ApiLogRequest;
import com.greelee.gllog.model.ApiLogDO;
import gl.tool.component.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author: gl
 * @Email: 110.com
 * @version: 1.0
 * @Date: 2019/3/25
 * @describe:
 */
@Getter
@AllArgsConstructor
public enum AdApiEnum {
    /**
     * AD 域登录验证 api
     */
    LOGIN_API("http://10.89.65.93:8010/Account/AccountCheck.asmx/userLogin", HttpMethod.POST, new String[]{"account", "pass"}, "ad 域登录验证 api"),
    /**
     * AD 域分页获取用户信息 api
     */
    LIST_USER_API("http://10.89.65.93:8010/Base/Base.asmx/getPageUsers", HttpMethod.POST, new String[]{"pageSize", "pageIndex","orderField","orderType","token"}, "AD 域分页获取用户信息 api"),
    /**
     * AD 域获取全部单位信息 api
     */
    LIST_TOTAL_CORP_API("http://10.89.65.93:8010/Base/Base.asmx/getCorps", HttpMethod.POST, new String[]{"token"}, "AD 域获取全部单位信息 api");

    /**
     * 保存 api 请求日志
     */
    public void saveApiLog(ApiLogRequest apiLogRequest, Map<String, String> paramMap, String response) throws ServiceException {
        ApiLogDO apiLogDO = ApiLogDO.builder()
                .url(this.getUrl())
                .method(this.getMethod().name())
                .description(this.getDescription())
                .params(JSON.toJSONString(paramMap, SerializerFeature.WriteMapNullValue))
                .response(response)
                .createTime(LocalDateTime.now())
                .build();
        apiLogRequest.save(apiLogDO);
    }

    /**
     * api请求地址
     */
    private final String url;
    /**
     * api 请求方法
     */
    private final HttpMethod method;
    /**
     * 请求参数Key列表
     */
    private final String[] paramKeys;
    /**
     * 请求描述
     */
    private final String description;
}
