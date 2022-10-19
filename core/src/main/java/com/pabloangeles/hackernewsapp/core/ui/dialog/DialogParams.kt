package com.pabloangeles.hackernewsapp.core.ui.dialog

import androidx.annotation.RawRes
import com.pabloangeles.hackernewsapp.core.R

data class DialogParams(     val title: String = "",
                             val subTitle: String = "",
                             val textBtn1: String = "",
                             val textBtn2: String? = null,
                            @RawRes var lottieFile: Int = R.raw.success_dialog)
