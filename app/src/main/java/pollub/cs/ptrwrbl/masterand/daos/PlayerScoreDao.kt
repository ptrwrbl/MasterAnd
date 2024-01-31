package pollub.cs.ptrwrbl.masterand.daos

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pollub.cs.ptrwrbl.masterand.models.PlayerWithScore

@Dao
interface PlayerScoreDao {
    @Query("select players.playerId as playerId, scores.scoreId as scoreId, scores.score as score," +
            "players.name as name, players.email as email " +
            "from players, scores where players.playerId = scores.playerId")
    fun getPlayersWithScore(): Flow<List<PlayerWithScore>>
}