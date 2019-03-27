package com.greelee.cnpc.ad.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.greelee.cnpc.ad.manager.AdRequest;
import com.greelee.cnpc.ad.model.AdCorpListDTO;
import com.greelee.cnpc.ad.model.AdUserDTO;
import com.greelee.cnpc.ad.model.AdUserListDTO;
import com.greelee.cnpc.ad.properties.AdLoginProperties;
import com.greelee.gllog.manager.ApiLogRequest;
import com.greelee.gllog.manager.ExceptionLogRequest;
import gl.tool.component.exception.ServiceException;
import gl.tool.util.container.ContainerUtil;
import gl.tool.util.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.client.config.RequestConfig;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.greelee.cnpc.ad.constant.AdApiEnum.*;

/**
 * @author sqm
 * @version 1.0
 * @date 2019/02/14
 * @description: 类描述: AD域请求 API 类,通用处理层
 **/
@Slf4j
@Component
public class AdRequestImpl implements AdRequest {

    public static void main(String[] args) throws Exception {
//         String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"*\">{Rows:[{UserID:450,UserName:\"刘健\",CorpID:21,Headship:\"计量自控技术员\",LevelID:6,OfficeTel:\"\",Mobile:\"13890003775\",Email:\"liu.jian@petrochina.com.cn\",Enabled:true,Memo:\"\",password:\"\",isadmin:false,level:\"一般员工\",workTime:\"2003-08-01 00:00:00\",Sex:\"女\",Updatetime:\"\",stationID:\"\",stationName:\"\",WeUserID:\"sqglc_jlz05\",isWeixin:\"1\",isSms:\"1\",isChange:\"0\",UserNo:\"444604\",XH:381,corpname:\"计量监督维护管理站\",timer:\"3600\",token:\"hfNsi890QNo4WVQVwrtzTtUNEgR\\/Bs0SgEuLe7ahmNU=\",isManage:\"0\",manageCorp:\"0\"}],Total:1}</string>";
// //        System.out.println(parseLoginSuccessXml(xml));
// //        System.out.println(parseUpdateUserXml(xml));
////         System.out.println(xml);
//
//         Document document = DocumentHelper.parseText(xml);
//         Element element = document.getRootElement();
//         String text = element.getText();
//         System.out.println("text:"+text);
//         JSONObject jsonObject = JSON.parseObject(text);
//         JSONArray rows = jsonObject.getJSONArray("Rows");
//         if (null != rows && rows.size() == 1) {
//             JSONObject row = rows.getJSONObject(0);
//         }
//         System.out.println("row:"+JSON.toJSONString(jsonObject));

        System.out.println(URLEncoder.encode("", "utf-8"));
    }

    /**
     * AD 域登录 api 响应成功解析字符串
     * {
     * "Total":1,
     * "Rows":[
     * {
     * "UserID":450,
     * "UserName":"刘健",
     * "CorpID":21,
     * "Headship":"计量自控技术员",
     * "LevelID":6,
     * "OfficeTel":"",
     * "Mobile":"13890003775",
     * "Email":"liu.jian@petrochina.com.cn",
     * "Enabled":true,
     * "Memo":"",
     * "password":"",
     * "isadmin":false,
     * "level":"一般员工",
     * "workTime":"2003-08-01 00:00:00",
     * "Sex":"女",
     * "Updatetime":"",
     * "stationID":""
     * "stationName":"",
     * "WeUserID":"sqglc_jlz05",
     * "isWeixin":"1",
     * "isSms":"1",
     * "isChange":"0",
     * "UserNo":"444604",
     * "XH":381,
     * "corpname":"计量监督维护管理站",
     * "manageCorp":"0",
     * "timer":"3600",
     * "isManage":"0",
     * "token":"hfNsi890QNo4WVQVwrtzTtUNEgR/Bs0SgEuLe7ahmNU=",
     * }
     * ]
     * }
     */

    private ApiLogRequest apiLogRequest;

    private HttpServletRequest httpServletRequest;

    private AdLoginProperties adLoginProperties;

    private ExceptionLogRequest exceptionLogRequest;

    private RequestConfig requestConfig;

    @Autowired
    public AdRequestImpl(ApiLogRequest apiLogRequest, HttpServletRequest httpServletRequest, AdLoginProperties adLoginProperties, ExceptionLogRequest exceptionLogRequest) {
        this.apiLogRequest = apiLogRequest;
        this.httpServletRequest = httpServletRequest;
        this.adLoginProperties = adLoginProperties;
        this.exceptionLogRequest = exceptionLogRequest;
        this.requestConfig = requestConfig();
    }

