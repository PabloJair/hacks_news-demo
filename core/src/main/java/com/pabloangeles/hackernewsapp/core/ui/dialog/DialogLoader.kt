package com.pabloangeles.hackernewsapp.core.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.pabloangeles.hackernewsapp.core.R
import com.pabloangeles.hackernewsapp.core.databinding.DialogLoaderBinding

class DialogLoader(context: Context) : Dialog(context) {
  private val binding: DialogLoaderBinding by lazy { DialogLoaderBinding.inflate(layoutInflater) }
  /**
   * It sets the background color of the dialog to white_transparent.
   *
   * @param savedInstanceState The saved instance state of the activity.
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    window?.apply {
      setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
      setBackgroundDrawableResource(R.color.white_transparent)
    }
    setCanceledOnTouchOutside(true)
    setCancelable(false)
  }

  companion object {
    /* A variable that is used to store the instance of the dialog. */
    private var loader: DialogLoader? = null
    /**
     * It shows a dialog.
     *
     * @param context The context of the activity from which you want to show the dialog.
     */
    fun showDialog(
        context: Context?,
    ) {
      hideDialog()
      if (context != null) {
        try {
          loader = DialogLoader(context)
          loader?.let { loader -> loader.show() }
        } catch (e: Exception) {
          Log.e("DialogLoader", "Error in showDialog", e)
          e.printStackTrace()
        }
      }
    }

    /** It hides the dialog. */
    fun hideDialog() {
      if (loader != null && loader?.isShowing!!) {
        loader =
            try {
              loader?.dismiss()
              null
            } catch (e: Exception) {
              Log.e("DialogLoader", "Error in hideDialog", e)

              null
            }
      }
    }
  }
}
