package com.employeemanagement.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private  var retrofit: Retrofit? = null
    private val BASE_URL = " http://dummy.restapiexample.com/api/v1/"

    /**
     * Create an instance of Retrofit object
     */
    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

    fun getAPIService(): ApiServices {
        val service = getRetrofitInstance()!!.create(ApiServices::class.java)
        return service
    }
}