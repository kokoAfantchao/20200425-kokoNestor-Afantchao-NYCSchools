package com.push.nycschools.model

import com.google.gson.annotations.SerializedName

data class School(
    @SerializedName("dbn") var dbn : String,
    @SerializedName("school_name")var school_name: String,
    @SerializedName("boro")var  boro  : String,
    @SerializedName("location")var  location  :String
)
//"dbn": "01M292",
//"school_name": "HENRY STREET SCHOOL FOR INTERNATIONAL STUDIES",
//"num_of_sat_test_takers": "29",
//"sat_critical_reading_avg_score": "355",
//"sat_math_avg_score": "404",
//"sat_writing_avg_score": "363"