package com.ralphabounader.eece451project

import models.AppointmentData
import models.UserLogin
import models.UserRegistration
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("api/users/users/register/")
    fun registerUser(@Body userRegistration: UserRegistration): Call<Void>

    @POST("api/users/users/login/")
    fun loginUser(@Body userLogin: UserLogin): Call<ResponseBody>

    @GET("api/patients/")
    fun listPatients(@Header("Authorization") authToken: String): Call<List<String>>

    @GET("api/medical_personnel/")
    fun listMedicalPersonnel(@Header("Authorization") authToken: String): Call<List<String>>

    @POST("api/patients/{pk}/schedule_appointment/")
    fun scheduleAppointment(
        @Header("Authorization") authToken: String,
        @Path("pk") patientId: Int,
        @Body appointmentData: AppointmentData
    ): Call<Void>


}

