package com.rika.wxspringbootbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxJsCodeResp {
    private String openid;
    private String unionid;
    private String errmsg;
    private String session_key;
    private int errcode;
}
