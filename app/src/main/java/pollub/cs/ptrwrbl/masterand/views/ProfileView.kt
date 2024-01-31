package pollub.cs.ptrwrbl.masterand.views

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import pollub.cs.ptrwrbl.masterand.R
import pollub.cs.ptrwrbl.masterand.models.PlayerWithScore
import pollub.cs.ptrwrbl.masterand.navigation.Screen
import pollub.cs.ptrwrbl.masterand.viewmodels.ProfileViewModel
import pollub.cs.ptrwrbl.masterand.viewmodels.ViewModelProvider

@Composable
fun ProfileImage(profileImageUri: Uri?,) {
    if(profileImageUri != null) {
        AsyncImage(
            model = profileImageUri,
            contentDescription = "Profile image",
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    } else {
        Image(
            painter = painterResource(
                id = R.drawable.baseline_question_mark
            ),
            contentDescription = "Profile photo",
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}
@Composable
fun ProfileScreen(
    navController: NavController,
    playerId: Long,
    colors: Int = 6,
    profileScoreViewModel: ProfileViewModel = viewModel(factory = ViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    var playerScore = remember { mutableStateOf<PlayerWithScore>(PlayerWithScore(0L, 0L, 0, "Loading...", "loading@example.net") )}

    LaunchedEffect(colors) {
        coroutineScope.launch {
            // Set a default value before loading
            playerScore.value = PlayerWithScore(0L, 0L, 0, "Loading...", "loading@example.net")

            profileScoreViewModel.loadPlayerScore(playerId)
            playerScore.value = profileScoreViewModel.player
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
                    text = playerScore.value.name,
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            item {
                Text(
                    text = playerScore.value.email,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
            }
            item {
                Text(
                    text = if(playerScore.value.score == null) "Not attempted" else "Best score: ${playerScore.value.score}",
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
            }
            item {
                Button(onClick = {
                    navController.navigate(route = Screen.Game.route + "/${playerId}/${colors}")
                }) { Text(text = "Return to game") }
            }
        }
    }

}