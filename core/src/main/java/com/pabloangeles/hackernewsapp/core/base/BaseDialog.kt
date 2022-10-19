package com.pabloangeles.hackernewsapp.core.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.LinearLayout
import com.pabloangeles.hackernewsapp.core.R
import com.pabloangeles.hackernewsapp.core.databinding.DialogLottieBinding
import com.pabloangeles.hackernewsapp.core.extension.dp
import com.pabloangeles.hackernewsapp.core.ui.dialog.DialogParams

abstract class BaseDialog(context: Context, private val params: DialogParams) : Dialog(context) {

  /* A lazy property. It is only initialized when it is used for the first time. */
  private val binding: DialogLottieBinding by lazy { DialogLottieBinding.inflate(layoutInflater) }
  /* A function that is called when the button is clicked. */
  protected var onClickBtn1: (() -> Unit)? = null
  /* A function that is called when the button is clicked. */
  protected var onClickBtn2: (() -> Unit)? = null
  /* A constant that is used to set the width of the dialog. */
  private  val percentWith: Float = 0.8F

  /**
   * It sets the layout of the dialog.
   *
   * @param savedInstanceState The saved instance state of the dialog.
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(binding.root)
    binding.apply {
      btn1.text = params.textBtn1
      btn1.setOnClickListener {
        onClickBtn1?.invoke()
        dismiss()
      }

      titleDialog.text = params.title
      messageDialog.text = params.subTitle
      iconLottie.setAnimation(params.lottieFile)

      params.textBtn2?.let {
        btn2.text = it
        btn2.visibility = VISIBLE
        btn2.setOnClickListener {
          onClickBtn2?.invoke()
          dismiss()
        }
      }
    }
    val displayMetrics = context.resources.displayMetrics
    val width = (displayMetrics.widthPixels / displayMetrics.density).toInt().dp(context = context)

    window?.apply {
      setLayout((width * percentWith).toInt(), LinearLayout.LayoutParams.WRAP_CONTENT)
      setBackgroundDrawableResource(R.color.white_transparent)
    }
    this.setCanceledOnTouchOutside(true)
    this.setCancelable(false)
  }
}
