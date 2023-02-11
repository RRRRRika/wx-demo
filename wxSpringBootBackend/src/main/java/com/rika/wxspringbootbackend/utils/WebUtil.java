package com.rika.wxspringbootbackend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rika.wxspringbootbackend.entity.User;
import com.rika.wxspringbootbackend.entity.WxJsCodeResp;
import com.rika.wxspringbootbackend.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebUtil {

    private final String WXURL = "https://api.weixin.qq.com/sns/jscode2session";
    private final String APPID = "***********";
    private final String SECRET = "*************";
    private final String GRANT_TYPE = "authorization_code";

    public WxJsCodeResp getOpenID(String jsCode) {
        String url = WXURL +
                "?appid=" + APPID +
                "&secret=" + SECRET +
                "&js_code=" + jsCode +
                "&grant_type=" + GRANT_TYPE +
                "&connect_redirect=1";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        try {
            CloseableHttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(EntityUtils.toString(entity), WxJsCodeResp.class);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
