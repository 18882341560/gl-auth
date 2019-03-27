package com.greelee.cnpc.ad.manager;


import com.greelee.cnpc.ad.model.AdCorpListDTO;
import com.greelee.cnpc.ad.model.AdUserDO;
import com.greelee.cnpc.ad.model.AdUserDTO;
import com.greelee.cnpc.ad.model.AdUserListDTO;
import gl.tool.component.exception.ServiceException;
import gl.tool.component.mvc.base.BaseRequest;

/**
 * @author sqm
 * @version 1.0
 * @date 2019/03/01
 * @description: 类描述:
 **/
public interface AdRequest extends BaseRequest<AdUserDO> {
    /**
     * 验证用户名与密码
     *
     * @param account 用户名
     * @param pass    密码
     * @throws ServiceException
     */
    AdUserDTO login(String account, String pass) throws ServiceException;

    /**
     * 分页获取用户列表
     *
     * @return 只要返回 null 就是请求失败
     * @throws ServiceException
     */
    AdUserListDTO importAdUser(int pageIndex) throws ServiceException;

    /**
     * 分页获取用户列表
     *
     * @param pageSize   分页容量
     * @param pageIndex  分页页码
     * @param orderField 排序字段 默认UserID
     * @param orderType  排序类型 1.顺序;2.倒序
     * @return 只要返回 null 就是请求失败
     * @throws ServiceException
     */
    AdUserListDTO importAdUser(int pageSize, int pageIndex, String orderField, String orderType) throws ServiceException ;

    /**
     * 获取全部单位列表
     * @return 单位列表信息 ,只要为 null 则获取失败
     * @throws ServiceException
     */
    AdCorpListDTO importAdCorp() throws ServiceException ;

}
