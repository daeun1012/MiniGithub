package com.githubio.daeun1012.minigithub.view.util

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

fun <T : ViewModel> FragmentActivity.vm(factory: ViewModelProvider.Factory, model: KClass<T>): T {
    return ViewModelProviders.of(this, factory).get(model.java)
}
