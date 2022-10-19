package com.pabloangeles.hackernewsapp.core.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.pabloangeles.hackernewsapp.core.R
import com.pabloangeles.hackernewsapp.core.ui.dialog.DialogError
import com.pabloangeles.hackernewsapp.core.ui.dialog.DialogLoader
import com.pabloangeles.hackernewsapp.core.ui.dialog.DialogSuccess
import com.pabloangeles.hackernewsapp.core.ui.dialog.DialogWarning

/* `BaseActivity` is an abstract class that takes in a type parameter of type `ViewBinding` and returns
a type `T` */
abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    /* A function that takes in a parameter of type LayoutInflater and returns a type T. */
    abstract val bindingInflater: (LayoutInflater) -> T

    /* A lazy property that is initialized when it is first used. */
    protected val binding: T by lazy {bindingInflater.invoke(layoutInflater)  }

    /**
     * It sets up the app bar with the title passed in.
     *
     * @param toolbar The toolbar that you want to set up.
     * @param title The title of the toolbar
     */
    open fun setupAppBar(toolbar: Toolbar, title: String){
        toolbar.title =title
        setSupportActionBar(toolbar)
    }

    /**
     * `setupView()` is an open function that does nothing
     */

    open fun setupView(){}

    /**
     * It sets up the view model.
     */
    open fun setupViewModel(){}

    /**
     * `init()` is an open function that takes no parameters and returns nothing.
     */
    open fun init(){}

    /**
     * It sets the content view to the root of the binding object.
     */
    private fun setupConfig(){
        setContentView(requireNotNull(binding.root))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupConfig()
        setupView()
        setupViewModel()
        init()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

    }

    /**
     * A function that is used to observe the data from the view model.
     *
     * @param fetchData BaseFetchData
     */
    open fun onObserverViewModel(fetchData: BaseFetchData){
        when(fetchData){
            is BaseFetchData.Error -> onFails(fetchData.message)
            is BaseFetchData.Loader -> onLoader(fetchData.isShow)
            is BaseFetchData.Success -> onSuccess(fetchData.data)
        }

    }

    /**
     * It shows a dialog with an error message.
     *
     * @param title The title of the dialog
     * @param subTitle The text that will be displayed in the body of the dialog.
     * @param textBtn1 String = getString(R.string.accept),
     * @param onClickBtn1 (() -> Unit)? = null,
     */
    fun showError(
        title: String,
        subTitle: String,
        textBtn1: String = getString(R.string.accept),
        onClickBtn1: (() -> Unit)? = null,
    ){

        DialogError.showDialog(
            this,
            title,
            subTitle,
            textBtn1,
            onClickBtn1)
    }

    /**
     * It shows a dialog with a title, subtitle, and a button.
     *
     * @param title The title of the dialog
     * @param subTitle The text that will be displayed below the title.
     * @param textBtn1 String = getString(R.string.cancel),
     * @param onClickBtn1 (() -> Unit)? = null,
     */
    fun showSuccess(
        title: String,
        subTitle: String,
        textBtn1: String = getString(R.string.cancel),
        onClickBtn1: (() -> Unit)? = null,
    ){

        DialogSuccess.showDialog(
            this,
            title,
            subTitle,
            textBtn1,
            onClickBtn1,
        )
    }

    /**
     * It shows a warning dialog with two buttons.
     *
     * @param title The title of the dialog
     * @param subTitle The text that will be displayed in the body of the dialog.
     * @param textBtn1 String = getString(R.string.cancel),
     * @param textBtn2 String = getString(R.string.accept),
     * @param onClickBtn1 (() -> Unit)? = null,
     * @param onClickBtn2 (() -> Unit)? = null,
     */
    fun showWarning(
        title: String,
        subTitle: String,
        textBtn1: String = getString(R.string.cancel),
        textBtn2: String = getString(R.string.accept),
        onClickBtn1: (() -> Unit)? = null,
        onClickBtn2: (() -> Unit)? = null,
    ){

        DialogWarning.showDialog(
            this,
            title,
            subTitle,
            textBtn1,
            textBtn2,
            onClickBtn1,
            onClickBtn2
        )
    }

    fun showLoader()=DialogLoader.showDialog(this)
    fun hideLoader()=DialogLoader.hideDialog()

    /**
     * An open function that takes in a parameter of type Any and returns nothing.
     *
     * @param data The data returned from the server.
     */
    open fun onSuccess(data: Any){}

    /**
     * `onFails` is an open function that takes a string as a parameter and returns nothing
     *
     * @param message The message that is returned from the server.
     */
    open fun onFails(message: String){}

    /**
     * `onLoader(data: Boolean)`
     *
     * @param data Boolean
     */
    open fun onLoader(data: Boolean){}

}