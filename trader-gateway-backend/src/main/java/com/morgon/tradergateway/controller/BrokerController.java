package com.morgon.tradergateway.controller;

import com.morgon.tradergateway.model.Broker;
import com.morgon.tradergateway.model.Trader;
import com.morgon.tradergateway.service.BrokerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zhengyu Wu
 * @date 2019/6/6
 * @description BrokerController
 * @version 1.0.0
 **/

@RestController
@Api(value = "Broker Controller", description = "对Broker对象的操作", tags = "Broker")
@RequestMapping("/broker")
public class BrokerController {

    @Autowired
    private BrokerService brokerService;

    @ApiOperation(value="getAllBrokers", notes="Get all brokers")
    @ResponseBody
    @RequestMapping(value = "/getAllBrokers", method = RequestMethod.GET)
    public List<Broker> getAllBrokers() {

        return brokerService.findAllBrokers();
    }

}
