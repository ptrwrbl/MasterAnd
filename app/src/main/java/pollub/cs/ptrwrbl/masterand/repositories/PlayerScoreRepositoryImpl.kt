package pollub.cs.ptrwrbl.masterand.repositories

import kotlinx.coroutines.flow.Flow
import pollub.cs.ptrwrbl.masterand.daos.PlayerScoreDao
import pollub.cs.ptrwrbl.masterand.models.PlayerWithScore

class PlayerScoreRepositoryImpl(private val playerScoreDao: PlayerScoreDao) : PlayerScoreRepository {
    override fun getPlayerScore(playerId: Long): Flow<PlayerWithScore> {
        return playerScoreDao.getPlayerScore(playerId)
    }
    override fun getAllPlayersWithScores(): Flow<List<PlayerWithScore>> {
        return playerScoreDao.getPlayersWithScore()
    }
}