@file:Suppress("SpellCheckingInspection")

package com.expressus.android.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.*
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.expressus.android.presentation.components.MachineFrame
import com.expressus.compose.components.leftPanel.CoffeeSlot
import com.expressus.compose.components.leftPanel.FaucetOffsets
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
        WindowCompat.setDecorFitsSystemWindows(window, false)

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
                            vibrate(this@ExpressusActivity)
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

private fun vibrate(context: Context) {
    with(context) {
        val milliseconds = 5000L
        val amplitude = 50

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator.vibrate(
                VibrationEffect.createOneShot(milliseconds, amplitude)
            )
        } else {
            @Suppress("DEPRECATION")
            (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    it.vibrate(VibrationEffect.createOneShot(milliseconds, amplitude))
                } else {
                    it.vibrate(milliseconds)
                }
            }
        }
    }
}

@Composable
private fun Expressus(state: ExpressusUiState, makeCoffee: () -> Unit) {
    MachineFrame(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CoffeeSlot(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(10.dp),
                isGrinding = state.isGrinding,
                isPouring = state.isPouring,
                pouringSpeed = 2L,
                slotOffset = 50.dp,
                faucetOffsets = FaucetOffsets(10.dp, 10.dp)
            )
            Spacer(Modifier.size(50.dp))
            Display(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 50.dp),
                text = state.label()
            )
            Spacer(Modifier.size(50.dp))
            CoffeeSelectorsTheme {
                CircularButton(size = 70.dp, makeCoffee)
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