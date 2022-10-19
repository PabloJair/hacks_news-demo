package com.pabloangeles.hackernewsapp.core.extension

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

 /* A function that takes a context and returns an Int. */
 fun Int.dp(context: Context) =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, toFloat(), context.resources.displayMetrics)
            .roundToInt()