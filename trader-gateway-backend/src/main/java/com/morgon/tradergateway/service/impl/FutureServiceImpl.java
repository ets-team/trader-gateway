package com.morgon.tradergateway.service.impl;

import com.morgon.tradergateway.model.Future;
import com.morgon.tradergateway.repository.BrokerRepository;
import com.morgon.tradergateway.repository.FutureRepository;
import com.morgon.tradergateway.service.FutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("futureService")
public class FutureServiceImpl implements FutureService{

    @Autowired
    private FutureRepository futureRepository;

    @Override
    public List<Future> findAllFutures() {
        List<Future> futureList = futureRepository.findFuturesByExpired("false");

        return futureList;
    }

    @Override
    public Future findFutureByFutureID(Long futureID) {
        return futureRepository.findFutureByFutureID(futureID);
    }

    @Override
    public List<Future> findFuturesByFutureName(String futureName) {
        return futureRepository.findFuturesByFutureName(futureName);
    }
}
