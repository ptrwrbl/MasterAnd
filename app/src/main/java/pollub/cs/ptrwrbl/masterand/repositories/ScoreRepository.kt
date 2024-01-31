package pollub.cs.ptrwrbl.masterand.repositories

import pollub.cs.ptrwrbl.masterand.models.Score

interface ScoreRepository {
    suspend fun insertScore(score: Score)
    suspend fun updateScore(score: Score)

}