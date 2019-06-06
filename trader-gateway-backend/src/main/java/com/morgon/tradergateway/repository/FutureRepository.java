package com.morgon.tradergateway.repository;

import com.morgon.tradergateway.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhengyu Wu
 * @date 2019/5/29
 * @description FutureRepository
 * @version 1.0.0
 **/
@Repository("FutureRepository")
public interface FutureRepository extends JpaRepository<Future, String> {
    /**
     * 通过futureID获取Future
     * @param futureID
     * @return Future
     */
    Future findFutureByFutureID(Long futureID);

    /**
     * 通过futureName获取Future列表
     * @param futureName
     * @return Future列表
     */
    List<Future> findFuturesByFutureName(String futureName);

    /**
     * 获取未过期的Future列表
     * @param expired
     * @return Future列表
     */
    List<Future> findFuturesByExpired(String expired);

    /**
     * 通过futureName获取未过期的Future列表
     * @param futureName
     * @param expired
     * @return Future列表
     */
    List<Future> findFuturesByFutureNameAndExpired(String futureName, String expired);
}
