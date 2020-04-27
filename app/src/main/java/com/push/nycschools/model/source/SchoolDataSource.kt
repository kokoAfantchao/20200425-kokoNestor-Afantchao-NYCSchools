package com.push.nycschools.model.source

import androidx.annotation.NonNull
import com.push.nycschools.model.School


interface SchoolDataSource  {

        fun getSchools ( callback: LoadSchoolCallBack )

         //TODO Add more logic later

        // improve this with RXjava
        interface LoadSchoolCallBack {

            fun onSchoolsLoaded(Schools: List<School>)

            fun onDataNotAvailable()
        }


}