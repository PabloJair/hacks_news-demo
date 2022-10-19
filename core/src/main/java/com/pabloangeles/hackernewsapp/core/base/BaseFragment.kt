package com.pabloangeles.hackernewsapp.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.pabloangeles.hackernewsapp.core.R
import com.pabloangeles.hackernewsapp.core.ui.dialog.DialogError
import com.pabloangeles.hackernewsapp.core.ui.dialog.DialogLoader
import com.pabloangeles.hackernewsapp.core.ui.dialog.DialogSuccess
import com.pabloangeles.hackernewsapp.core.ui.dialog.DialogWarning

abstract class BaseFragment<T : ViewBinding> : Fragment() {
  /* A lazy property that is initialized when it is first used. */
  protected val binding: T by lazy { bindingInflater.invoke(layoutInflater) }

  abstract val bindingInflater: (LayoutInflater) -> T

  /** `setupView()` is an open function that does nothing */
  open fun setupView() {}
  /** > This function is called when the view is created */
  open fun setupObserver() {}
  /** It sets up the view model. */
  open fun setupViewModel() {}
  /** `init()` is an open function that takes no arguments and returns nothing. */
  open fun init() {}

  /**
   * It sets up the view model, observer, view and initializes the view model.
   *
   * @param savedInstanceState Bundle?
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupViewModel()
    setupObserver()
    setupView()
    init()
  }

  /**
   * It returns the root view of the binding object.
   *
   * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
   * @param container The parent that this fragment's UI should be attached to.
   * @param savedInstanceState Bundle?
   */
  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? = binding.root.apply {}

  /**
   * A function that is used to observe the data from the view model.
   *
   * @param fetchData BaseFetchData -> This is the data that is sent from the view model.
   */
  open fun onObserverViewModel(fetchData: BaseFetchData) {
    when (fetchData) {
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
  ) {

    DialogError.showDialog(requireContext(), title, subTitle, textBtn1, onClickBtn1)
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
  ) {

    DialogSuccess.showDialog(
        requireContext(),
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
  ) {

    DialogWarning.showDialog(
        requireContext(), title, subTitle, textBtn1, textBtn2, onClickBtn1, onClickBtn2)
  }

  /** It shows a dialog loader. */
  fun showLoader() = DialogLoader.showDialog(requireContext())
  /** It hides the loader. */
  fun hideLoader() = DialogLoader.hideDialog()
  /**
   * An open function that takes in a parameter of type Any and returns nothing.
   *
   * @param data The data returned from the server.
   */
  open fun onSuccess(data: Any) {}

  /**
   * `onFails` is an open function that takes a string as a parameter and returns nothing
   *
   * @param message The message that is returned from the server.
   */
  open fun onFails(message: String) {}

  /**
   * `onLoader(data: Boolean)`
   *
   * @param data Boolean
   */
  open fun onLoader(data: Boolean) {}
}
