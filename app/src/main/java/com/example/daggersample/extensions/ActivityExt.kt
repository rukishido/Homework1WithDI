package com.example.daggersample.extensions

import android.content.Context
import android.widget.Toast

fun Context.showErrorMessage(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
        this,
        getString(resId),
        duration
    ).show()
}