package com.github.xjhxjhxjh.service.impl;

import com.github.xjhxjhxjh.pojo.*;
import com.github.xjhxjhxjh.service.AdminService;
import com.github.xjhxjhxjh.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xjhxjhxjh
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin adminLogin(Admin admin) {
        return adminMapper.selAdminByaNameAndaPwd(admin);
    }

    @Override
    public List<Station> findStation() {
        return adminMapper.selStation();
    }

    @Override
    public void addStation(Station station) {
        adminMapper.insStation(station);
    }

    @Override
    public List<User> findUser() {
        return adminMapper.selUser();
    }

    @Override
    public void updTrain(Train train) {
        adminMapper.updTrain(train);
    }

    @Override
    public void addTrain(Train train) {
        adminMapper.insTrain(train);
    }

    @Override
    public void addRoute(Route route) {
        adminMapper.insRoute(route);
    }

    @Override
    public List<Info> findInfo() {
        return adminMapper.selInfo();
    }

    @Override
    public List<Level> findLevel() {
        return adminMapper.selLevel();
    }

    @Override
    public List<Route> findRoute(Integer tId) {
        return adminMapper.selRoute(tId);
    }

    @Override
    public List<Train> selTrain() {
        return adminMapper.selTrain();
    }

    @Override
    public List<User> selAllOrder() {
        return adminMapper.selAllOrder();
    }

}
