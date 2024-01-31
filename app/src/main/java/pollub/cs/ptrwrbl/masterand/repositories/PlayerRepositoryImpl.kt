package pollub.cs.ptrwrbl.masterand.repositories

import kotlinx.coroutines.flow.Flow
import pollub.cs.ptrwrbl.masterand.daos.PlayerDao
import pollub.cs.ptrwrbl.masterand.models.Player

class PlayerRepositoryImpl(private val playerDao: PlayerDao) : PlayerRepository {
    override fun getAllPlayersStream(): Flow<List<Player>> {
        return playerDao.getAllPlayers()
    }

    override fun getPlayerById(playerId: Long): Flow<Player> {
        return playerDao.getPlayerById(playerId)
    }

    override fun getPlayersByEmail(email: String): Flow<Player> {
        return playerDao.getPlayerByEmail(email)
    }

    override suspend fun insertPlayer(player: Player) : Long {
        return playerDao.insertPlayer(player)
    }

    override suspend fun updatePlayer(player: Player) {
        playerDao.updatePlayer(player)
    }

    override suspend fun deletePlayer(player: Player) {
        playerDao.deletePlayer(player)
    }
}