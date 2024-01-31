package pollub.cs.ptrwrbl.masterand.repositories

import kotlinx.coroutines.flow.Flow
import pollub.cs.ptrwrbl.masterand.models.Player

interface PlayerRepository {
    fun getAllPlayersStream(): Flow<List<Player>>
    fun getPlayerById(playerId: Long): Flow<Player>
    fun getPlayersByEmail(email: String): Flow<Player>
    suspend fun insertPlayer(player: Player) : Long
    suspend fun updatePlayer(player: Player)
    suspend fun deletePlayer(player: Player)
}