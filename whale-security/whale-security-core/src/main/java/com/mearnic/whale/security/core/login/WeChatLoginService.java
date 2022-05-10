package com.mearnic.whale.security.core.login;

public interface WeChatLoginService {
    String getOpenIdByCode(String code);
}
