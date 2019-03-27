package com.greelee.cnpc.ad.model;

import lombok.*;

/**
 * @author: gl
 * @Email: 110.com
 * @version: 1.0
 * @Date: 2019/3/25
 * @describe:  中石油西南分公司用户字段
 */
@Data
@Builder
@AllArgsConstructor
public class AdUserDTO {
    /**
     * 登录名(唯一)
     */
    private String account;
    /**
     * 用户id (UNIQUE)
     */
    private Integer UserID;
    /**
     * 用户名
     */
    private String UserName;
    /**
     * 单位id
     */
    private Integer CorpID;
    /**
     * 职务
     */
    private String Headship;
    /**
     * 头衔 ID
     */
    private String LevelID;
    /**
     * 电话
     */
    private String OfficeTel;
    /**
     * 手机
     */
    private String Mobile;
    /**
     * 邮箱 (UNIQUE)
     */
    private String Email;
    /**
     * 是否启用:1.启用;2.停用
     */
    private Boolean Enabled;
    /**
     * 备注
     */
    private String Memo;
    private String password;
    /**
     * 是否是超级管理员:1.是;0.否
     */
    private Boolean isadmin;
    /**
     * 级别:处级\副处级\科级\副科级\股级\一般员工
     */
    private String level;
    /**
     * 参工时间
     */
    private String workTime;
    /**
     * 性别
     */
    private String Sex;
    private String Updatetime;
    private String stationID;
    private String stationName;
    private String WeUserID;
    private String isWeixin;
    private String isSms;
    private String isChange;
    private String UserNo;
    /**
     * 序号
     */
    private Integer XH;
    /**
     * 单位名
     */
    private String CorpName;
    private String manageCorp;
    private String timer;
    private String isManage;
    private String token;
    /**
     * 分页总页数
     */
    private Integer Pages;
    /**
     * 索引值
     */
    private String indexs;
    private Integer ids1;
}
