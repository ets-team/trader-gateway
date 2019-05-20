package com.morgon.tradergateway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Zhengyu Wu
 * @date 2019/5/20
 * @description Future
 * @version 1.0.0
 **/
@Entity
@Table(name = "Futures")
public class Future implements Serializable {
    @Id
    @Column(nullable = false, name = "futures_id")
    private String futureID;

    @Column(nullable = false, name = "futures_name")
    private String futureName;

    @Column(nullable = false, name = "period")
    private String period;

    @Column(nullable = false, name = "symbol")
    private String symbol;

    @Column(nullable = false, name = "category")
    private String category;

    @Column(nullable = false, name = "expired")
    private String expired;

    @Column(nullable = false, name = "icon")
    private String icon;

    public Future() {}

    public Future(String futureID, String futureName, String period, String symbol, String category, String expired, String icon) {
        this.futureID = futureID;
        this.futureName = futureName;
        this.period = period;
        this.symbol = symbol;
        this.category = category;
        this.expired = expired;
        this.icon = icon;
    }

    public String getFutureID() {
        return futureID;
    }

    public void setFutureID(String futureID) {
        this.futureID = futureID;
    }

    public String getFutureName() {
        return futureName;
    }

    public void setFutureName(String futureName) {
        this.futureName = futureName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
