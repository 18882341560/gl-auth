package com.greelee.cnpc.ad.model;

import lombok.*;

import java.util.List;

/**
 * @author: gl
 * @Email: 110.com
 * @version: 1.0
 * @Date: 2019/3/25
 * @describe:  AD域请求 API 获取的响应解析JSON 后获取的模型对象
 */


@Builder
@Data
@AllArgsConstructor
public class AdCorpListDTO {
    private List<AdCorpDTO> Rows;
    private Integer Total;
}