    /**
     * 请求 API 设置
     */
    private RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(adLoginProperties.getRequestApiTimeout())
                .setSocketTimeout(adLoginProperties.getRequestApiTimeout())
                .setConnectionRequestTimeout(1000)
                .build();
    }

    /**
     * 验证用户名与密码
     * @param account 用户名
     * @param pass    密码
     */
    @Override
    public AdUserDTO login(String account, String pass) throws ServiceException {
        /**
         * 对入参进行一次 URLencoding 编码
         */
        try {
            account = Objects.isNull(account) ? "" : URLEncoder.encode(account, "utf-8");
            pass = Objects.isNull(pass) ? "" : URLEncoder.encode(pass, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String> paramMap = new HashMap<>(4);
        paramMap.put(LOGIN_API.getParamKeys()[0], account);
        paramMap.put(LOGIN_API.getParamKeys()[1], pass);
        String result;
        try {
            result = HttpUtil.sendPost(LOGIN_API.getUrl(), paramMap, requestConfig);
            /**
             * 保存日志
             */
            LOGIN_API.saveApiLog(apiLogRequest, paramMap, result);
        } catch (IOException e) {
            exceptionLogRequest.saveExceptionLog(httpServletRequest,"AD 域登录接口请求异常", e);
            return null;
        }
        if (Objects.nonNull(result)) {
            String text = parseJsonStrFromAdResult(result);
            if (Objects.nonNull(text)) {
                AdUserListDTO adUserListDTO = JSON.parseObject(text, AdUserListDTO.class);
                if (Objects.nonNull(adUserListDTO)) {
                    List<AdUserDTO> adUserDTOList = adUserListDTO.getRows();
                    if (ContainerUtil.isNotEmpty(adUserDTOList) && adUserDTOList.size() == 1) {
                        return adUserDTOList.get(0);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 分页获取用户列表:默认分页容量
     */
    public static final int DEFAULT_PAGE_SIZE = 500;
    /**
     * 分页获取用户列表:默认排序字段
     */
    public static final String DEFAULT_ORDER_FIELD = "UserID";
    /**
     * 分页获取用户列表:顺序排序
     */
    public static final String ORDER_TYPE_ASC = "1";
    /**
     * 分页获取用户列表:倒序排序
     */
    public static final String ORDER_TYPE_DESC = "2";

    /**
     * 分页获取用户列表
     *
     * @return 只要返回 null 就是请求失败
     */
    @Override
    public AdUserListDTO importAdUser(int pageIndex) throws ServiceException {
        return importAdUser(DEFAULT_PAGE_SIZE, pageIndex, DEFAULT_ORDER_FIELD, ORDER_TYPE_ASC);
    }

    /**
     * 分页获取用户列表
     *
     * @param pageSize   分页容量
     * @param pageIndex  分页页码
     * @param orderField 排序字段 默认UserID
     * @param orderType  排序类型 1.顺序;2.倒序
     * @return 只要返回 null 就是请求失败
     */
    @Override
    public AdUserListDTO importAdUser(int pageSize, int pageIndex, String orderField, String orderType) throws ServiceException {
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put(LIST_USER_API.getParamKeys()[0], String.valueOf(pageSize));
        paramMap.put(LIST_USER_API.getParamKeys()[1], String.valueOf(pageIndex));
        paramMap.put(LIST_USER_API.getParamKeys()[2], orderField);
        paramMap.put(LIST_USER_API.getParamKeys()[3], orderType);
        paramMap.put(LIST_USER_API.getParamKeys()[4], adLoginProperties.getToken());
        String result;
        try {
            result = HttpUtil.sendPost(LIST_USER_API.getUrl(), paramMap, requestConfig);
            /**
             * 保存日志
             */
            LIST_USER_API.saveApiLog(apiLogRequest, paramMap, result);
        } catch (IOException e) {
            exceptionLogRequest.saveExceptionLog(httpServletRequest,"AD 域获取分页用户列表请求异常", e);
            return null;
        }
        if (Objects.nonNull(result)) {
            String text = parseJsonStrFromAdResult(result);
            if (Objects.nonNull(text)) {
                return JSON.parseObject(text, AdUserListDTO.class);
            }
        }
        return null;
    }

    /**
     * 获取全部单位列表
     * @return 单位列表信息 ,只要为 null 则获取失败
     */
    @Override
    public AdCorpListDTO importAdCorp() throws ServiceException {
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put(LIST_TOTAL_CORP_API.getParamKeys()[0], adLoginProperties.getToken());
        String result;
        try {
            result = HttpUtil.sendPost(LIST_TOTAL_CORP_API.getUrl(), paramMap, requestConfig);
            /**
             * 保存日志
             */
            LIST_TOTAL_CORP_API.saveApiLog(apiLogRequest, paramMap, result);
        } catch (IOException e) {
            exceptionLogRequest.saveExceptionLog(httpServletRequest,"AD 域获取全部单位请求异常", e);
            return null;
        }
        if (Objects.nonNull(result)) {
            String text = parseJsonStrFromAdResult(result);
            if (Objects.nonNull(text)) {
                return JSON.parseObject(text, AdCorpListDTO.class);
            }
        }
        return null;
    }


    /**
     * 解析 Ad 域相关 API 的 xml 响应字符串为 json 字符串
     *
     * @param adResult
     * @return
     */
    private String parseJsonStrFromAdResult(String adResult) throws ServiceException {
        try {
            Document document = DocumentHelper.parseText(adResult);
            Element element = document.getRootElement();
            return element.getText();
        } catch (DocumentException e) {
            exceptionLogRequest.saveExceptionLog(httpServletRequest,"AD 域请求异常,AD 请求响应信息为:" + adResult, e);
            return null;
        }
    }
}
