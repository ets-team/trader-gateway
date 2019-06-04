package com.morgon.tradergateway.utils;

import com.morgon.tradergateway.repository.BrokerRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author Zhengyu Wu
 * @date 2019/6/2
 * @desciprtion HTTP工具类
 * @version 1.0.0
 **/
@Component
public class HttpUtil {
    /**
     * HTTP GET请求
     * @param url url
     * @param param 参数
     * @param token token
     * @return 字符串结果
     */
    public String sendGet (String url, String param, String token) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = (param != null) ? url + "?" + param : url;
            System.out.println(urlNameString);
            URL readUrl = new URL(urlNameString);
            URLConnection connection = readUrl.openConnection();
            connection.setConnectTimeout(2000);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
            connection.setRequestProperty("authorization", "Bearer " + token);
            connection.connect();

            Map<String, List<String>> map = connection.getHeaderFields();
            /*
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            */
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("get error(most probably manually timeout)" + e);
            return result;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * HTTP POST请求
     * @param url url
     * @param param 参数
     * @return 字符串结果
     */
    public String sendPost (String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            out = new PrintWriter(connection.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


}
