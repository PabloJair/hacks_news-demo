package com.pabloangeles.hackernewsapp.core.ui.dialog

import android.content.Context
import com.pabloangeles.hackernewsapp.core.R
import com.pabloangeles.hackernewsapp.core.base.BaseDialog

class DialogWarning(context: Context, txtTitle: String, txtSubTitle: String, txtButton1: String, txtButton2: String) :
    BaseDialog(
        context,
        DialogParams(title = txtTitle, txtSubTitle, txtButton1,txtButton2 , R.raw.question_dialog)){

    companion object{
        /* A variable that will be used to store the dialog that is being shown. */
        private var dialog: DialogWarning? = null

        /**
         * It shows a dialog with two buttons.
         *
         * @param context Context
         * @param txtTitle The title of the dialog
         * @param txtSubTitle The text that will be displayed in the dialog.
         * @param txtButton1 The text of the first button.
         * @param txtButton2 String,
         * @param onClickButton1 The function to be executed when the first button is clicked.
         * @param onClickButton2 (() -> Unit)?
         */
        fun showDialog(context: Context,
                              txtTitle: String,
                              txtSubTitle: String,
                              txtButton1: String,
                              txtButton2: String,
                       onClickButton1:(() -> Unit)?,
                       onClickButton2:(() -> Unit)?
        )  {
            if (dialog!=null && dialog?.isShowing!!) {
                dialog = try {
                    dialog?.dismiss()
                    null
                } catch (e: Exception) {
                    null
                }
            }
            dialog = DialogWarning(context, txtTitle, txtSubTitle, txtButton1,txtButton2)
            dialog?.let {
                it.onClickBtn1 = onClickButton1
                it.onClickBtn2 = onClickButton2

                it.show()
            }
        }
    }
}
