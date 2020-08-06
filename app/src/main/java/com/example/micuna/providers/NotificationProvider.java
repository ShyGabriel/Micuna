package com.example.micuna.providers;

import com.example.micuna.modelo.FCMBody;
import com.example.micuna.modelo.FCMResponse;
import com.example.micuna.retrofit.IFCMapi;
import com.example.micuna.retrofit.RetrofitClient;

import retrofit2.Call;

public class NotificationProvider {

    private String url = "https://fcm.googleapis.com";

    public NotificationProvider() {
    }

    public Call<FCMResponse> sendNotification(FCMBody body){
        return RetrofitClient.getClientObject(url).create(IFCMapi.class).send(body);
    }

}
