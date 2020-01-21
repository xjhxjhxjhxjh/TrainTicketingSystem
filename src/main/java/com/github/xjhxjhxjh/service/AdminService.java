package com.github.xjhxjhxjh.service;

import com.github.xjhxjhxjh.pojo.*;

import java.util.List;

/**
 * @author xjhxjhxjh
 */
public interface AdminService {

    Admin adminLogin(Admin admin);
    List<Station> findStation();
    void addStation(Station station);
    List<User> findUser();
    void updTrain(Train train);
    void addTrain(Train train);
    void addRoute(Route route);
    List<Info> findInfo();
    List<Level> findLevel();
    List<Route> findRoute(Integer tId);
    List<Train> selTrain();
    List<User> selAllOrder();
}
