package com.pabloangeles.hackernewsapp.home.ui.activities

import android.view.LayoutInflater
import com.pabloangeles.hackernewsapp.core.base.BaseActivity
import com.pabloangeles.hackernewsapp.home.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/* `HomeActivity` is a `BaseActivity` that uses `ActivityHomeBinding` as its `ViewBinding` and inflates
it using `ActivityHomeBinding::inflate` */
@AndroidEntryPoint
class HomeActivity(
    override val bindingInflater: (LayoutInflater) -> ActivityHomeBinding =
        ActivityHomeBinding::inflate
) : BaseActivity<ActivityHomeBinding>()
