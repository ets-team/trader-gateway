package com.morgon.tradergateway.service.impl;

import com.morgon.tradergateway.model.Broker;
import com.morgon.tradergateway.repository.BrokerRepository;
import com.morgon.tradergateway.service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhengyu Wu
 * @date 2019/6/6
 * @description BrokerService实现
 * @version 1.0.0
 **/

@Service("brokerService")
public class BrokerServiceImpl implements BrokerService {
    @Autowired
    private BrokerRepository brokerRepository;

    @Override
    public List<Broker> findAllBrokers() {
        List<Broker> brokers = brokerRepository.findAll();

        return brokers;
    }
}
