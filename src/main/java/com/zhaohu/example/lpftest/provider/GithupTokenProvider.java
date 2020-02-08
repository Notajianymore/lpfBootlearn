package com.zhaohu.example.lpftest.provider;

import com.alibaba.fastjson.JSON;
import com.zhaohu.example.lpftest.dto.GitHupAccessTokenDto;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithupTokenProvider {
    public String getGithupToken(GitHupAccessTokenDto accessTokenDto) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String json = JSON.toJSONString(accessTokenDto);
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            String[] firstSplit = res.split("&");
            String[] sndSplit = firstSplit[0].split("=");
            String token = sndSplit[1];
            System.out.println("token"+token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithupUser getGithupUser(String accessToken) {
        if(accessToken == null || accessToken.length() == 0){
            return null;
        }
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            System.out.println(res);
            GithupUser user = JSON.parseObject(res, GithupUser.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
