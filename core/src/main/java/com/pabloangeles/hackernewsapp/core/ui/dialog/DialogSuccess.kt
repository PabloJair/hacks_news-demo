package com.pabloangeles.hackernewsapp.core.ui.dialog

import android.content.Context
import com.pabloangeles.hackernewsapp.core.R
import com.pabloangeles.hackernewsapp.core.base.BaseDialog

class DialogSuccess(context: Context, txtTitle: String, txtSubTitle: String, txtButton1: String) :
    BaseDialog(
        context,
        DialogParams(title = txtTitle, txtSubTitle, txtButton1, null, R.raw.success_dialog)){
    companion object{
            private var dialog: DialogSuccess? = null

            /**
             * It shows a dialog with a title, subtitle, and a button.
             *
             * @param context Context
             * @param txtTitle The title of the dialog
             * @param txtSubTitle The text that will be displayed in the dialog's body.
             * @param txtButton1 The text of the first button.
             * @param onClickListener This is the function that will be called when the button is
             * clicked.
             */
            fun showDialog(context: Context,
                                  txtTitle: String,
                                  txtSubTitle: String,
                                  txtButton1: String,
                                  onClickListener:(() -> Unit)?
            )  {
                if (dialog!=null && dialog?.isShowing!!) {
                    dialog = try {
                        dialog?.dismiss()
                        null
                    } catch (e: Exception) {
                        null
                    }
                }
                dialog = DialogSuccess(context, txtTitle, txtSubTitle, txtButton1)
                dialog?.let {
                    it.onClickBtn1 = onClickListener
                    it.show()
                }
            }
        }
    }
