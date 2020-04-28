package com.push.nycschools.model.source.remote;

import android.util.Log;

import com.push.nycschools.model.School;
import com.push.nycschools.model.apiservice.SchoolService;
import com.push.nycschools.model.source.SchoolDataSource;
import com.push.nycschools.utils.AppExecutors;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SchoolRemoteDataSource implements SchoolDataSource {

    private SchoolService schoolService;
    private static AppExecutors appExecutors;
    private static SchoolRemoteDataSource remoteDataSource;

    public static SchoolRemoteDataSource getInstance() {
        if (remoteDataSource != null) return remoteDataSource;
        remoteDataSource = new SchoolRemoteDataSource();
        appExecutors = new AppExecutors();
        return remoteDataSource;
    }

    private SchoolRemoteDataSource() {
        schoolServiceBuilder();
    }


    @Override
    public void getSchools(@NotNull final LoadSchoolCallBack callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {

                    Call<List<School>> listSchool = schoolService.getListSchool();
                    final Response<List<School>> response = listSchool.execute();
                    final List<School> schoolList = response.body();


                    appExecutors.getMainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            if (schoolList != null && !schoolList.isEmpty())
                                callback.onSchoolsLoaded(schoolList);
                            else callback.onDataNotAvailable();
                        }
                    });


                } catch (Exception e) {
                    callback.onDataNotAvailable();
                }

            }
        };

        appExecutors.getNetworkIO().execute(runnable);
    }


    private void schoolServiceBuilder() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("$$app_token", "your-actual-api-key")
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SchoolService.Companion.getAPI_BASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                //.client(okHttpClient)
                .build();
        schoolService = retrofit.create(SchoolService.class);

    }
}
