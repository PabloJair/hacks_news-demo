package com.pabloangeles.hackernewsapp.core.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/* A generic class that takes two parameters, `T` and `U`. `T` is a `ViewBinding` and `U` is a generic
type. It has a `onClickItem` function that takes two parameters and returns nothing. It also has an
abstract function called `setup` that takes one parameter and returns nothing. */
abstract class BaseViewHolder<T: ViewBinding,U>(var binding:T): RecyclerView.ViewHolder(binding.root) {

    /* A function that takes two parameters and returns nothing. */
    var onClickItem:((item:U,view:T)->Unit)?=null

    /* A function that takes one parameter and returns nothing. */
    abstract fun setup(item:U);
}
