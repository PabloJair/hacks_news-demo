package com.pabloangeles.hackernewsapp.core.base

/* `BaseFetchData` is a sealed class that has three subclasses: `Loader`, `Error` and `Success`. */
sealed class BaseFetchData {
    /**
     * `Loader` is a data class that has a single property `isShow` of type `Boolean`
     * @property {Boolean} isShow - Boolean - This is the property that will be used to show/hide the
     * loader.
     */
    data class Loader(var isShow:Boolean): BaseFetchData()
    /**
     * `Error` is a data class that has a single property `message` of type `String`.
     * @property {String} message - The error message to be displayed to the user.
     */
    data class Error(var message:String): BaseFetchData()
    /**
     * `Success` is a data class that has a single property `data` of type `Any`.
     * @property {Any} data - The data that is returned from the API.
     */
    data class Success(var data:Any): BaseFetchData()
}