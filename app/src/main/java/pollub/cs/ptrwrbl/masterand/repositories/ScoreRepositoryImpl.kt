package pollub.cs.ptrwrbl.masterand.repositories

import kotlinx.coroutines.flow.Flow
import pollub.cs.ptrwrbl.masterand.daos.ScoreDao
import pollub.cs.ptrwrbl.masterand.models.Score

class ScoreRepositoryImpl(private val scoreDao: ScoreDao) : ScoreRepository {
    override fun getScoreByPlayerId(playerId: Long): Flow<Score> {
        return scoreDao.getScoreByPlayerId(playerId)
    }
    override suspend fun insertScore(score: Score): Long {
        return scoreDao.insertScore(score)
    }

    override suspend fun updateScore(score: Score) {
        scoreDao.updateScore(score)
    }
}