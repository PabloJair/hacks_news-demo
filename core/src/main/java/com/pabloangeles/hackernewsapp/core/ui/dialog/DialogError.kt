package com.pabloangeles.hackernewsapp.core.ui.dialog

import android.content.Context
import com.pabloangeles.hackernewsapp.core.R
import com.pabloangeles.hackernewsapp.core.base.BaseDialog

class DialogError(context: Context, txtTitle: String, txtSubTitle: String, txtButton1: String) :
    BaseDialog(
        context,
        DialogParams(title = txtTitle, txtSubTitle, txtButton1, null, R.raw.error_dialog)) {

  companion object {
    /* A variable that will be used to store the dialog that is currently being displayed. */
    private var dialog: DialogError? = null

    /**
     * It shows a dialog with a title, subtitle, and a button.
     *
     * @param context Context
     * @param txtTitle The title of the dialog
     * @param txtSubTitle The text that will be displayed in the dialog.
     * @param txtButton1 The text of the first button.
     * @param onClickButton1 This is the function that will be called when the button is clicked.
     */
    fun showDialog(
        context: Context,
        txtTitle: String,
        txtSubTitle: String,
        txtButton1: String,
        onClickButton1: (() -> Unit)?,
    ) {
      if (dialog != null && dialog?.isShowing!!) {
        dialog =
            try {
              dialog?.dismiss()
              null
            } catch (e: Exception) {
              null
            }
      }
      dialog = DialogError(context, txtTitle, txtSubTitle, txtButton1)
      dialog?.let {
        it.onClickBtn1 = onClickButton1

        it.show()
      }
    }
  }
}
