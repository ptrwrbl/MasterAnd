package pollub.cs.ptrwrbl.masterand.repositories

import kotlinx.coroutines.flow.Flow
import pollub.cs.ptrwrbl.masterand.models.Score

interface ScoreRepository {
    fun getScoreByPlayerId(playerId: Long): Flow<Score>
    suspend fun insertScore(score: Score): Long
    suspend fun updateScore(score: Score)

}