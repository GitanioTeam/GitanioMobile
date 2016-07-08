package developers.gitanio.es.gitanio.controller;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import developers.gitanio.es.gitanio.model.AppUserConfig;

/**
 * Created by pedro on 05/07/16.
 */
public class TokenService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String email = /*intent.getStringExtra("email")*/"user";
        String senha = /*intent.getStringExtra("senha")*/"password";
        HttpHeaders token = getToken(email, senha);

        AppUserConfig.getInstance().setToken(token);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SGIT","On destroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private HttpHeaders getToken(String email, String senha){

        HttpHeaders token = null;

        try {
            HttpAuthentication authHeader = new HttpBasicAuthentication(email, senha);

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAuthorization(authHeader);
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            token = requestHeaders;

        } catch (Exception e){
            e.printStackTrace();
        }

        return(token);
    }

}
