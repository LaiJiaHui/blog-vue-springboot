package com.shimh.entity;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * @author linguochao
 * @Description 页面参数
 * @Date 2020/1/19 10:49
 */

@Getter
@Setter
public class PageEntity {

    @NotNull
    private Integer pageSize;

    @NotNull
    private Integer pageNum;
}
