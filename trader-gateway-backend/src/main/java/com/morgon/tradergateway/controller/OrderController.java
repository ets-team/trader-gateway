package com.morgon.tradergateway.controller;


import com.morgon.tradergateway.model.Order;
import com.morgon.tradergateway.service.OrderService;
import com.morgon.tradergateway.service.TraderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Zhengyu Wu
 * @date 2019/5/29
 * @description OrderController
 * @version 1.0.0
 **/

@RestController
@Api(value = "Order Controller", description = "对Order对象的操作", tags = "Order")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value="Send order", notes="Send order")
    @ResponseBody
    @RequestMapping(value = "/sendOrder", method = RequestMethod.POST)
    public Boolean sendOrder(@RequestBody Order order, HttpServletRequest hr) {
        return orderService.sendOrder(order,  hr);
        //return orderService.iceBerg(order,  hr);
        //return orderService.TWAP(order,  hr);
    }

    @ApiOperation(value="Cancel order", notes="Cancel order")
    @ResponseBody
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public Boolean cancelOrder(@RequestBody Order order, HttpServletRequest hr) {
        return orderService.cancelOrder(order,  hr);
    }
}
