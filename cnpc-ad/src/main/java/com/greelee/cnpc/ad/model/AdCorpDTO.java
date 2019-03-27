package com.greelee.cnpc.ad.model;

import lombok.*;

/**
 * @author: gl
 * @Email: 110.com
 * @version: 1.0
 * @Date: 2019/3/25
 * @describe: AD域请求 API 获取的单位对象模型
 */

@Builder
@Data
public class AdCorpDTO {
    /**
     * 单位id
     */
    private Integer CorpID;
    /**
     * 单位名
     */
    private String CorpName;
    /**
     * 父级单位 ID
     */
    private Integer ParentID;
    /**
     * 序号
     */
    private Integer xh;
    /**
     * 单位负责人
     */
    private String CorpManager;
}
