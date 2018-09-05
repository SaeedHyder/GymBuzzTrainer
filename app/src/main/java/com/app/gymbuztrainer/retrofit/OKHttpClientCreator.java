package com.app.gymbuztrainer.retrofit;


import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.app.gymbuztrainer.BuildConfig;
import com.app.gymbuztrainer.global.AppConstants;
import com.app.gymbuztrainer.helpers.BasePreferenceHelper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OKHttpClientCreator {

    private static NotificationManager mNotifyManager;
    private static NotificationCompat.Builder mBuilder;
    private static BasePreferenceHelper preferenceHelper;

   /* public static OkHttpClient createCustomInterceptorClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader("Accept-Encoding", "identity").build();
            return chain.proceed(request);
        });
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new CustomInterceptor(progressListener));
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();


    }*/

    public static OkHttpClient createCustomInterceptorClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CustomInterceptor(progressListener))
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                                    @Override
                                    public Response intercept(Chain chain) throws IOException {
                                        Request request = chain.request().newBuilder().addHeader("Accept-Encoding", "identity").build();
                                        return chain.proceed(request);
                                    }

                                }

                )
                .build();


        return client;


    }

    public static OkHttpClient createCustomInterceptorClientwithHeader(Context context) {
        preferenceHelper = new BasePreferenceHelper(context);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        if (preferenceHelper.get_TOKEN() != null) {
            AppConstants.HeaderToken = AppConstants.BEARER+" "+preferenceHelper.get_TOKEN();
        } else {
            AppConstants.HeaderToken = "";
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CustomInterceptor(progressListener))
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .addHeader("Accept-Encoding", "identity")
                                .addHeader("Authorization", AppConstants.HeaderToken)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                })
                .build();

        return client;

    }

    public static OkHttpClient createDefaultInterceptorClient(Context context) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse.newBuilder()
                                .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                                .build();
                    }
                })
                .build();


        return client;

    }

    final static ProgressResponseBody.ProgressListener progressListener = new ProgressResponseBody.ProgressListener() {
        @Override
        public void update(long bytesRead, long contentLength, boolean done) {
          //  int percent = (int) ((100 * bytesRead) / contentLength);
        }
    };

}
