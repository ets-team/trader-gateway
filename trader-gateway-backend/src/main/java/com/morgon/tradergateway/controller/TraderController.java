package com.morgon.tradergateway.controller;

import com.morgon.tradergateway.model.Trader;
import com.morgon.tradergateway.service.TraderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Zhengyu Wu
 * @date 2019/5/29
 * @description TraderController
 * @version 1.0.0
 **/

@RestController
@Api(value = "Trader Controller", description = "对Trader对象的操作", tags = "Trader")
@RequestMapping("/trader")
public class TraderController {
    @Autowired
    private TraderService traderService;

    @ApiOperation(value="login", notes="Log in")
    @ResponseBody
    @RequestMapping(value = "/login/{tradername}/{password}", method = RequestMethod.POST)
    public String loginPost(@PathVariable("tradername") String tradername, @PathVariable("password")String password, HttpSession session) {
        return traderService.login(tradername, password, session);
    }

    @ApiOperation(value="logout", notes="Log out")
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        // 移除session
        return traderService.logout(session);
    }

    @ApiOperation(value="getAllTraders", notes="Get all traders")
    @ResponseBody
    @RequestMapping(value = "/getAllTraders", method = RequestMethod.GET)
    public List<Trader> getAllTraders() {

        return traderService.findAllTraders();
    }


}
