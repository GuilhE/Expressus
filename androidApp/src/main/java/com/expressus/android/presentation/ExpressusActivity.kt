@file:Suppress("SpellCheckingInspection")

package com.expressus.android.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.expressus.compose.components.leftPanel.CoffeeSlot
import com.expressus.compose.components.leftPanel.MachineLeftFrame
import com.expressus.compose.components.rightPanel.CircularButton
import com.expressus.compose.components.rightPanel.Display
import com.expressus.compose.themes.CoffeeSelectorsTheme
import com.expressus.domain.stateMachines.ExpressusUiState
import com.expressus.domain.viewModels.ExpressusViewModel
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ExpressusActivity : AppCompatActivity() {
    private val grindingPlayer: MediaPlayer by lazy { MediaPlayer.create(this, com.expressus.android.R.raw.grinding) }
    private val pouringPlayer: MediaPlayer by lazy { MediaPlayer.create(this, com.expressus.android.R.raw.pouring) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = getViewModel<ExpressusViewModel>().also { it.handleSavedState(savedInstanceState != null) }
        with(viewModel) {
            setContent {
                Surface {
                    val state = state.collectAsStateLifecycleAware().value
                    Expressus(state) { makeCoffee() }
                    when {
                        state.isGrinding -> {
                            pouringPlayer.apply {
                                stop()
                                prepare()
                            }
                            grindingPlayer.start()
                            (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).let {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    it.vibrate(
                                        VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE)
                                    )
                                } else {
                                    @Suppress("DEPRECATION")
                                    it.vibrate(5000)
                                }
                            }
                        }
                        state.isPouring -> {
                            grindingPlayer.apply {
                                stop()
                                prepare()
                            }
                            pouringPlayer.start()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Expressus(state: ExpressusUiState, makeCoffee: () -> Unit) {
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val maxWidth = this.maxWidth
        val maxHeight = this.maxHeight
        MachineLeftFrame(Modifier.fillMaxSize()) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                CoffeeSlot(maxWidth, 70.dp, 150.dp, DpSize(15.dp, maxHeight), 2L, state.isGrinding, state.isPouring)
                Display(modifier = Modifier.padding(50.dp), text = state.label())
                CoffeeSelectorsTheme {
                    CircularButton(size = 70.dp, makeCoffee)
                }
            }
        }
    }
}

@Composable
@Preview
private fun ExpressusPreview() {
    Expressus(ExpressusUiState()) {}
}

@Composable
private fun <STATE : Any> StateFlow<STATE>.collectAsStateLifecycleAware(): State<STATE> {
    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlowLifecycleAware = remember(this, lifecycleOwner) {
        this.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    // Need to access the initial value to convert to State - collectAsState() suppresses this lint warning too
    @SuppressLint("StateFlowValueCalledInComposition")
    val initialValue = this.value
    return stateFlowLifecycleAware.collectAsState(initialValue)
}