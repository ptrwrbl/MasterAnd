package pollub.cs.ptrwrbl.masterand.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import pollub.cs.ptrwrbl.masterand.models.PlayerWithScore
import pollub.cs.ptrwrbl.masterand.repositories.PlayerScoreRepository

class LeaderboardViewModel(private val playerScoreRepository: PlayerScoreRepository) : ViewModel() {
    var leaderboard = mutableStateListOf<PlayerWithScore>()

    suspend fun loadLeaderboard() {
        val playerWithScoresFlow = playerScoreRepository.getAllPlayersWithScores()

        playerWithScoresFlow.collect { playerWithScores ->
            val leaderboardCopy = leaderboard.toMutableList()
            leaderboardCopy.addAll(playerWithScores)
            leaderboard.clear()
            leaderboard.addAll(leaderboardCopy)
        }
    }
}