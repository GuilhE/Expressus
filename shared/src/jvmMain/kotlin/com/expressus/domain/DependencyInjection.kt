package com.expressus.domain

import com.expressus.domain.viewModels.ExpressusViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual fun platformModule() = module {
    factory { ExpressusViewModel() }
}

object ViewModels : KoinComponent {
    fun expressusStateViewModel() = get<ExpressusViewModel>()
}