package com.example.latihanstudikasus.api

import com.example.latihanstudikasus.model.NewsResponseItem
import com.example.latihanstudikasus.model.PostUserResponse
import com.example.latihanstudikasus.model.UserResponseItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("news")
    fun getAllDataNews() : Call<List<NewsResponseItem>>

    @PUT("user/{id}")
    @FormUrlEncoded
    fun updateUser(
        @Path("id")id : String,
        @Field("name")name : String,
        @Field("pass")pass :String,
        @Field("username")username : String,
        @Field("address")adress : String,
        @Field("umur")umur : String,
        @Field("image")image : String
    ): Call<PostUserResponse>

    @GET("user")
    fun getAllDataUser(): Call<List<UserResponseItem>>

    @POST("user")
    fun detailUser(@Field("id") id : Int) : Call<List<UserResponseItem>>

    @POST("user")
    fun postDataUser(@Body reqUser: PostUserResponse) : Call<UserResponseItem>

}