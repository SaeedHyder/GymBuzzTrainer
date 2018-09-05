package com.app.gymbuztrainer.helpers;

import android.content.Context;
import android.util.Log;

import com.app.gymbuztrainer.entities.ResponseWrapper;
import com.app.gymbuztrainer.global.WebServiceConstants;
import com.app.gymbuztrainer.retrofit.WebService;
import com.app.gymbuztrainer.retrofit.WebServiceFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 5/15/2017.
 */

public class TokenUpdater {
    private static final TokenUpdater tokenUpdater = new TokenUpdater();
    private WebService webservice;

    private TokenUpdater() {
    }

    public static TokenUpdater getInstance() {
        return tokenUpdater;
    }



    public void UpdateToken(Context context, String DeviceType, String Token) {

        if (Token==null || Token.isEmpty()) {
            Log.e("Token Updater", "Token is Empty");
        }
        webservice = WebServiceFactory.getWebServiceInstanceWithCustomInterceptorandheader(context,WebServiceConstants.SERVICE_URL);

        Call<ResponseWrapper> call = webservice.updateToken(Token,DeviceType);
        call.enqueue(new Callback<ResponseWrapper>() {
            @Override
            public void onResponse(Call<ResponseWrapper> call, Response<ResponseWrapper> response) {
                if (response!=null && response.body()!=null && response.body().getMessage()!=null)
                    Log.i("UPDATETOKEN", response.body().getMessage() + "");
            }

            @Override
            public void onFailure(Call<ResponseWrapper> call, Throwable t) {
                Log.e("UPDATETOKEN", t.toString());
            }
        });


    }

    /*  public void UpdateToken(Context context, final String userid, String DeviceType, String Token) {
        if (Token.isEmpty()) {
            Log.e("Token Updater", "Token is Empty");
        }
        webservice = WebServiceFactory.getWebServiceInstanceWithCustomInterceptor(context,WebServiceConstants.SERVICE_URL);

       *//* Call<ResponseWrapper> call = webservice.updateToken(userid,Token, DeviceType );
        call.enqueue(new Callback<ResponseWrapper>() {
            @Override
            public void onResponse(Call<ResponseWrapper> call, Response<ResponseWrapper> response) {
                if (response.body()!=null)
                Log.i("UPDATETOKEN", response.body().getResponse() + "" + userid);
            }

            @Override
            public void onFailure(Call<ResponseWrapper> call, Throwable t) {
                Log.e("UPDATETOKEN", t.toString());
            }
        });*//*
    }*/

}
