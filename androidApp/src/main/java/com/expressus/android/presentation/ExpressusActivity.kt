@file:Suppress("SpellCheckingInspection")

package com.expressus.android.presentation

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.expressus.compose.components.ExpressusMobile
import com.expressus.domain.stateMachines.ExpressusUiState
import com.expressus.domain.viewModels.ExpressusViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

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
                    val state = state.collectAsStateWithLifecycle().value
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

@Composable
private fun Expressus(state: ExpressusUiState, makeCoffee: () -> Unit) {
    with(state) {
        ExpressusMobile(
            isGrinding = isGrinding,
            isPouring = isPouring,
            isMakingCoffee = isMakingCoffee(),
            status = label(),
            makeCoffee = { makeCoffee() })
    }
}