package pollub.cs.ptrwrbl.masterand.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pollub.cs.ptrwrbl.masterand.views.GameScreenInitial
import pollub.cs.ptrwrbl.masterand.views.LoginScreenInitial
import pollub.cs.ptrwrbl.masterand.views.ProfileScreenInitial

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route ){

        composable(
            route = Screen.Login.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700, easing = EaseIn)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700, easing = EaseOut)
                )
            }
        ){
            LoginScreenInitial(navController = navController)
        }

        composable(
            route = Screen.Game.route + "/{playerId}/{colors}",
            arguments = listOf(
                navArgument("playerId") { type = NavType.LongType},
                navArgument("colors"){
                    type = NavType.IntType
                    defaultValue = 5
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ){ entry ->
            GameScreenInitial(
                navController = navController,
                playerId = entry.arguments?.getLong("playerId")!!,
                colors = entry.arguments?.getInt("colors") ?: 5
            )
        }

        composable(
            route = Screen.Profile.route + "/{profileId}",
            arguments = listOf(
                navArgument("playerId") { type = NavType.LongType},
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ){ entry ->
            ProfileScreenInitial(
                navController = navController,
                playerId = entry.arguments?.getLong("playerId")!!,
            )
        }
    }
}
