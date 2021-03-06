package com.perfect.commons.bdlogin;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by baizz on 2014-11-5.
 * refactor 2015-1-8
 */
@Component("baiduLoginHandler")
public class BaiduHttpLoginHandler extends AbstractBaiduHttpClient {

    private static String login_js = null;

    private String castk;


    public boolean login(String username, String password, String imagecode, String cookies) throws IOException {
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("appid", "3"));
        formParams.add(new BasicNameValuePair("button2", "登录"));
        formParams.add(new BasicNameValuePair("entered_login", username));
        formParams.add(new BasicNameValuePair("entered_password", password));
        formParams.add(new BasicNameValuePair("entered_imagecode", imagecode));
        formParams.add(new BasicNameValuePair("tpl", "www2"));
        formParams.add(new BasicNameValuePair("fromu", "http://www2.baidu.com/"));

        HttpPost httpPost = new HttpPost(LOGIN_URL);
        httpPost.addHeader("Cookie", cookies);
        httpPost.setHeader("Accept", ACCEPT);
        httpPost.setHeader("Content-Type", CONTENT_TYPE);
        httpPost.setEntity(new UrlEncodedFormEntity(formParams, StandardCharsets.UTF_8));

        try (CloseableHttpClient httpClient = createHttpClient()) {
            // login check
            boolean isChecked;
            String redirectUrl = null;
            try (CloseableHttpResponse loginResponse = httpClient.execute(httpPost)) {
                if (loginResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = loginResponse.getEntity();
                    String body = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                    String data = Jsoup.parse(body).body().data();
                    String url = data.substring(data.indexOf("\"") + 1, data.lastIndexOf("\""));
                    isChecked = url.contains("action=check");
                    if (isChecked) {
                        redirectUrl = url;
                        int first = url.indexOf("castk") + 8;
                        int last = url.lastIndexOf("&");
                        castk = url.substring(first, last);
                    } else {
                        return false;
                    }
                }
            }

            // redirect
            HttpGet redirectRequest = new HttpGet(redirectUrl);
            BaiduHttp.headerWrap(redirectRequest);
            try (CloseableHttpResponse redirectResponse = httpClient.execute(redirectRequest)) {
                return redirectResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
            }
        }

    }

    public boolean execute(String username, String password, String imagecode, String cookies) throws IOException {
        return login(username, password, imagecode, cookies);
    }

    public static String getBaiduLoginJSPath() {
        if (login_js == null) {
            try {
                login_js = JavascriptFileHelper.getJavascriptPath().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return login_js;
    }

    public String getCastk() {
        return castk;
    }
}