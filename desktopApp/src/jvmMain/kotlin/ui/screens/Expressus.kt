@file:OptIn(ExperimentalFoundationApi::class)

package ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.expressus.domain.DependencyInjection
import com.expressus.domain.stateMachines.ExpressusUiState
import com.expressus.domain.viewModels.ExpressusViewModel
import org.koin.core.Koin
import ui.composables.leftPanel.BottomPanel
import ui.composables.leftPanel.CoffeeSlot
import ui.composables.leftPanel.MachineLeftFrame
import ui.composables.leftPanel.TopPanel
import ui.composables.rightPanel.*
import utils.SoundPlayer

fun main() = application {
    val koin: Koin = remember { DependencyInjection.initKoinAndReturnInstance() }
    val viewModel = remember { koin.get<ExpressusViewModel>() }
    var resizable by remember { mutableStateOf(true) }

    Window(
        title = "Expressus",
        onCloseRequest = ::exitApplication,
        icon = painterResource("icon.png"),
        state = rememberWindowState(size = DpSize(600.dp, 1024.dp)),
        resizable = resizable
    ) {
        with(viewModel) {
            Expressus(state.collectAsState().value, onRendered = { resizable = false }) { makeCoffee() }
        }
    }
}

@Composable
private fun Expressus(state: ExpressusUiState, onRendered: () -> Unit, makeCoffee: () -> Unit) {
    when {
        state.grinding -> SoundPlayer.playGrindingSound()
        state.pouring -> SoundPlayer.playPouringSound()
    }
    Row {
        LeftPanel(Modifier.weight(3f), state)
        RightPanel(Modifier.weight(2f).padding(top = 10.dp), state, makeCoffee)
    }
    LaunchedEffect(Unit) { onRendered() }
}

@Composable
@Preview
private fun ExpressusPreview() {
    Expressus(ExpressusUiState(), {}, {})
}

@Composable
private fun LeftPanel(modifier: Modifier, state: ExpressusUiState) {
    MachineLeftFrame(modifier) {
        Box(
            Modifier.weight(2.8f),
            contentAlignment = Alignment.Center
        ) {
            TopPanel()
        }
        Box(
            Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            CoffeeSlot(Modifier.size(150.dp), state.grinding, state.pouring)
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
    MachineRightFrame(modifier) {
        Column(
            Modifier.weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Display(Modifier.padding(20.dp), state.label())
            CoffeeSelectors(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                count = 7,
                isMakingCoffee = state.isMakingCoffee(),
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
    RightPanel(Modifier, state = ExpressusUiState()) {}
}