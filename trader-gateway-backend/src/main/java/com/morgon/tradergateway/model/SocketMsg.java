package com.morgon.tradergateway.model;

import java.util.List;

/**
 * @author Zhengyu Wu
 * @date 2019/6/5
 * @description websocket传输的SocketMsg类
 * @version 1.0.0
 **/

public class SocketMsg {
    private int type; //聊天类型0：群聊，1：单聊.
    private String toUser;//接受者.
    private List<MarketDepthRow> marketDepth;


    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getToUser() {
        return toUser;
    }
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }
    public List<MarketDepthRow> getMarketDepth() {
        return marketDepth;
    }

    public void setMarketDepth(List<MarketDepthRow> marketDepth) {
        this.marketDepth = marketDepth;
    }
    //private String msg;//消息
    /*/public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }*/

}