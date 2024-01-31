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
import pollub.cs.ptrwrbl.masterand.views.GameScreen
import pollub.cs.ptrwrbl.masterand.views.LeaderboardScreen
import pollub.cs.ptrwrbl.masterand.views.LoginScreen
import pollub.cs.ptrwrbl.masterand.views.ProfileScreen

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
            LoginScreen(navController = navController)
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
            GameScreen(
                navController = navController,
                playerId = entry.arguments?.getLong("playerId")!!,
                colors = entry.arguments?.getInt("colors") ?: 5
            )
        }

        composable(
            route = Screen.Profile.route + "/{playerId}/{colors}",
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
            ProfileScreen(
                navController = navController,
                playerId = entry.arguments?.getLong("playerId")!!,
                colors = entry.arguments?.getInt("colors") ?: 5
            )
        }

        composable(
            route = Screen.Leaderboard.route + "/{playerId}/{attempts}/{colors}",
            arguments = listOf(
                navArgument("playerId") { type = NavType.LongType},
                navArgument("attempts"){
                    type = NavType.IntType
                    defaultValue = 0
                },
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
            LeaderboardScreen(
                navController = navController,
                playerId = entry.arguments?.getLong("playerId")!!,
                attempts = entry.arguments?.getInt("attempts") ?: 0,
                colors = entry.arguments?.getInt("colors") ?: 5
            )
        }
    }
}
