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
public class Order {
    private Integer oId;
    private Double oPrice;
    private Integer oState;
    private Station oEnd;
    private Station oStart;
    private Train oTrain;
    private Integer oSeat;
    private Timestamp oTime;
    private Timestamp oStime;
    private Timestamp oEtime;
}
