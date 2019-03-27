package com.greelee.cnpc.ad.dao;

import com.greelee.cnpc.ad.model.AdUserDO;
import gl.tool.component.mvc.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: gl
 * @Email: 110.com
 * @version: 1.0
 * @Date: 2019/3/27
 * @describe:
 */
@Repository
public interface AdUserDao extends BaseDao<AdUserDO> {
    /**
     * 判断是否存在
     * @param adUserId
     * @return
     */
    Boolean isExistByAdUserId(@Param("adUserId") Integer adUserId);

    /**
     * 判断是否存在
     * @param account
     * @return
     */
    Boolean isExistByAccount(@Param("account") String account);
}
