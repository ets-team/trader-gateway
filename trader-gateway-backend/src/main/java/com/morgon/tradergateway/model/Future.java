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
    private Long futureID;

    @Column(nullable = false, name = "futures_name")
    private String futureName;

    @Column(nullable = false, name = "period")
    private String period;

    @Column(nullable = false, name = "expired")
    private String expired; // "false" "true"


    public Future() {}

    public Future(Long futureID, String futureName, String period,  String expired) {
        this.futureID = futureID;
        this.futureName = futureName;
        this.period = period;
        this.expired = expired;
    }

    public Long getFutureID() {
        return futureID;
    }

    public void setFutureID(Long futureID) {
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

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

}
