package com.github.xjhxjhxjh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xjhxjhxjh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {
    private Integer tId;
    private Integer tMaxNum;
    private Double tPrice;
    private String tName;
    private String tType;
    private List<Route> tList;
    private String tDriverName;
}
