@file:OptIn(ExperimentalFoundationApi::class)

package ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.expressus.domain.DependencyInjection
import com.expressus.domain.stateMachines.ExpressusUiState
import com.expressus.domain.viewModels.ExpressusViewModel
import kotlinx.coroutines.delay
import org.koin.core.Koin
import ui.composables.leftPanel.BottomPanel
import ui.composables.leftPanel.CoffeeSlot
import ui.composables.leftPanel.MachineLeftFrame
import ui.composables.leftPanel.TopPanel
import ui.composables.rightPanel.*
import utils.SoundPlayer
import java.io.File

fun main() = application {
    val koin: Koin = DependencyInjection.initKoinAndReturnInstance()
    val viewModel = koin.get<ExpressusViewModel>()
    Window(
        title = "Expressus",
        onCloseRequest = ::exitApplication,
        icon = painterResource("icon.png"),
        state = rememberWindowState(width = 600.dp, height = 1024.dp),
        resizable = false
    ) {
        with(viewModel) {
            Expressus(state.collectAsState().value) { makeCoffee() }
        }
    }
}

@Composable
private fun Expressus(state: ExpressusUiState, makeCoffee: () -> Unit) {
    when {
        state.brewing -> SoundPlayer.playBrewingSound()
        state.pouring -> SoundPlayer.playPouringSound()
    }
    Row {
        LeftPanel(Modifier.weight(3f), state)
        RightPanel(Modifier.weight(2f).padding(top = 10.dp), state, makeCoffee)
    }
}

@Composable
@Preview
private fun ExpressusPreview() {
    Expressus(ExpressusUiState()) {}
}

@Composable
private fun LeftPanel(modifier: Modifier, state: ExpressusUiState) {
    MachineLeftFrame(modifier) {
        Box(Modifier.weight(2.8f)) {
            TopPanel()
        }
        Box(
            Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            CoffeeSlot(Modifier.size(150.dp), state.brewing, state.pouring)
        }
        Box(Modifier.weight(1.5f)) {
            BottomPanel()
        }
    }
}

@Composable
@Preview
private fun LeftPanelPreview() {
    LeftPanel(Modifier, ExpressusUiState())
}

@Composable
private fun RightPanel(modifier: Modifier, state: ExpressusUiState, makeCoffee: () -> Unit) {
    var toggle by remember { mutableStateOf(false) }
    LaunchedEffect(state.isMakingCoffee()) {
        while (state.isMakingCoffee()) {
            toggle = !toggle
            delay(250)
        }
    }

    MachineRightFrame(modifier) {
        Column(
            Modifier.weight(1.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Display(Modifier.padding(20.dp), state.label())
            CoffeeSelectors(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                count = 10,
                toggle = toggle,
                onClick = {
                    if (state.isOnStandBy()) {
                        makeCoffee()
                    }
                }
            )
            PaymentSocket(PaddingValues(horizontal = 40.dp))
        }
        Column(
            Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            FanGrid(Modifier.width(120.dp))
            Spacer(Modifier.size(10.dp))
            FanGrid(Modifier.width(120.dp))
        }
    }
}

@Composable
@Preview
private fun RightPanelPreview() {
    RightPanel(Modifier, ExpressusUiState()) {}
}