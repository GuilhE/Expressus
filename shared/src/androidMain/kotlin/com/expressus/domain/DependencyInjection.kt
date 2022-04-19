package com.expressus.domain

import com.expressus.domain.viewModels.ExpressusViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual fun platformModule() = module {
    viewModel { ExpressusViewModel() }
}
