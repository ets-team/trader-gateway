package com.morgon.tradergateway.repository;

import com.morgon.tradergateway.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zhengyu Wu
 * @date 2019/5/29
 * @description BrokerRepository
 * @version 1.0.0
 **/
@Repository("BrokerRepository")
public interface BrokerRepository extends JpaRepository<Broker, Long> {
    /**
     * 通过brokerName获取Broker
     * @param brokerName
     * @return Broker
     */
    Broker findBrokerByBrokerName(String brokerName);
    Broker findBrokerByBrokerID(String brokerID);
}
