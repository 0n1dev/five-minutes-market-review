package com.example.sms;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Runner implements ApplicationRunner {

    @Value("${sms.api_key}")
    String apiKey;

    @Value("${sms.api_secret}")
    String apiSecret;

    @Value("${sms.phone_number}")
    String phoneNumber;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println(apiKey);
        Message message = new Message(apiKey, apiSecret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", phoneNumber);    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "휴대폰인증 테스트 메시지 : 인증번호는[084654]입니다.");
        params.put("app_version", "test"); // application name and version

        try {
            JSONObject obj = (JSONObject) message.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
