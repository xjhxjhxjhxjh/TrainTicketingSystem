package com.github.xjhxjhxjh.controller;

import com.github.xjhxjhxjh.pojo.Admin;
import com.github.xjhxjhxjh.pojo.Route;
import com.github.xjhxjhxjh.pojo.Train;
import com.github.xjhxjhxjh.pojo.User;
import com.github.xjhxjhxjh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xjhxjhxjh
 */
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/adminLoginPage")
    public ModelAndView adminLoginPage(ModelAndView modelAndView){
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping("/adminLogin")
    public ModelAndView login(Admin admin , ModelAndView modelAndView){
        Admin adminLogin = adminService.adminLogin(admin);
        if (null == adminLogin){
            modelAndView.addObject("msg","用户名，密码错误");
            modelAndView.setViewName("adminLogin");
        } else{
            modelAndView.addObject("admin",adminLogin);
            modelAndView.setViewName("adminHomePage");
        }
        return modelAndView;
    }

    @RequestMapping("/userInfo")
    public ModelAndView userInfo(ModelAndView modelAndView){
        List<User> user = adminService.findUser();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("userInfo");
        return modelAndView;
    }

    @RequestMapping("/trainInfo")
    public ModelAndView trainIfo(ModelAndView modelAndView){
        List<Train> train = adminService.selTrain();
        modelAndView.addObject("train",train);
        modelAndView.setViewName("trainInfo");
        return modelAndView;
    }

    @RequestMapping("/editTrain")
    public ModelAndView editTrain(Train train, ModelAndView modelAndView){
        adminService.updTrain(train);
        List<Train> trainSel = adminService.selTrain();
        modelAndView.addObject("train",trainSel);
        modelAndView.setViewName("trainInfo");
        modelAndView.addObject("msg","更新完成");
        return  modelAndView;
    }

    @RequestMapping("/routeInfo")
    public ModelAndView routeInfo(ModelAndView modelAndView){
        List<Route> route = new ArrayList<>();
        List<Train> trains = adminService.selTrain();
        for (Train train : trains){
            List<Route> route1 = adminService.findRoute(train.getTId());
            if(route1 != null){
                for (Route route2 : route1) {
                    if(route2 != null && route2.getRStation() != null){
                        route.add(route2);
                    }
                }
            }
        }
        modelAndView.addObject("route",route);
        modelAndView.setViewName("routeInfo");
        return  modelAndView;
    }

    @RequestMapping("/allOrder")
    public ModelAndView allOrder(ModelAndView modelAndView){
        List<User> users = adminService.selAllOrder();
        modelAndView.addObject("users",users);
        modelAndView.setViewName("allOrder");
        return  modelAndView;
    }
}
