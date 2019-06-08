package com.morgon.tradergateway.service;

import java.util.List;

public interface TradeService {
    /**
     * 根据trader id返回所有成交信息
     * @return trade列表
     */
    Object findAllTrades(int traderId);
}
