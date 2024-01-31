package pollub.cs.ptrwrbl.masterand.views

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import pollub.cs.ptrwrbl.masterand.R
import pollub.cs.ptrwrbl.masterand.navigation.Screen

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
fun Email(email: String) {
    Text(text = "Email: $email")
}

@Composable
fun Username(username: String) {
    Text(
        text = username,
        modifier = Modifier
                .padding(bottom = 15.dp)
    )
}

@Composable
fun ProfileScreenInitial(
    navController: NavController,
    playerId: Long,
    colors: Int = 6
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Username(username = "test")
        Email(email = "test@test.com")
        Button(onClick = {
            navController.navigate(route = Screen.Game.route + "${playerId}/${colors}")
        }) { Text(text = "Return to game") }
    }

}