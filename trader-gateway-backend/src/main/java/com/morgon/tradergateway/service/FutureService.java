package com.morgon.tradergateway.service;

import com.morgon.tradergateway.model.Future;

import java.util.List;

/**
 * @author Zhengyu Wu
 * @date 2019/6/6
 * @description FutureService接口
 * @version 1.0.0
 **/


public interface FutureService {
    /**
     * 查找所有future信息
     * @return future列表
     */
    List<Future> findAllFutures();


    /**
     * 查找指定id的future信息
     * @param futureID id
     * @return future信息
     */
    Future findFutureByFutureID(Long futureID);

    /**
     * 查找指定名称的future信息
     * @param futureName 名称
     * @return future列表
     */
    List<Future> findFuturesByFutureName(String futureName);
}
