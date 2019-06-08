package com.morgon.tradergateway.model;


import java.io.Serializable;

/**
 * @author Zhengyu Wu
 * @date 2019/6/8
 * @description Trade
 * @version 1.0.0
 **/

public class Trade implements Serializable {
    private int amount;
    private int buyTraderID;
    private int buyTraderOrderID;
    private int price;
    private int sellTraderID;
    private int sellTraderOrderID;
    private String timeStamp;
    private int tradeID;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBuyTraderID() {
        return buyTraderID;
    }

    public void setBuyTraderID(int buyTraderID) {
        this.buyTraderID = buyTraderID;
    }

    public int getBuyTraderOrderID() {
        return buyTraderOrderID;
    }

    public void setBuyTraderOrderID(int buyTraderOrderID) {
        this.buyTraderOrderID = buyTraderOrderID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSellTraderID() {
        return sellTraderID;
    }

    public void setSellTraderID(int sellTraderID) {
        this.sellTraderID = sellTraderID;
    }

    public int getSellTraderOrderID() {
        return sellTraderOrderID;
    }

    public void setSellTraderOrderID(int sellTraderOrderID) {
        this.sellTraderOrderID = sellTraderOrderID;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getTradeID() {
        return tradeID;
    }

    public void setTradeID(int tradeID) {
        this.tradeID = tradeID;
    }


}
