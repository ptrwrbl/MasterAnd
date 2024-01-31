package pollub.cs.ptrwrbl.masterand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pollub.cs.ptrwrbl.masterand.navigation.SetupNavGraph

import pollub.cs.ptrwrbl.masterand.ui.theme.MasterAndTheme
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterAndTheme {
                navController = rememberNavController()


                SetupNavGraph(navController = navController)
            }
        }
    }
}