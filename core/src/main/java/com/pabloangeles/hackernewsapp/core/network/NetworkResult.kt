package com.pabloangeles.hackernewsapp.core.network

/* A sealed class is a class that can be subclassed, but only inside the file it's defined */
sealed class NetworkResult<T>(var data: T? = null,
                              var errorMessage: String? = null,
                              val isLoading: Boolean =false
){
    /* A Success class that extends NetworkResult and takes a generic type T. */
    class Success<T>(data: T) : NetworkResult<T>(data)

    /* `Error` is a subclass of `NetworkResult` that takes a `String` and an optional `T` and returns a
    `NetworkResult` with the `String` as the message and the `T` as the data */
    class Error<T>(errorMessage: String, data: T? = null) : NetworkResult<T>(data, errorMessage)

    /* `Loading` is a subclass of `NetworkResult` that is used to indicate that the network request is
    in progress */
    class Loading<T> (isLoading: Boolean) : NetworkResult<T>(isLoading = isLoading)
}