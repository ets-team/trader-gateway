package com.morgon.tradergateway.service.impl;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.morgon.tradergateway.model.Broker;
import com.morgon.tradergateway.model.Order;
import com.morgon.tradergateway.model.OrderbookItem;
import com.morgon.tradergateway.repository.BrokerRepository;
import com.morgon.tradergateway.repository.FutureRepository;
import com.morgon.tradergateway.service.OrderService;
import com.morgon.tradergateway.utils.HttpUtil;
import com.morgon.tradergateway.utils.SnowflakeIdWorker;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
    @Autowired
    private FutureRepository futureRepository;

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

        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        order.setOrderID(id);
        //order.setTimeStamp(LocalDateTime.now(ZoneId.of("UTC")));
        //order.setTraderName(tradername);
        //order.setBrokerName("broker1");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> re = restTemplate.postForEntity("http://192.168.2.40:30405/order", order, Boolean.class);
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
        ResponseEntity<Boolean> re = restTemplate.postForEntity("http://192.168.2.40:30405/order", order, Boolean.class);
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

        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id1 = idWorker.nextId();
        order1.setAmount(amount);
        order1.setType(order.getType());
        order1.setTraderName(order.getTraderName());
        order1.setFutureID(order.getFutureID());
        order1.setOrderID(id1);
        order1.setPrice(order.getPrice());
        order1.setPrice2(order.getPrice2());
        order1.setSide(order.getSide());

        long id2 = idWorker.nextId();
        order2.setAmount(amount);
        order2.setType(order.getType());
        order2.setTraderName(order.getTraderName());
        order2.setFutureID(order.getFutureID());
        order2.setOrderID(id2);
        order2.setPrice(order.getPrice());
        order2.setPrice2(order.getPrice2());
        order2.setSide(order.getSide());


        long id3 = idWorker.nextId();
        order3.setAmount(order.getAmount()-2*amount);
        order3.setType(order.getType());
        order3.setTraderName(order.getTraderName());
        order3.setFutureID(order.getFutureID());
        order3.setOrderID(id3);
        order3.setPrice(order.getPrice());
        order3.setPrice2(order.getPrice2());
        order3.setSide(order.getSide());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> re1 = restTemplate.postForEntity("http://192.168.2.40:30405/order", order1, Boolean.class);
        Boolean rst1 = re1.getBody();

        ResponseEntity<Boolean> re2 = restTemplate.postForEntity("http://192.168.2.40:30405/order", order2, Boolean.class);
        Boolean rst2 = re2.getBody();

        ResponseEntity<Boolean> re3 = restTemplate.postForEntity("http://192.168.2.40:30405/order", order3, Boolean.class);
        Boolean rst3 = re3.getBody();

        return !rst1 & !rst2 & !rst3 ;

    }

    @Override
    public Boolean TWAP(Order order, HttpServletRequest request){

        //String futurename = futureRepository.findFutureByFutureID(order.getFutureID()).getFutureName();
        //String expired = futureRepository.findFutureByFutureID(order.getFutureID()).getExpired();

        String url = "http://192.168.2.40:30405/broker_tradehistory?futureName=" + "test" + "&period=" + "test";
        //System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> re =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {
                        });
        Object rst = re.getBody();

        Gson gson = new Gson();
        // 将list集合变成json格式
        String str = gson.toJson(rst);
        //System.out.println(str);
        // 将str json格式变成 list格式
        List<OrderbookItem> list = gson.fromJson(str, new TypeToken<List<OrderbookItem>>() {
        }.getType());

        int total_price = 0;
        int total_num = list.size();
        //System.out.println(total_num);
        for (int i = 0; i < total_num; i++) {
            total_price += list.get(i).price;
        }

        int twap_price = total_price/total_num;

        int amount = order.getAmount()/10; // split into 10 pierces

        Order order_once = new Order();
        order_once.setAmount(amount);
        order_once.setType("m");  // 转成market type
        order_once.setTraderName(order.getTraderName());
        order_once.setFutureID(order.getFutureID());
        order_once.setPrice(twap_price);
        order_once.setPrice2(twap_price);
        order_once.setSide(order.getSide());

        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);


        int count = 0;
        while(count < 10) {
            try {
                if (count == 9){
                    order_once.setAmount(order.getAmount()-9*amount);
                }
                Thread.sleep(6*1000 ); //设置暂停的时间 6 秒,
                //System.out.println(1);

                long id = idWorker.nextId();
                order_once.setOrderID(id);

                ResponseEntity<Boolean> re1 = restTemplate.postForEntity("http://192.168.2.40:30405/order", order_once, Boolean.class);
                Boolean rst1 = re1.getBody();

                count ++;
                } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Boolean VWAP(Order order, HttpServletRequest request){

        //String futurename = futureRepository.findFutureByFutureID(order.getFutureID()).getFutureName();
        //String expired = futureRepository.findFutureByFutureID(order.getFutureID()).getExpired();

        String url = "http://192.168.2.40:30405/broker_tradehistory?futureName=" + "test" + "&period=" + "test";
        //System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> re =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {
                        });
        Object rst = re.getBody();

        Gson gson = new Gson();
        String str = gson.toJson(rst);
        //System.out.println(str);
        // 将str json格式变成 list格式
        List<OrderbookItem> list = gson.fromJson(str, new TypeToken<List<OrderbookItem>>() {
        }.getType());

        int total_price = 0;
        int total_num = list.size();
        int total_qty = 0;
        //System.out.println(total_num);
        for (int i = 0; i < total_num; i++) {
            total_price += list.get(i).price * list.get(i).qty;
            total_qty += list.get(i).qty;
        }

        int vwap_price = total_price/total_qty;

        int amount = order.getAmount()/10; // split into 10 pierces

        Order order_once = new Order();
        order_once.setAmount(amount);
        order_once.setType("m");  // 转成market type
        order_once.setTraderName(order.getTraderName());
        order_once.setFutureID(order.getFutureID());
        order_once.setPrice(vwap_price);
        order_once.setPrice2(vwap_price);
        order_once.setSide(order.getSide());

        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

        int count = 0;
        while(count < 10) {
            try {
                if (count == 9){
                    order_once.setAmount(order.getAmount()-9*amount);
                }
                Thread.sleep(6*1000 ); //设置暂停的时间 6 秒,
                //System.out.println(1);

                long id = idWorker.nextId();
                order_once.setOrderID(id);

                ResponseEntity<Boolean> re1 = restTemplate.postForEntity("http://192.168.2.40:30405/order", order_once, Boolean.class);
                Boolean rst1 = re1.getBody();

                count ++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}

