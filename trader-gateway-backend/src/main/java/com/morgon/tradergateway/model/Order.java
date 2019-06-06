package com.morgon.tradergateway.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Zhengyu Wu
 * @date 2019/5/20
 * @description Order
 * @version 1.0.0
 **/
public class Order implements Serializable {
    private Long orderID;

    private Long futureID;

    private String type; // 'm','f','s','c'

    private String side;  // 'b','s'

    private Integer price;

    private Integer price2;

    private Integer amount;

    private String brokerName;

    private String traderName;

    private Long traderOrderID;

    public Order() {}

    public Order(Long orderID, Long futureID, String type, String side,
                 Integer price, Integer price2, Integer amount, String brokerName, Long traderOrderID) {
        this.orderID = orderID;
        this.futureID = futureID;
        this.type = type;
        this.side = side;
        this.price = price;
        this.price2 = price;
        this.amount = amount;
        this.brokerName = brokerName;
        this.traderOrderID = traderOrderID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getFutureID() {
        return futureID;
    }

    public void setFutureID(Long futureID) {
        this.futureID = futureID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public Integer getPrice2() {
        return price2;
    }

    public void setPrice2(Integer price2) {
        this.price2 = price2;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public Long getTraderOrderID() {
        return traderOrderID;
    }

    public void setTraderOrderID(Long traderOrderID) {
        this.traderOrderID = traderOrderID;
    }
}

