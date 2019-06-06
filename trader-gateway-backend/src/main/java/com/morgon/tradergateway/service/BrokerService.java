package com.morgon.tradergateway.service;

import com.morgon.tradergateway.model.Broker;

import java.util.List;

/**
 * @author Zhengyu Wu
 * @date 2019/6/6
 * @description BrokerService接口
 * @version 1.0.0
 **/
public interface BrokerService {
    /**
     * 查找所有broker信息(ID、名称)
     * @return broker信息列表
     */
    List<Broker> findAllBrokers();
}
