package pollub.cs.ptrwrbl.masterand.viewmodels

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.firstOrNull
import pollub.cs.ptrwrbl.masterand.models.Player
import pollub.cs.ptrwrbl.masterand.repositories.PlayerRepository

class LoginViewModel(private val playerRepository: PlayerRepository): ViewModel() {
    var playerId by mutableStateOf(0L)
        private set
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var profileImageUri by mutableStateOf<Uri?>(null)

    suspend fun savePlayer() {
        if (playerId == 0L) {
            val playerToUpdate = playerRepository.getPlayersByEmail(email).firstOrNull()

            if(playerToUpdate != null) {
                playerId = playerToUpdate.playerId
            }
        }

        val player = Player(playerId, name, email)

        if (playerId == 0L) {
            playerId = playerRepository.insertPlayer(player)
        } else {
            playerRepository.updatePlayer(player)
        }
    }
}