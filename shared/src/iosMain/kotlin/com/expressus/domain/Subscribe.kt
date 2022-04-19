@file:Suppress("unused")

package com.expressus.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

fun Flow<*>.subscribe(
    onEach: (item: Any) -> Unit,
    onComplete: () -> Unit,
    onThrow: (error: Throwable) -> Unit
): Job = this.subscribe(Dispatchers.Main, onEach, onComplete, onThrow)

fun Flow<*>.subscribe(
    dispatcher: CoroutineDispatcher,
    onEach: (item: Any) -> Unit,
    onComplete: () -> Unit,
    onThrow: (error: Throwable) -> Unit
): Job =
    this.onEach { onEach(it as Any) }
        .catch { onThrow(it) }
        .onCompletion { onComplete() }
        .launchIn(CoroutineScope(Job() + dispatcher))