package pollub.cs.ptrwrbl.masterand.repositories

import pollub.cs.ptrwrbl.masterand.daos.ScoreDao
import pollub.cs.ptrwrbl.masterand.models.Score

class ScoreRepositoryImpl(private val scoreDao: ScoreDao) : ScoreRepository {
    override suspend fun insertScore(score: Score) {
        scoreDao.insertScore(score)
    }

    override suspend fun updateScore(score: Score) {
        scoreDao.updateScore(score)
    }
}