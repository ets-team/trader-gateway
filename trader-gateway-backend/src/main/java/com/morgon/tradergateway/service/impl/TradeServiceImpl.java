package com.morgon.tradergateway.service.impl;

import com.morgon.tradergateway.model.Trade;
import com.morgon.tradergateway.model.Trader;
import com.morgon.tradergateway.service.TradeService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Zhengyu Wu
 * @date 2019/6/8
 * @description TradeService实现
 * @version 1.0.0
 **/

@Service("TradeService")
public class TradeServiceImpl implements TradeService {

    @Override
    public Object findAllTrades(int traderId) {

        String url = "http://192.168.2.40:30405/history?traderID=" + traderId;
        //System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> re =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {
                        });
        Object rst = re.getBody();
        return rst;
    }

}
