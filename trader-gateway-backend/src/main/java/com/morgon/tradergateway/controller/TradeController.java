package com.morgon.tradergateway.controller;

import com.morgon.tradergateway.service.TradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Api(value = "Trade Controller", description = "对Trade对象的操作", tags = "Trade")
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @ApiOperation(value="getAllTrades", notes="Get All Trades")
    @ResponseBody
    @RequestMapping(value = "/getAllTrades/{id}", method = RequestMethod.GET)
    public Object getUser(@PathVariable("id") int id)
    {

        return tradeService.findAllTrades(id);
    }
}
