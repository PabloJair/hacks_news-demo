package com.pabloangeles.hackernewsapp.core

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


/* A class that is used to navigate between the different screens of the app. */
@Singleton
class AppNavigationModule @Inject constructor(@ApplicationContext val appContext: Context) {

    private  val HOME_MODULE="com.pabloangeles.hackernewsapp.home"
    /**
     * It opens the home screen of the app
     *
     * @param context The context from which you want to open the activity.
     */
    @Singleton
    fun openHome() = open(
        appContext,
        HOME_MODULE
    )


    /**
     * It opens the app.
     *
     * @param context Context
     * @param action The action to be performed.
     */
    private fun open(
        context: Context,
        nameModule: String
    ) =
        context
            .startActivity(
                intentClearTop(
                    internalIntent(
                        context, nameModule
                    )
                )
            )

    /**
     * > This function takes an intent and returns an intent with the flags
     * `Intent.FLAG_ACTIVITY_CLEAR_TOP` and `Intent.FLAG_ACTIVITY_SINGLE_TOP` set
     *
     * @param intent Intent - The intent to be used to start the activity.
     * @return The intent is being returned.
     */
    private fun intentClearTop(intent: Intent): Intent =
         intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    /**
     * It creates an intent with the given action and the package name of the given context
     *
     * @param context The context of the application.
     * @param action The action to be performed.
     */
    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}