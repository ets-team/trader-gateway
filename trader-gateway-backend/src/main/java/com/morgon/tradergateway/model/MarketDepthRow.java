package com.morgon.tradergateway.model;

import java.io.Serializable;

/**
 * @author Zhengyu Wu
 * @date 2019/6/8
 * @description MarketDepthRow
 * @version 1.0.0
 **/

public class MarketDepthRow implements Serializable {

    private int level;

    private int buy_vol;

    private int price;

    private int sell_vol;

    private int level2;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBuy_vol() {
        return buy_vol;
    }

    public void setBuy_vol(int buy_vol) {
        this.buy_vol = buy_vol;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSell_vol() {
        return sell_vol;
    }

    public void setSell_vol(int sell_vol) {
        this.sell_vol = sell_vol;
    }

    public int getLevel2() {
        return level2;
    }

    public void setLevel2(int level2) {
        this.level2 = level2;
    }

}
