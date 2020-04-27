package com.push.nycschools.model.apiservice

import com.push.nycschools.model.School
import retrofit2.http.GET



interface SchoolService {
    companion object {

        val API_BASE_URL ="https://data.cityofnewyork.us/resource/"
    }

    @GET("/f9bf-2cp4.json")
    fun getListSchool() :retrofit2.Call<List<School>>
}

