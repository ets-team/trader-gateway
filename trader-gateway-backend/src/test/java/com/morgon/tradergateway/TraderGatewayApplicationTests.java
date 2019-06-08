package com.morgon.tradergateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 没有(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) websocket test跑不起来
public class TraderGatewayApplicationTests {

	@Test
	public void contextLoads() {
	}

}
