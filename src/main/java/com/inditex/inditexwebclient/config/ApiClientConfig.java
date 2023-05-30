package com.inditex.inditexwebclient.config;


import com.inditex.inditexwebclient.clients.ExistProductApi.ExistingApis;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class ApiClientConfig {
    @Bean
    ExistingApis existingApisClient(
            @Value("${webServices.existingApi.url}") String url,
            @Value("${webServices.existingApi.connectTimeout}") long connectTimeout,
            @Value("${webServices.existingApi.readTimeout}") long readTimeout,
            @Value("${webServices.existingApi.writeTimeout}") long writeTimeout
            ) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS);
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(ExistingApis.class);
    }
}
