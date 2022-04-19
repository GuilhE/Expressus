@file:Suppress("SpellCheckingInspection")

package com.expressus.android.presentation.ui.screens

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expressus.android.presentation.ui.composables.collectAsStateLifecycleAware
import com.expressus.domain.stateMachines.ExpressusUiState
import com.expressus.domain.viewModels.ExpressusViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ExpressusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = getViewModel<ExpressusViewModel>().also { it.handleSavedState(savedInstanceState != null) }
        with(viewModel) {
            setContent {
                Surface {
                    Expressus(state.collectAsStateLifecycleAware().value) { makeCoffee() }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Expressus(state: ExpressusUiState, makeCoffee: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Crossfade(state.toString()) {
            Text(
                text = it,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
        }
        Spacer(modifier = Modifier.size(50.dp))
        Button(enabled = state.isOnStandBy(), onClick = { makeCoffee() }) {
            Text(text = "Make Coffee")
        }
    }
}

@Composable
@Preview
private fun ExpressusPreview() {
    Expressus(ExpressusUiState()) {}
}