package com.app.gymbuztrainer.helpers;

import android.util.Log;


import com.app.gymbuztrainer.activities.DockActivity;
import com.app.gymbuztrainer.entities.ResponseWrapper;
import com.app.gymbuztrainer.global.WebServiceConstants;
import com.app.gymbuztrainer.interfaces.webServiceResponseLisener;
import com.app.gymbuztrainer.retrofit.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 7/17/2017.
 */

public class ServiceHelper<T> {
    private webServiceResponseLisener serviceResponseLisener;
    private DockActivity context;
    private WebService webService;

    public ServiceHelper(webServiceResponseLisener serviceResponseLisener, DockActivity conttext, WebService webService) {
        this.serviceResponseLisener = serviceResponseLisener;
        this.context = conttext;
        this.webService = webService;
    }

    public void enqueueCall(Call<ResponseWrapper<T>> call, final String tag) {
        if (InternetHelper.CheckInternetConectivityandShowToast(context)) {
            context.onLoadingStarted();
            call.enqueue(new Callback<ResponseWrapper<T>>() {
                @Override
                public void onResponse(Call<ResponseWrapper<T>> call, Response<ResponseWrapper<T>> response) {
                    context.onLoadingFinished();
                    if (response != null && response.body() != null && response.body().getResponse() != null) {
                        if (response.body().getResponse().equals(WebServiceConstants.SUCCESS_RESPONSE_CODE)) {
                            serviceResponseLisener.ResponseSuccess(response.body().getResult(), tag,response.body().getMessage());
                        } else {
                            serviceResponseLisener.ResponseFailure(tag);
                            UIHelper.showShortToastInCenter(context, response.body().getMessage());
                        }
                    } else {
                        serviceResponseLisener.ResponseFailure(tag);
                        UIHelper.showShortToastInCenter(context, "No response from server");
                    }

                }

                @Override
                public void onFailure(Call<ResponseWrapper<T>> call, Throwable t) {
                    context.onLoadingFinished();
                    t.printStackTrace();
                    serviceResponseLisener.ResponseFailure(tag);
                    Log.e(ServiceHelper.class.getSimpleName() + " by tag: " + tag, t.toString());
                }
            });
        }
    }

}
