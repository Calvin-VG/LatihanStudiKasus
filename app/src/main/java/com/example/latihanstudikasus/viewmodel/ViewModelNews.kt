package com.example.latihanstudikasus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihanstudikasus.api.ApiClient
import com.example.latihanstudikasus.model.NewsResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelNews: ViewModel() {

    private var liveDataNews : MutableLiveData<List<NewsResponseItem>> = MutableLiveData()

    @JvmName("getLiveDataNews1")
    fun getLiveDataNews(): MutableLiveData<List<NewsResponseItem>> {
        return liveDataNews
    }

    fun getDataNews(){
        ApiClient.instance.getAllDataNews()
            .enqueue(object : Callback<List<NewsResponseItem>> {
                override fun onResponse(
                    call: Call<List<NewsResponseItem>>,
                    response: Response<List<NewsResponseItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataNews.postValue(response.body())
                    }else{
                        liveDataNews.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<NewsResponseItem>>, t: Throwable) {
                    liveDataNews.postValue(null)
                }

            })
    }

}