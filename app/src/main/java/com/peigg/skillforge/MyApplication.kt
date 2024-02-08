/*
Este archivo contiene HiltApplication, que es una clase que extiende de Application y que
es necesaria para poder utilizar Hilt en la aplicación.
 */

package com.peigg.skillforge


import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application()
