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
    public Boolean sendOrder(Order order, HttpServletRequest request) {
        /*
        System.out.println("fuck1");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(order));
        System.out.println("fuck");*/

        //HttpSession session = request.getSession();
        //String tradername = session.getAttribute("user").toString();
        //if (tradername == null) return null;

        //order.setOrderID();
        //order.setTimeStamp(LocalDateTime.now(ZoneId.of("UTC")));
        //order.setTraderName(tradername);
        //order.setBrokerName("broker1");


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> re = restTemplate.postForEntity("http://59.78.48.187:8011/order", order, Boolean.class);
        Boolean rst = re.getBody();
        //System.out.println(rst.toString());

        return !rst;
        
    }

    @Override
    public Boolean cancelOrder(Order order, HttpServletRequest request) {

        if (!order.getType().equals("c")){
            order.setType("c");
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> re = restTemplate.postForEntity("http://59.78.48.187:8011/order", order, Boolean.class);
        Boolean rst = re.getBody();

        return !rst;

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

    @Override
    public Boolean iceBerg(Order order, HttpServletRequest request){

        Order order1 = new Order();
        Order order2  = new Order();
        Order order3 = new Order();
        int amount = order.getAmount()/3;

        order1.setAmount(amount);
        order1.setType(order.getType());
        order1.setTraderName(order.getTraderName());
        order1.setFutureID(order.getFutureID());
        order1.setOrderID(order.getOrderID());
        order1.setPrice(order.getPrice());
        order1.setPrice2(order.getPrice2());
        order1.setSide(order.getSide());

        order2.setAmount(amount);
        order2.setType(order.getType());
        order2.setTraderName(order.getTraderName());
        order2.setFutureID(order.getFutureID());
        order2.setOrderID(order.getOrderID());
        order2.setPrice(order.getPrice());
        order2.setPrice2(order.getPrice2());
        order2.setSide(order.getSide());

        order3.setAmount(order.getAmount()-2*amount);
        order3.setType(order.getType());
        order3.setTraderName(order.getTraderName());
        order3.setFutureID(order.getFutureID());
        order3.setOrderID(order.getOrderID());
        order3.setPrice(order.getPrice());
        order3.setPrice2(order.getPrice2());
        order3.setSide(order.getSide());
        order3.setTraderOrderID(order.getTraderOrderID());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> re1 = restTemplate.postForEntity("http://59.78.48.187:8011/order", order1, Boolean.class);
        Boolean rst1 = re1.getBody();

        ResponseEntity<Boolean> re2 = restTemplate.postForEntity("http://59.78.48.187:8011/order", order2, Boolean.class);
        Boolean rst2 = re2.getBody();

        ResponseEntity<Boolean> re3 = restTemplate.postForEntity("http://59.78.48.187:8011/order", order3, Boolean.class);
        Boolean rst3 = re3.getBody();

        return !rst1 & !rst2 & !rst3 ;

    }
}

