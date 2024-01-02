package pollub.cs.ptrwrbl.masterand

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "game_screen" ){

        composable(route = Screen.Login.route){
            LoginScreenInitial(navController = navController)
        }

        composable(route = Screen.Game.route){
            GameScreenInitial(navController = navController)
        }

        composable(route = Screen.Profile.route){
            ProfileScreenInitial(navController = navController)
        }
    }
}