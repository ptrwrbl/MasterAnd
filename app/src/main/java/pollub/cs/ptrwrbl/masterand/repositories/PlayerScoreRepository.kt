package pollub.cs.ptrwrbl.masterand.repositories

import kotlinx.coroutines.flow.Flow
import pollub.cs.ptrwrbl.masterand.models.PlayerWithScore

interface PlayerScoreRepository {

    fun getAllPlayersWithScores(): Flow<List<PlayerWithScore>>
}