package com.pabloangeles.hackernewsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/* The `@HiltAndroidApp` annotation tells Hilt to generate a `Hilt_HackerNewsApplication` class that
extends `Hilt_Application` and implements `HackerNewsApplication_GeneratedInjector` */
@HiltAndroidApp class HackerNewsApplication : Application()
