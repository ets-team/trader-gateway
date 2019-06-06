package com.morgon.tradergateway.service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Zhengyu Wu
 * @date 2019/6/4
 * @description TraderService接口
 * @version 1.0.0
 **/
public interface TraderService {

    /**
     * 所有用户信息
     * @return trader列表
     */
    List findAllTraders();

    /**
     * 检查用户是否存在
     * @param trader 新用户
     * @return 布尔值
     */
    //boolean checkDuplicate(Trader trader);



    /**
     * 用户登录
     * @param traderName 用户名
     * @param password 密码
     * @return rst
     */
    String login(String traderName, String password, HttpSession session);



    /**
     * 用户登出
     * @param session session
     * @return rst
     */
    String logout(HttpSession session);
}
