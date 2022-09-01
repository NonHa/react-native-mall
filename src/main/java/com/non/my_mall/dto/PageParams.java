package com.non.my_mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "分页查询对象")
public class PageParams {
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", example = "1")
    private Integer page = 1;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数", example = "10")
    private Integer pageSize = 10;
    /**
     * 进行排序的字段
     */
    @ApiModelProperty(value = "进行排序的字段", example = "id")
    private String orderField;
    /**
     * 排序类型：desc降序，升序,默认降序
     */
    @ApiModelProperty(value = "排序类型", example = "desc")
    private String seType="desc";

}
