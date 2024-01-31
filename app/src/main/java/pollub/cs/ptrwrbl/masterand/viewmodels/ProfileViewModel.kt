package pollub.cs.ptrwrbl.masterand.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.firstOrNull
import pollub.cs.ptrwrbl.masterand.models.PlayerWithScore
import pollub.cs.ptrwrbl.masterand.repositories.PlayerScoreRepository

class ProfileViewModel(private val playerScoreRepository: PlayerScoreRepository) : ViewModel() {
    var player by mutableStateOf<PlayerWithScore>(PlayerWithScore(0L, 0L, 0, "Not loaded", "not@loaded.bug"))

    suspend fun loadPlayerScore(playerId: Long) {
        var playerToShow = playerScoreRepository.getPlayerScore(playerId).firstOrNull()

        if(playerToShow != null) {
            player = playerToShow
            player.score = playerToShow.score ?: 0
        }
    }
}