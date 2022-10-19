package com.pabloangeles.hackernewsapp

import android.os.CountDownTimer
import android.view.LayoutInflater
import com.pabloangeles.hackernewsapp.core.AppNavigationModule
import com.pabloangeles.hackernewsapp.core.base.BaseActivity
import com.pabloangeles.hackernewsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity(
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate,
) : BaseActivity<ActivityMainBinding>() {
  /* A constant that is used to set the time of the splash screen. */
  private val MILLIS_FEATURE = 8000L
  /* Setting the time of the splash screen. */
  private val COUNT_DOWN_TIMER = 1000L

  /* Injecting the AppNavigationModule class into the MainActivity class. */
  @Inject lateinit var appNavigationModule: AppNavigationModule

  /** A countdown timer that will open the home screen after the countdown is finished. */
  override fun init() {
    object : CountDownTimer(MILLIS_FEATURE, COUNT_DOWN_TIMER) {
          override fun onTick(p0: Long) = Unit

          override fun onFinish() {
            appNavigationModule.openHome()
            finish()
          }
        }
        .start()
  }
}
