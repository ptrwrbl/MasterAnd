package pollub.cs.ptrwrbl.masterand.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pollub.cs.ptrwrbl.masterand.navigation.Screen
import pollub.cs.ptrwrbl.masterand.viewmodels.LeaderboardViewModel
import pollub.cs.ptrwrbl.masterand.viewmodels.ViewModelProvider


@Composable
fun LeaderboardScreen(
    navController: NavController,
    playerId: Long,
    attempts: Int = 0,
    colors: Int = 6,
    leaderboardScoreViewModel: LeaderboardViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(colors) {
        coroutineScope.launch {
            leaderboardScoreViewModel.loadLeaderboard()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Leaderboard",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            item {
                Text(
                    text = "Recent score: $attempts",
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
            }
            items(leaderboardScoreViewModel.leaderboard.size) { i ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(48.dp),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(leaderboardScoreViewModel.leaderboard[i].name)
                    Text(leaderboardScoreViewModel.leaderboard[i].score.toString())
                }
            }
            item {
                Button(onClick = {
                    navController.navigate(route = Screen.Game.route + "/${playerId}/${colors}")
                }) { Text(text = "Return to game") }
            }
        }
    }

}