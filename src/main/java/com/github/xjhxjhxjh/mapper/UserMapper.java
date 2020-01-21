package com.github.xjhxjhxjh.mapper;

import com.github.xjhxjhxjh.pojo.*;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author xjhxjhxjh
 */
@Mapper
public interface UserMapper {
    /**
     * 根据密码和用户名查询用户
     * @param user
     * @return User
     */
    @Select("select * from t_User where uName = #{uName} and uPwd = #{uPwd}")
    User findUserByuNameAnduPwd(User user);

    /**
     * 根据电话号和身份证查询用户
     * @param user
     * @return User
     */
    @Select("select * from t_User where uTel = #{uTel} and uIdCard = #{uIdCard}")
    User findUserByuTelAnduIdCard(User user);

    /**
     * 根据用户Id查用户
     * @param uId
     * @return
     */
    @Select("select * from t_User where uId = #{uId}")
    User selUserByuId(Integer uId);

    /**
     * 插入用户
     * @param user
     */
    @Insert("insert into t_User values (#{uId}, #{uName}, #{uPwd}, #{uTel}, #{uIdCard})")
    void saveUser(User user);

    /**
     * 查询同一身份证出现次数
     * @param uIdCard
     * @return 同一身份证出现次数
     */
    @Select("select count(*) from t_User where uIdCard = #{uIdCard}")
    Integer countuIdCard(String uIdCard);

    /**
     * 查询同一电话号出现次数
     * @param uTel
     * @return
     */
    @Select("select count(*) from t_User where uTel = #{uTel}")
    Integer countuTel(String uTel);

    /**
     * 更新用户信息
     * @param user
     */
    @Update("update t_user set uName = #{uName}, uPwd = #{uPwd}, uIdCard = #{uIdCard}," +
            " uTel = #{uTel} where uId = #{uId}")
    void updUser(User user);

    /**
     * 查询用户所有订单
     * @param uId
     * @return User
     */
    User selUserAllOrder(Integer uId);


    /**
     * 修改订单状态（0：以购票，1：已完成，-1：已取消订单）
     * @param oId
     * @param oState
     */
    @Update("update t_order set oState = #{oState} where oId = #{oId}")
    void updOrder(Integer oId, Integer oState);

    /**
     * 新增订单
     * @param order
     */
    @Insert("insert into t_order(oId,oPrice,oState,o_uId,oEnd,oStart,o_tId,oSeat,oTime,oStime,oEtime) " +
            "values(default,#{order.oPrice},default,#{uId}," +
            "#{order.oEnd.sId},#{order.oStart.sId},#{tId}, #{order.oSeat},#{order.oTime},#{order.oStime},#{order.oEtime})")
    void insOrder(@Param("order") Order order, @Param("uId") Integer uId, @Param("tId") Integer tId);

    /**
     * 更新座位数
     * @param timeStart
     * @param timeEnd
     * @param tId
     * @param count
     */
    @Update("update t_Route set rSeatNum = rSeatNum + #{count} where r_tId = #{tId} and " +
            " unix_timestamp(rDate) >= unix_timestamp(#{timeStart}) " +
            " and unix_timestamp(#{timeEnd}) >= unix_timestamp(rDate)")
    void  updSeatNum(Timestamp timeStart, Timestamp timeEnd, Integer tId, Integer count);

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

    /**
     * 查询乘坐的火车 选等级
     * @param sId
     * @param eId
     * @return
     */
    List<UserRoute>  selTrain(Integer sId, Integer eId, String tType, Timestamp nowTime);

    /**
     * 根据名字查火车Id
     * @param sName
     * @return
     */
    @Select("select sId from t_station where sName = #{sName}")
    Integer selTrainId(String sName);

    /**
     * 查询座位
     * @param tId
     * @param time
     * @return
     */
    @Select("select oSeat from t_order where o_tId = #{tId} and oTime = #{time}")
    List<Integer> selSeat(Integer tId, Timestamp time);

    /**
     * 查询城市的车站
     * @param cityName
     * @return
     */
    @Select("select sId from t_station where sCityName = #{cityName}")
    List<Integer> selStationIdByCityName(String cityName);

    /**
     * 根据车站名字查车站Id
     * @param sName
     * @return
     */
    @Select("select sId from t_station where sName = #{sName}")
    Integer selsIdBysName(String sName);

    /**
     * 根据列车名字查列车Id
     * @param tName
     * @return
     */
    @Select("select tId from t_train where tName = #{tName}")
    Integer seltIdBytName(String tName);

    /**
     * 查询座位数
     * @param timeStart
     * @param timeEnd
     * @param tId
     * @return
     */
    @Select("select rSeatNum from t_route where r_tId = #{tId} and " +
            " unix_timestamp(rDate) >= unix_timestamp(#{timeStart}) " +
            " and unix_timestamp(#{timeEnd}) >= unix_timestamp(rDate)")
    List<Integer> selSeatNum(Timestamp timeStart, Timestamp timeEnd, Integer tId);

    @Select("select DISTINCT sCityName from t_station")
    List<String> selCityName();
}