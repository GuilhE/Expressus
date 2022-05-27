package presentation

import SoundPlayer
import androidx.compose.desktop.ui.tooling.preview.Preview
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
import com.expressus.compose.components.leftPanel.*
import com.expressus.compose.components.rightPanel.*
import com.expressus.domain.DependencyInjection
import com.expressus.domain.stateMachines.ExpressusUiState
import com.expressus.domain.viewModels.ExpressusViewModel
import org.koin.core.Koin

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
        state.isGrinding -> SoundPlayer.playGrindingSound()
        state.isPouring -> SoundPlayer.playPouringSound()
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
        BoxWithConstraints(
            Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            CoffeeSlot(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(10.dp),
                isGrinding = state.isGrinding,
                isPouring = state.isPouring,
                pouringSpeed = 10L,
                slotOffset = 10.dp,
                faucetOffsets = FaucetOffsets(2.dp, 2.dp)
            )
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
            Display(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp),
                text = state.label()
            )
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
            PaymentSocket(Modifier.fillMaxWidth().padding(horizontal = 40.dp))
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