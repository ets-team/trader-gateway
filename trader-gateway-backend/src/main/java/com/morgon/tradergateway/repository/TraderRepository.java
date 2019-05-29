package com.morgon.tradergateway.repository;

import com.morgon.tradergateway.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zhengyu Wu
 * @date 2019/5/28
 * @description TraderRepository
 * @version 1.0.0
 **/
@Repository("TraderRepository")
public interface TraderRepository extends JpaRepository<Trader, Long> {
    /**
     * 通过traderName获取Trader
     * @param traderName
     * @return Trader
     */
    Trader findTraderByTraderName(String traderName);
    Trader findTraderByTraderNameAndPassword(String traderName, String password);
}
