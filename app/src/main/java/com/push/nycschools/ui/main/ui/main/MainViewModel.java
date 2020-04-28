package com.push.nycschools.ui.main.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.push.nycschools.model.School;
import com.push.nycschools.model.source.SchoolDataSource;
import com.push.nycschools.model.source.SchoolsRepository;
import com.push.nycschools.model.source.remote.SchoolRemoteDataSource;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<School>> allSchool  ;
    private SchoolsRepository schoolsRepository ;

    public  MainViewModel(){
      initRepository();
    }


    public LiveData<List<School>> getAllSchool() {
        if (allSchool== null){
            allSchool = new MutableLiveData<List<School>>();
        }
        loadAllSchool();
        return allSchool;
    }


    private void loadAllSchool(){
      schoolsRepository.getSchools(new SchoolDataSource.LoadSchoolCallBack() {
          @Override
          public void onSchoolsLoaded(@NotNull List<School> Schools) {
           allSchool.setValue(Schools);
          }

          @Override
          public void onDataNotAvailable() {

          }
      });
    }

    private void  initRepository (){
        schoolsRepository = SchoolsRepository.getInstance(SchoolRemoteDataSource.getInstance());

    }

}
