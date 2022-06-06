package com.example.latihanstudikasus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihanstudikasus.api.ApiClient
import com.example.latihanstudikasus.model.PostUserResponse
import com.example.latihanstudikasus.model.UserResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser: ViewModel() {

    private var liveDataLoginUser = MutableLiveData<List<UserResponseItem>>()
    private var liveDataUpdateUser = MutableLiveData<PostUserResponse>()

    init {
        liveDataLoginUser = MutableLiveData()
        liveDataUpdateUser = MutableLiveData()
    }

    fun getLiveLogin(): MutableLiveData<List<UserResponseItem>> {
        return liveDataLoginUser
    }
    fun getLiveUpdate() : MutableLiveData<PostUserResponse> {
        return liveDataUpdateUser
    }

    fun loginUserAPI(){
        ApiClient.instance.getAllDataUser()
            .enqueue(object : Callback<List<UserResponseItem>> {
                override fun onResponse(call: Call<List<UserResponseItem>>, response: Response<List<UserResponseItem>>) {
                    if (response.isSuccessful){
                        liveDataLoginUser.postValue(response.body())
                    }else{
                        liveDataLoginUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<UserResponseItem>>, t: Throwable) {
                    liveDataLoginUser.postValue(null)
                }

            })
    }

    fun updateUserAPI(id :Int, name : String,pass:String, username: String, address: String,
                      umur : String, image:String){
        ApiClient.instance.updateUser(id.toString(), name,pass, username, address, umur,image)
            .enqueue(object : Callback<PostUserResponse> {
                override fun onResponse(call: Call<PostUserResponse>, response: Response<PostUserResponse>) {
                    if (response.isSuccessful){
                        liveDataUpdateUser.postValue(response.body())
                    }else{
                        liveDataUpdateUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<PostUserResponse>, t: Throwable) {
                    liveDataUpdateUser.postValue(null)
                }

            })
    }

    fun addNewUserApi(alamat: String, image: String, umur: Int, username: String,
                      password: String, name: String): Boolean{
        var messageResponse = false
        ApiClient.instance.postDataUser(
            PostUserResponse(alamat, image, name, password, umur, username)
        ).enqueue(object : Callback<UserResponseItem> {
            override fun onResponse(
                call: Call<UserResponseItem>,
                response: Response<UserResponseItem>
            ) {
                messageResponse = response.isSuccessful
            }

            override fun onFailure(call: Call<UserResponseItem>, t: Throwable) {
                messageResponse = false
            }

        })
        return messageResponse
    }

}