package com.pabloangeles.hackernewsapp.core.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.pabloangeles.hackernewsapp.core.network.NetworkResult

abstract class BaseObservable(application: Application): AndroidViewModel(application) {

     /* Creating a MutableLiveData object that will be used to store the loading state of the network
     request. */
     private var loader        = MutableLiveData<Boolean>()

     /* A MutableLiveData object that will be used to store the success state of the network request. */
     private var success       = MutableLiveData<Any>()

     /* A MutableLiveData object that will be used to store the error state of the network request. */
     private var error         = MutableLiveData<String>()

     /**
      * It takes the three LiveData objects and combines them into one MediatorLiveData object
      *
      * @return A MediatorLiveData object that is observing the loader, success, and error LiveData
      * objects.
      */
     open fun fetchData(): MediatorLiveData<BaseFetchData> {
          val mediator = MediatorLiveData<BaseFetchData>()

          mediator.addSource(loader) {
               if (it != null)
                    mediator.value = BaseFetchData.Loader(it)
          }

          mediator.addSource(success) {
               if (it != null)
                    mediator.value = BaseFetchData.Success(it)
          }

          mediator.addSource(error) {

               if (it != null){
                    mediator.value = BaseFetchData.Error(it)
                    }

          }
          return mediator
     }

     /* A function that takes a NetworkResult object as a parameter and sets the value of the
     success, error, and loader LiveData objects based on the type of NetworkResult object that is
     passed
     in. */
     fun <T> setupLiveData(networkResult: NetworkResult<T>){
          when(networkResult){
               is NetworkResult.Success->this.success.value = networkResult.data
               is NetworkResult.Error->this.error.value = networkResult.errorMessage
               is NetworkResult.Loading->this.loader.value = networkResult.isLoading
          }
     }
}
