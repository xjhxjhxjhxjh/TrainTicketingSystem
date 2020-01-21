package com.github.xjhxjhxjh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author xjhxjhxjh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private Integer rId;
    private Train rTrain;
    private Station rStation;
    private Timestamp rDate;
    private Integer rSeatNum;
}
