package com.wintig.rpc;

import com.wintig.rpc.remote.SendSMS;
import com.wintig.rpc.remote.vo.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RpcClientApplicationTests {

	@Autowired
	private SendSMS sendSMS;

	@Test
	public void rpcTest() {

		sendSMS.sendMail(new UserInfo("wintig", "18224045695"));

	}

}
