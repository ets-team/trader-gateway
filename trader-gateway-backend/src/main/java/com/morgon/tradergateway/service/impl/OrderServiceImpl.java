package com.morgon.tradergateway.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.morgon.tradergateway.model.Broker;
import com.morgon.tradergateway.model.Order;
import com.morgon.tradergateway.repository.BrokerRepository;
import com.morgon.tradergateway.service.OrderService;
import com.morgon.tradergateway.utils.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.ConnectException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author Zhengyu Wu
 * @date 2019/6/4
 * @description OrderService实现
 * @version 1.0.0
 **/
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private BrokerRepository brokerRepository;

    @Override
    public Order sendOrder(Order order, HttpServletRequest request) {
        System.out.println("fuck1");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(order));
        System.out.println("fuck");

        HttpSession session = request.getSession();
        String tradername = session.getAttribute("user").toString();
        if (tradername == null) return null;

        //order.setOrderID();
        order.setTimeStamp(LocalDateTime.now(ZoneId.of("UTC")));
        order.setTraderName(tradername);
        order.setBrokerName("broker1");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order> re = restTemplate.postForEntity("http://59.78.48.187:8011/order", order, Order.class);
        Order rst = re.getBody();
        System.out.println(rst.toString());


        return rst;


    }

    @Override
    public boolean cancelOrder(Order order, HttpServletRequest request) {
        /*String username = jwtTokenUtil.parseUsername(request);
        if (username == null) return false;

        String Target = order.getBrokerName();*/

        return false;

    }

    @Override
    public Map getOrders(String futuresID, String status, String page, HttpServletRequest request) {
        /*String username = jwtTokenUtil.parseUsername(request);
        String params = "";
        if (!futuresID.equals("null")) params = params + "&futures_id=" + futuresID;
        if (!status.equals("-1")) params = params + "&status=" + status;
        params = params + "&trader_name=" + username;
        params = params + "&page=" + page;

        System.out.println(params);

        List<Broker> brokers = brokerRepository.findAll();
        int totalNum = 0;
        List orderList = new ArrayList();
        for (Broker broker : brokers) {
            String result = new HttpUtil().sendGet(broker.getBrokerHttp() + "/orders", params, broker.getBrokerToken());
            JSONObject jsonResult = JSONObject.fromObject(result);
            JSONArray jsonArray = jsonResult.getJSONArray("data");
            totalNum += jsonResult.getInt("total");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                object.put("brokerName", broker.getBrokerName());
                orderList.add(object);
            }
        }
        */
        Map resultMap = new HashMap();
        //resultMap.put("orderList", orderList);
        //resultMap.put("totalNum", totalNum);
        return resultMap;
    }
}

