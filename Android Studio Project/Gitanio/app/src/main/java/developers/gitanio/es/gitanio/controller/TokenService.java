package developers.gitanio.es.gitanio.controller;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;

import developers.gitanio.es.gitanio.model.AnDebugger;
import developers.gitanio.es.gitanio.model.DicionarioURL;
import developers.gitanio.es.gitanio.services.JsonConverter;

/**
 * Created by pedro on 05/07/16.
 */
public class TokenService extends Service {

    private OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        String email = intent.getStringExtra("email");
//        String senha = intent.getStringExtra("senha");
//        String token = getToken(email, senha);

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

    private String getToken(String email, String senha){

        String token = null;

        try {

            HttpAuthentication authHeader = new HttpBasicAuthentication(email, senha);
        Log.d("SGIT","Ok");
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAuthorization(authHeader);

            AnDebugger.getInstance().setDeb(authHeader);
            JSONObject jsonUserId = JsonConverter.getAutenticacao(email,senha);

            String userId = jsonUserId.toString();
            String linkUrl = DicionarioURL.GET_LOGIN_URL + "/" + userId;

            Request request = new Request.Builder().url(linkUrl).build();
            Response response = client.newCall(request).execute();

            String json = response.body().string();
            token = JsonConverter.getToken(json);

        } catch (Exception e){
            e.printStackTrace();
        }

        return(token);
    }

}
