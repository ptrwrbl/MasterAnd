package pollub.cs.ptrwrbl.masterand.viewmodels

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import pollub.cs.ptrwrbl.masterand.models.Player
import pollub.cs.ptrwrbl.masterand.repositories.PlayerRepository

class PlayerViewModel(private val playerRepository: PlayerRepository): ViewModel() {
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
                if(profileImageUri == null && playerToUpdate.profileImageUri != null)
                    profileImageUri = playerToUpdate.profileImageUri
            }
        }

        val player = Player(playerId, name, email, profileImageUri)

        if (playerId == 0L) {
            playerId = playerRepository.insertPlayer(player)
        } else {
            playerRepository.updatePlayer(player)
        }
    }


    fun getAllPlayers(): List<Player> {
        var all : List<Player> = emptyList()
        viewModelScope.launch {
            val allPlayersStream = playerRepository.getAllPlayersStream()
            all = allPlayersStream.first()
        }
        return all
    }

    suspend fun getPlayerById(playerId: Long) {
        val player = playerRepository.getPlayerById(playerId).firstOrNull()

        if(player != null) {
            this.playerId = player.playerId
            this.name = player.name
            this.email = player.email
            this.profileImageUri = player.profileImageUri
        }
    }
}