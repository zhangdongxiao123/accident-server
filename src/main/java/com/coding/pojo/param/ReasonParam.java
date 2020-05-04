package com.coding.pojo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class ReasonParam {

    @ApiModelProperty("原因名称")
    private String reaname;

    @ApiModelProperty("原因详情")
    private String reahow;
}
