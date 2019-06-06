package com.morgon.tradergateway.model;

import javax.persistence.*;

/**
 * @author Zhengyu Wu
 * @date 2019/5/20
 * @description Broker
 * @version 1.0.0
 **/
@Entity
@Table(name = "Broker")
public class Broker {
    @Id
    @Column(nullable = false, name = "broker_id")
    private Long brokerID;

    @Column(nullable = false, name = "broker_name")
    private String brokerName;


    public Broker() {}

    public Broker(Long brokerID, String brokerName, String brokerHttp, String brokerWs, String brokerToken) {
        this.brokerID = brokerID;
        this.brokerName = brokerName;

    }

    public Long getBrokerID() {
        return brokerID;
    }

    public void setBrokerID(Long brokerID) {
        this.brokerID = brokerID;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

   
}
