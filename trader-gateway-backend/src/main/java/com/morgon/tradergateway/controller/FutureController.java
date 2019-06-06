package com.morgon.tradergateway.controller;

import com.morgon.tradergateway.model.Broker;
import com.morgon.tradergateway.model.Future;
import com.morgon.tradergateway.service.FutureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zhengyu Wu
 * @date 2019/6/6
 * @description FutureController
 * @version 1.0.0
 **/

@RestController
@Api(value = "Future Controller", description = "对Future对象的操作", tags = "Future")
@RequestMapping("/future")
public class FutureController {

    @Autowired
    private FutureService futureService;

    @ApiOperation(value="getAllFutures", notes="Get all non-expired futures")
    @ResponseBody
    @RequestMapping(value = "/getAllFutures", method = RequestMethod.GET)
    public List<Future> getAllFutures() {

        return futureService.findAllFutures();
    }

    @ApiOperation(value="findFutureByFutureID", notes="Get Future By FutureID")
    @ResponseBody
    @RequestMapping(value = "/findFutureByFutureID/{FutureID}", method = RequestMethod.GET)
    public Future getAllFutures(@PathVariable("FutureID") Long FutureID) {

        return futureService.findFutureByFutureID(FutureID);
    }

    @ApiOperation(value="findFuturesByFutureName", notes="Get Futures By FutureName")
    @ResponseBody
    @RequestMapping(value = "/findFuturesByFutureName/{FutureName}", method = RequestMethod.GET)
    public List<Future> findFuturesByFutureName(@PathVariable("FutureName") String FutureName) {

        return futureService.findFuturesByFutureName(FutureName);
    }

}
