package com.github.xjhxjhxjh.service.impl;

import com.github.xjhxjhxjh.pojo.*;
import com.github.xjhxjhxjh.mapper.UserMapper;
import com.github.xjhxjhxjh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author xjhxjhxjh
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(User user) {
        return userMapper.findUserByuNameAnduPwd(user);
    }

    @Override
    public User findUser(User user) {
        return userMapper.findUserByuTelAnduIdCard(user);
    }

    @Override
    public User findUser(Integer uId) {
        return userMapper.selUserByuId(uId);
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public Integer countIdCard(String uIdCard) {
        return userMapper.countuIdCard(uIdCard);
    }

    @Override
    public Integer countuTel(String uTel) {
        return userMapper.countuTel(uTel);
    }

    @Override
    public void updUser(User user) {
        userMapper.updUser(user);
    }

    @Override
    public User findUserAllOrder(Integer uId) {
        return userMapper.selUserAllOrder(uId);
    }

    @Override
    public void updOrder(Integer oId, Integer oStare) {
        userMapper.updOrder(oId, oStare);
    }

    @Override
    public void insOrder(Order order, Integer uId, Integer tId) {
        userMapper.insOrder(order, uId, tId);
    }

    @Override
    public void updSeatNum(Timestamp timeStart, Timestamp timeEnd, Integer tId, Integer count) {
        userMapper.updSeatNum(timeStart, timeEnd, tId, count);
    }

    @Override
    public List<Info> findInfo() {
        return userMapper.selInfo();
    }

    @Override
    public List<Level> findLevel() {
        return userMapper.selLevel();
    }

    @Override
    public List<Route> findRoute(Integer tId) {
        return userMapper.selRoute(tId);
    }

    @Override
    public List<Integer> selSeat(Integer tId, Timestamp time) {
        return userMapper.selSeat(tId, time);
    }

    @Override
    public List<UserRoute> findTrain(Integer sId, Integer eId, String tType, Timestamp nowTime) {
        return userMapper.selTrain(sId, eId, tType, nowTime);
    }

    @Override
    public Integer findStationIdByName(String sName) {
        return userMapper.selTrainId(sName);
    }

    @Override
    public List<Integer> findStationIdByCityName(String cityName) {
        return userMapper.selStationIdByCityName(cityName);
    }

    @Override
    public Integer findsIdBysName(String sName) {
        return userMapper.selsIdBysName(sName);
    }

    @Override
    public Integer findtIdBytName(String tName) {
        return userMapper.seltIdBytName(tName);
    }

    @Override
    public List<Integer> findSeatNum(Timestamp timeStart, Timestamp timeEnd, Integer tId) {
        return userMapper.selSeatNum(timeStart,timeEnd, tId);
    }

    @Override
    public List<String> findCityName() {
        return userMapper.selCityName();
    }
}
