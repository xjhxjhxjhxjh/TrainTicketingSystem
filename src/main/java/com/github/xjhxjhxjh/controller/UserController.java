package com.github.xjhxjhxjh.controller;

import com.github.xjhxjhxjh.pojo.Order;
import com.github.xjhxjhxjh.pojo.Station;
import com.github.xjhxjhxjh.pojo.UserRoute;
import com.github.xjhxjhxjh.pojo.User;
import com.github.xjhxjhxjh.service.UserService;
import com.github.xjhxjhxjh.utils.CaclPriceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author xjhxjhxjh
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("userLogin");
        return modelAndView;
    }

    @RequestMapping("/loginPage")
    public ModelAndView loginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("userLogin");
        return modelAndView;
    }

    @RequestMapping("/userLogin")
    public ModelAndView login(User user, ModelAndView modelAndView) {
        User userLogin = userService.userLogin(user);
        if (null == userLogin) {
            modelAndView.addObject("msg", "用户名，密码错误");
            modelAndView.setViewName("userLogin");
        } else {
            modelAndView.addObject("user", userLogin);
            modelAndView.setViewName("userHomePage");
        }
        return modelAndView;
    }

    @RequestMapping("/userForget")
    public ModelAndView forget(ModelAndView modelAndView) {
        modelAndView.setViewName("userForgetPage");
        return modelAndView;
    }

    @RequestMapping("/userFind")
    public ModelAndView find(User user, ModelAndView modelAndView) {
        User userFind = userService.findUser(user);
        if (null == userFind) {
            modelAndView.addObject("msg", "no");
        } else {
            modelAndView.addObject("msg", "yes");
            modelAndView.addObject("uName", userFind.getUName());
            modelAndView.addObject("uPwd", userFind.getUPwd());
        }
        modelAndView.setViewName("userForgetPage");
        return modelAndView;
    }

    @RequestMapping("/userRegister")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("userRegister");
        return modelAndView;
    }

    @RequestMapping("/userRegisterNow")
    public ModelAndView registerNow(User user, ModelAndView modelAndView) {
        Integer cnt = userService.countIdCard(user.getUIdCard());
        cnt += userService.countuTel(user.getUTel());
        if (cnt >= 1) {
            modelAndView.addObject("msg", "该身份证号或电话号已经被注册");
            modelAndView.setViewName("userLogin");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("user", userService.findUser(user));
            modelAndView.setViewName("userHomePage");
        }
        return modelAndView;
    }

    @RequestMapping("/userOrder")
    public ModelAndView userOrder(Integer uId, ModelAndView modelAndView) {
        List<Order> orders = new ArrayList<>();
        User userAllOrder = userService.findUserAllOrder(uId);
        if (userAllOrder != null) {
            orders = userAllOrder.getUList();
        }
        modelAndView.addObject("orders", orders);
        User user = userService.findUser(uId);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userOrderInfo");
        return modelAndView;
    }

    @RequestMapping("/editOrder")
    public void editOrder(Integer uId, Integer oId, Integer tId, Timestamp oStime,
                          Timestamp oEtime, ModelAndView modelAndView) {
        userService.updOrder(oId, -1);
        // startStaion到endSation的座位数加一
        userService.updSeatNum(oStime, oEtime, tId, 1);
    }

    @RequestMapping("/editUser")
    public ModelAndView editUser(Integer uId, ModelAndView modelAndView) {
        User user = userService.findUser(uId);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("editUserInfo");
        return modelAndView;
    }

    @RequestMapping("/editUserInfo")
    public ModelAndView editUserInfo(User user, ModelAndView modelAndView) {
        userService.updUser(user);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/editUserInfo");
        return modelAndView;
    }

    /**
     * 查询列车
     *
     * @param user
     * @param sCity
     * @param eCity
     * @param tType
     * @param modelAndView
     * @return
     */
    @RequestMapping("findTicket")
    public ModelAndView findTicket(User user, String sCity, String eCity, String tType, StringBuilder time,
                                   ModelAndView modelAndView) {
        // 获取选择的时间
        time.append(" 00:00:00.00");
        String s = time.toString();
        Timestamp ts = Timestamp.valueOf(s);
        List<UserRoute> routes = new ArrayList<>();
        // 获取城市所有车站
        List<Integer> sIds = userService.findStationIdByCityName(sCity);
        List<Integer> eIds = userService.findStationIdByCityName(eCity);
        // 判断是否指定了车等级
        if ("都可以".equals(tType)) {
            tType = null;
        }
        for (Integer sId : sIds) {
            for (Integer eId : eIds) {
                // 获取高铁或动车可选方案
                List<UserRoute> route = userService.findTrain(sId, eId, tType, ts);
                // 计算价格
                for (UserRoute userRoute : route) {
                    long time1 = userRoute.getUreTime().getTime();
                    long time2 = userRoute.getUrsTime().getTime();
                    long time3 = ts.getTime();
                    if (time1 - time2 > 3600 * 24 * 1000 && time1 > time3) {
                        continue;
                    }
                    userRoute.setUrPay(CaclPriceUtils.getoPrice(userRoute.getUreDimension(),
                            userRoute.getUreLongitude(), userRoute.getUrsDimension(),
                            userRoute.getUrsLongitude(), userRoute.getUrPrice()));
                    routes.add(userRoute);
                }
            }
        }
        // 排序
        if (routes != null) {
            Collections.sort(routes);
        }
        // 返回数据
        modelAndView.addObject("time", time.subSequence(0, 10));
        modelAndView.addObject("routes", routes);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("buyTicket");
        return modelAndView;
    }

    /**
     * 购票
     *
     * @param uId
     * @param userRoute
     */
    @RequestMapping("/buyTicket")
    public String buyTicket(Integer uId, UserRoute userRoute,
                            ModelAndView modelAndView) {

        // 判断票是否已过期
        long timeStart = userRoute.getUrsTime().getTime();
        long timeNow = System.currentTimeMillis() + 1800 * 1000;
        if (timeStart < timeNow) {
            return "车票已过期";
        }


        // 获取火车站ID
        Integer sStartId = userService.findsIdBysName(userRoute.getUrsStationName());
        Integer sEndId = userService.findsIdBysName(userRoute.getUreStationName());
        // 获取火车ID
        Integer tId = userService.findtIdBytName(userRoute.getUrTname());
        ;

        // 判断现在能不能买
        List<Integer> seatNum = userService.findSeatNum(userRoute.getUrsTime(), userRoute.getUreTime(), tId);
        for (Integer integer : seatNum) {
            if (integer <= 0) {
                return "暂无余票";
            }
        }

        // 封装order数据
        Station oStart = new Station();
        oStart.setSId(sStartId);
        Station oEnd = new Station();
        oEnd.setSId(sEndId);
        Order order = new Order();
        order.setOSeat(1);
        order.setOStart(oStart);
        order.setOEnd(oEnd);
        order.setOPrice(userRoute.getUrPay());
        order.setOStime(userRoute.getUrsTime());
        order.setOEtime(userRoute.getUreTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);
        order.setOTime(ts);
        userService.insOrder(order, uId, tId);

        // startStaion到endSation的座位数减一
        userService.updSeatNum(userRoute.getUrsTime(), userRoute.getUreTime(), tId, -1);
        return "购买成功";
    }

    @RequestMapping("/findCityName")
    public List<String> findCityName() {
        return userService.findCityName();
    }

}