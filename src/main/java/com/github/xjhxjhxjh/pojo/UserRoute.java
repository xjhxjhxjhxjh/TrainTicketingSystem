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
public class UserRoute implements Comparable<UserRoute> {

    private String urType;
    private String urTname;
    private String ursCityName;
    private String ureCityName;
    private String ursStationName;
    private String ureStationName;
    private Double ursLongitude;
    private Double ursDimension;
    private Double ureLongitude;
    private Double ureDimension;
    private Double urPrice;
    private Double urPay;
    private Timestamp ursTime;
    private Timestamp ureTime;
    private Integer urSeatNum;

    @Override
    public int compareTo(UserRoute o) {
        if (this.getUrsTime().getTime() < o.getUrsTime().getTime()) {
            return -1;
        } else if (this.getUrsTime().getTime() == o.getUrsTime().getTime()){
                return  0;
        }else{
            return 0;
        }
    }
}
