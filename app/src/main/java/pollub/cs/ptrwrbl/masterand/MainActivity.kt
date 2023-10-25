@file:OptIn(ExperimentalMaterial3Api::class)

package pollub.cs.ptrwrbl.masterand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pollub.cs.ptrwrbl.masterand.ui.theme.MasterAndTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterAndTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val name = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val colors = rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MasterAnd",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        Box {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_baseline_question_mark_24),
                contentDescription = "Profile photo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Name") },
            singleLine = true,
            isError = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = { Text("Name can't be empty") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            singleLine = true,
            isError = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = { Text("Email can't be empty") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Enter number of colors") },
            singleLine = true,
            isError = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            supportingText = { Text("") }
        )
        NextButton {  }
    }
}

@Composable
fun NextButton(onClick: () -> Unit) {
    Button(onClick = { onClick() },
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
    ) {
        Text("Next")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    MasterAndTheme {
        ProfileScreen()
    }
}