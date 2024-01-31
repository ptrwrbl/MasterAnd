package pollub.cs.ptrwrbl.masterand.repositories

import kotlinx.coroutines.flow.Flow
import pollub.cs.ptrwrbl.masterand.models.PlayerWithScore

interface PlayerScoreRepository {

    fun getPlayerScore(playerId: Long): Flow<PlayerWithScore>
    fun getAllPlayersWithScores(): Flow<List<PlayerWithScore>>
}