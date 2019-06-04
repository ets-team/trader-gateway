package com.morgon.tradergateway.service.impl;

import com.morgon.tradergateway.model.Trader;
import com.morgon.tradergateway.repository.TraderRepository;
import com.morgon.tradergateway.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Zhengyu Wu
 * @date 2019/6/4
 * @description TraderService实现
 * @version 1.0.0
 **/
@Service("TraderService")
public class TraderServiceImpl implements TraderService{

    @Autowired
    private TraderRepository traderRepository;

    @Override
    public List<Trader> findAllTraders() {
        return traderRepository.findAll();
    }

    @Override
    public String login(String traderName, String password, HttpSession session){
        System.out.println(traderName);
        //String pw = traderRepository.findTraderByTraderName(traderName).getPassword();
        /*System.out.println(pw);
        if (pw.equals(password)){

        // 设置session
            session.setAttribute("user", traderName);
*/
            return "success";
       // }
        //else return "fail";
    }

    @Override
    public String logout(HttpSession session){
        // 移除session
        session.removeAttribute("user");
        return "success";
    }
}
