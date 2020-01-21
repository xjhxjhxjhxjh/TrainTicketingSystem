package com.github.xjhxjhxjh.service;

import com.github.xjhxjhxjh.pojo.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author xjhxjhxjh
 */
public interface UserService {

    User userLogin(User user);
    User findUser(User user);
    User findUser(Integer uId);
    void saveUser(User user);
    Integer countIdCard(String uIdCard);
    Integer countuTel(String uTel);
    void updUser(User user);
    User findUserAllOrder(Integer uId);
    void updOrder(Integer oId, Integer oStare);
    void insOrder(Order order, Integer uId, Integer tId);
    void updSeatNum(Timestamp timeStart, Timestamp timeEnd, Integer tId, Integer count);
    List<Info> findInfo();
    List<Level> findLevel();
    List<Route> findRoute(Integer tId);
    List<Integer> selSeat(Integer tId, Timestamp time);
    List<UserRoute> findTrain(Integer sId, Integer eId, String tType, Timestamp nowTime);
    Integer findStationIdByName(String sName);
    List<Integer> findStationIdByCityName(String cityName);
    Integer findsIdBysName(String sName);
    Integer findtIdBytName(String tName);
    List<Integer> findSeatNum(Timestamp timeStart, Timestamp timeEnd, Integer tId);
    List<String> findCityName();
}
