package pollub.cs.ptrwrbl.masterand.navigation

sealed class Screen(val route: String) {
    object Login: Screen(route = "login_screen")
    object Game: Screen(route = "game_screen")
    object Profile: Screen(route = "profile_screen")
    object Leaderboard: Screen(route = "leaderboard_screen")

}
