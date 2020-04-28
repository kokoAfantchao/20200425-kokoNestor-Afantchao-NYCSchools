package com.push.nycschools.model.apiservice

import com.push.nycschools.model.School
import retrofit2.Call
import retrofit2.http.GET



interface SchoolService {
    companion object {
        val API_BASE_URL ="https://data.cityofnewyork.us/resource/"
    }

    @GET("s3k6-pzi2.json")
    fun getListSchool() : Call<List<School>>
}

