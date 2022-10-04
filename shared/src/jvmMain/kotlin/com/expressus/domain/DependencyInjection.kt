package com.expressus.domain

import com.expressus.domain.viewModels.ExpressusViewModel
import org.koin.dsl.module

actual fun platformModule() = module {
    factory { ExpressusViewModel() }
}