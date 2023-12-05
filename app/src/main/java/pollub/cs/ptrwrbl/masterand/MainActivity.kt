@file:OptIn(ExperimentalMaterial3Api::class)

package pollub.cs.ptrwrbl.masterand

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
                    ProfileScreenInitial()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldWithError(value: String, onValueChange: (String) -> Unit, label: String, hasError: Boolean) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text("${label}") },
        singleLine = true,
        isError = hasError,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        supportingText = {
            if(hasError) {
                Text("Błędne dane")
            }
        }
    )
}

@Composable
fun ProfileImageWithPicker(profileImageUri: Uri?, selectImageOnClick: () -> Unit) {
    Box {
        if(profileImageUri != null) {
            AsyncImage(
                model = profileImageUri,
                contentDescription = "Profile image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_baseline_question_mark_24
                ),
                contentDescription = "Profile photo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        IconButton(onClick = selectImageOnClick) {
            Image(
                painter = painterResource(id = R.drawable.baseline_image_search_24),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopStart),
                contentScale = ContentScale.Crop
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenInitial() {
    val name = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("")}
    val colorNumber = rememberSaveable { mutableStateOf("")}
    val profileImageUri = rememberSaveable{
        mutableStateOf<Uri?>(null)
    }
    var isErrorName by rememberSaveable {
        mutableStateOf(false)
    }
    var isErrorEmail by rememberSaveable {
        mutableStateOf(false)
    }
    var isErrorColorNumber by rememberSaveable {
        mutableStateOf(false)
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedUri ->
            if (selectedUri != null) {
                profileImageUri.value = selectedUri
            }
        })

    fun validateName(text: String){
        isErrorName = text.isEmpty()
    }

    fun validateEmail(text: String){
        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")
        isErrorEmail = !emailPattern.matches(text)
    }

    fun validateColorNumber(text: String){
        isErrorColorNumber = (text.toInt() < 5 || text.toInt() > 10)
    }

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
        ProfileImageWithPicker(
            profileImageUri = profileImageUri.value,
            selectImageOnClick = {
                imagePicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            })

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextFieldWithError(
            value = name.value,
            onValueChange = { name.value = it
                validateName(it)},
            label = "Enter Name",
            hasError = isErrorName,
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextFieldWithError(
            value = email.value,
            onValueChange = { email.value = it
                validateEmail(it)},
            label = "Enter email",
            hasError = isErrorEmail,
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextFieldWithError(
            value = colorNumber.value,
            onValueChange = { colorNumber.value = it
                validateColorNumber(it)},
            label = "Enter number of colors",
            hasError = isErrorColorNumber,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Next")

        }

    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MasterAndTheme {
        ProfileScreenInitial()
    }
}