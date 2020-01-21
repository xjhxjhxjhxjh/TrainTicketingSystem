package com.github.xjhxjhxjh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xjhxjhxjh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer uId;
    private List<Order> uList;
    private String uName;
    private String uPwd;
    private String uIdCard;
    private String uTel;
}
