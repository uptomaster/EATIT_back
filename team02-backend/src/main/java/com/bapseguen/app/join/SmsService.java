package com.bapseguen.app.join;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsService {
   private static final String API_KEY = "NCSFPXSJZQFES5EK";
   private static final String API_SECRET = "FSNZYZ23TXVPPOJQONCXSNKW6YCH97E4";
   private static final String FROM_NUMBER = "01098863561";

   public String sendVerificationSms(String to) throws CoolsmsException {
      Message coolsms = new Message(API_KEY, API_SECRET);
      String verificationCode = generateVerificationCode();

      HashMap<String, String> params = new HashMap<String, String>();
      params.put("to", to);
      params.put("from", FROM_NUMBER);
      params.put("type", "SMS");
      params.put("text", "인증번호는 [" + verificationCode + "] 입니다.");
      System.out.println("인증코드 생성on");
      JSONObject obj = (JSONObject) coolsms.send(params);
      System.out.println("전송시도 on");
      System.out.println(obj.toString());

      return verificationCode;
   }

   private String generateVerificationCode() {
      Random random = new Random();
      StringBuilder code = new StringBuilder();
      for (int i = 0; i < 6; i++) {
         code.append(random.nextInt(10));
      }
      return code.toString();
   }
}