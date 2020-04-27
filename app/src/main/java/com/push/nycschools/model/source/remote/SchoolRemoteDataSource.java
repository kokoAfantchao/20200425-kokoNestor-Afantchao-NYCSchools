package com.push.nycschools.model.source.remote;

import com.push.nycschools.model.School;
import com.push.nycschools.model.apiservice.SchoolService;
import com.push.nycschools.model.source.SchoolDataSource;
import com.push.nycschools.utils.AppExecutors;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SchoolService.Companion.getAPI_BASE_URL())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        schoolService = retrofit.create(SchoolService.class);
    }
}
