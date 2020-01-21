package com.github.xjhxjhxjh.utils;

import com.github.xjhxjhxjh.pojo.Station;

import java.math.BigDecimal;

/**
 * 通过两点经纬度和火车单价求计算价格
 * @author xjhxjhxjh
 */
public class CaclPriceUtils {

    /**
     * /地球半径,单位千米
     */
    private static final double EARTH_RADIUS = 6378.137;

    /**
     * 根据车站求价格
     */
    public static Double getoPrice(Station oStart, Station oEnd, double price) {
        if (oStart.getSLongitude() == null || oEnd.getSLongitude() == null ||
                oStart.getSDimension() == null || oEnd.getSDimension() == null) {
            return 0D;
        } else {
            BigDecimal bigDecimal = new BigDecimal(getDistance(oStart.getSLongitude(),oStart.getSDimension(),
                    oEnd.getSLongitude(), oEnd.getSDimension()) * price);
            return  bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        }
    }

    /**
     * 根据两地经纬度求价格
     */
    public static Double getoPrice(Double l1, Double s1, Double l2, Double s2, double price) {
        if (l1 == null || l2 == null || s1 == null || s2 == null) {
            return 0D;
        } else {
            BigDecimal bigDecimal = new BigDecimal(getDistance(l1,s1,l2,s2) * price);
            return  bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        }
    }

    /**
     * 以知经纬度求距离
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return 距离
     */
    private static Double getDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 求弧度
     * @param d
     * @return 弧度
     */
    private static Double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
