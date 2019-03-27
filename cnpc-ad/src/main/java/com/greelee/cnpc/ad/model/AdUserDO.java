package com.greelee.cnpc.ad.model;

import gl.tool.component.mvc.base.PageBean;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: gl
 * @Email: 110.com
 * @version: 1.0
 * @Date: 2019/3/25
 * @describe: 中石油西南分公司用户字段与本系统映射字段
 */


@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
public class AdUserDO extends PageBean implements Serializable {

    private static final long serialVersionUID = -644361937770494540L;

    public static AdUserDO transformFromAdUserDTO(AdUserDTO adUserDTO) {
        LocalDateTime now = LocalDateTime.now();
        return AdUserDO.builder()
                .account(adUserDTO.getEmail())
                .createTime(now)
                .updateTime(now)
                .adUserid(adUserDTO.getUserID())
                .adUsername(adUserDTO.getUserName())
                .adCorpId(adUserDTO.getCorpID())
                .adHeadship(adUserDTO.getHeadship())
                .adLevelId(adUserDTO.getLevelID())
                .adOfficeTel(adUserDTO.getOfficeTel())
                .adMobile(adUserDTO.getMobile())
                .adEmail(adUserDTO.getEmail())
                .adEnabled(adUserDTO.getEnabled())
                .adMemo(adUserDTO.getMemo())
                .adIsAdmin(adUserDTO.getIsadmin())
                .adLevel(adUserDTO.getLevel())
                .adWorkTime(adUserDTO.getWorkTime())
                .adSex(adUserDTO.getSex())
                .adUpdateTime(adUserDTO.getUpdatetime())
                .adStationId(adUserDTO.getStationID())
                .adStationName(adUserDTO.getStationName())
                .adWeUserId(adUserDTO.getWeUserID())
                .adIsWeixin(adUserDTO.getIsWeixin())
                .adIsSms(adUserDTO.getIsSms())
                .adIsChange(adUserDTO.getIsChange())
                .adUserNo(adUserDTO.getUserNo())
                .adXh(adUserDTO.getXH())
                .adCorpName(adUserDTO.getCorpName())
                .adManageCorp(adUserDTO.getManageCorp())
                .adTimer(adUserDTO.getTimer())
                .adIsManage(adUserDTO.getIsManage())
                .adToken(adUserDTO.getToken())
                .adIds1(adUserDTO.getIds1())
                .build();
    }
    /**
     * 自增 id (主键)
     */
    private Long id;
    /**
     * 登录名(唯一)
     */
    private String account;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    //======== { AdUserDTO 对应的字段 } =========
    /**
     * 用户id  (UNIQUE)
     */
    private Integer adUserid;
    /**
     * 用户名
     */
    private String adUsername;
    /**
     * 单位id
     */
    private Integer adCorpId;
    /**
     * 职务
     */
    private String adHeadship;
    private String adLevelId;
    /**
     * 电话
     */
    private String adOfficeTel;
    /**
     * 手机
     */
    private String adMobile;
    /**
     * 邮箱 (UNIQUE)
     */
    private String adEmail;
    /**
     * 是否启用
     */
    private Boolean adEnabled;
    /**
     * 备注
     */
    private String adMemo;
    private String adPassword;
    private Boolean adIsAdmin;
    private String adLevel;
    private String adWorkTime;
    private String adSex;
    private String adUpdateTime;
    private String adStationId;
    private String adStationName;
    private String adWeUserId;
    private String adIsWeixin;
    private String adIsSms;
    private String adIsChange;
    private String adUserNo;
    private Integer adXh;
    private String adCorpName;
    private String adManageCorp;
    private String adTimer;
    private String adIsManage;
    private String adToken;
    private Integer adPages;
    private String adIndexs;
    private Integer adIds1;
}
