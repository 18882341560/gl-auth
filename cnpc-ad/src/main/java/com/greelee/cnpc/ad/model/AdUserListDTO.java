package com.greelee.cnpc.ad.model;

import lombok.*;

import java.util.List;

/**
 * @author: gl
 * @Email: 110.com
 * @version: 1.0
 * @Date: 2019/3/25
 * @describe:  AD域请求 API 获取的响应解析JSON 后获取的模型对象,也是中石油的字段
 */
@Data
@Builder
@AllArgsConstructor
public class AdUserListDTO {
    private List<AdUserDTO> Rows;
    private Integer Total;
}
