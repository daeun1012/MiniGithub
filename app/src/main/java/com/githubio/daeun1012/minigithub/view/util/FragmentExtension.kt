package com.githubio.daeun1012.minigithub.view.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

fun <T : ViewModel> Fragment.vm(factory: ViewModelProvider.Factory, model: KClass<T>): T {
    return ViewModelProviders.of(this, factory).get(model.java)
}
