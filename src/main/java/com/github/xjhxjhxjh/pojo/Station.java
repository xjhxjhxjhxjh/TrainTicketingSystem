package com.github.xjhxjhxjh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xjhxjhxjh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    private Integer sId;
    private String sName;
    private String sCityName;
    private Double sLongitude;
    private Double sDimension;
}
