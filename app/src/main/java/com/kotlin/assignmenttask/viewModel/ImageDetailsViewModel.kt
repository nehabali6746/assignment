package com.kotlin.assignmenttask.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.kotlin.assignmenttask.MyApplication
import com.kotlin.assignmenttask.R
import com.kotlin.assignmenttask.repo.RepoistoryClass
import com.kotlin.circleapisdemo.utils.Event
import com.kotlin.circleapisdemo.utils.Resource
import com.kotlin.circleapisdemo.utils.Utils
import com.kotlin.finaltarget.data.image.ImageDetail
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class ImageDetailsViewModel (application: Application, val repoistoryClass: RepoistoryClass) :
    ViewModel() {

    val mutableLiveData=MutableLiveData<Event<Resource<ImageDetail>>>()
    val liveData: LiveData<Event<Resource<ImageDetail>>> = mutableLiveData

    fun thread()= viewModelScope.launch {
        createUser()
    }

    private suspend fun createUser (){
        mutableLiveData.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(MyApplication())) {
                val response = repoistoryClass.getImagesList()

                    mutableLiveData.postValue(handlePicsResponse(response))

            } else {
                mutableLiveData.postValue(Event(Resource.Error(MyApplication().getString(
                        R.string.no_internet_connection))))
            }
        }  catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    mutableLiveData.postValue(
                            Event(Resource.Error(
                                  "No Internet connection.Please retry"
                            ))
                    )
                }
                else -> {
                    mutableLiveData.postValue(
                            Event(Resource.Error(
                                    "conversion conterol......"
                            ))


                    )
                }
            }
        }
    }



    private fun handlePicsResponse(response: Response<ImageDetail>): Event<Resource<ImageDetail>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }
}