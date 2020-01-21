package com.github.xjhxjhxjh.mapper;

import com.github.xjhxjhxjh.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author xjhxjhxjh
 */
@Mapper
public interface AdminMapper {

    /**
     *  根据密码和用户名查询管理员
     * @param admin
     * @return Admin
     */
    @Select("select * from t_Admin where aName = #{aName} and aPwd = #{aPwd}")
    Admin selAdminByaNameAndaPwd(Admin admin);

    /**
     * 查询所有的火车站信息
     * @return 火车站信息
     */
    @Select("select * from t_Station")
    List<Station> selStation();

    /**
     * 新增车站
     * @param station
     */
    @Insert("Insert into t_station values(#{sDimension},default,#{sName},#{sLongitude},#{sCityName})")
    void insStation(Station station);

    /**
     * 查询所有用户信息
     * @return 用户信息
     */
    @Select("select * from t_User")
    List<User> selUser();

    /**
     * 修改火车信息
     * @param train
     * @return
     */
    @Update("update t_train set tPrice = #{tPrice}, tName = #{tName}," +
            " tDriverName = #{tDriverName},tMaxNum = #{tMaxNum} where tId = #{tId}")
    void updTrain(Train train);

    /**
     * 新增火车
     * @param train
     */
    @Insert("insert into t_train(tId,tName,tType,tPrice,tMaxNum,tDriverName) values(#{tId},#{tName}," +
            "#{tType},#{tPrice},#{tMaxNum},#{tDriverName})")
    void insTrain(Train train);


    /**
     * 查询火车信息
     * @return
     */
    @Select("select * from t_train")
    List<Train> selTrain();

    /**
     * 新增线路
     * @param route
     */
    @Insert("insert into t_route(rId,r_tId,r_sId,rDate,rSeatNum) values(#{rId},#{rTrain.tId},#{rStation.sId}," +
            "#{rDate},#{rSeatNum})")
    void insRoute(Route route);

    /**
     * 查找车站信息
     * @return 信息表
     */
    @Select("select * from t_info")
    List<Info> selInfo();

    /**
     * 查询等级信息
     * @return 等级信息
     */
    @Select("select * from t_level")
    List<Level> selLevel();

    /**
     * 查询火车的线路
     * @param tId
     * @return
     */
    List<Route> selRoute(Integer tId);

    /**order
     * 查询所有订单
     * @return
     */
    List<User> selAllOrder();
}
