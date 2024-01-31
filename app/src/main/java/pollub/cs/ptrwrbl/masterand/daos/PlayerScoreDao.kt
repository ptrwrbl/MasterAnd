package pollub.cs.ptrwrbl.masterand.daos

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pollub.cs.ptrwrbl.masterand.models.PlayerWithScore

@Dao
interface PlayerScoreDao {
    @Query("select scores.scoreId, scores.playerId, scores.score, players.name, players.email " +
            "from players inner join scores on scores.playerId = players.playerId " +
            "where players.playerId = :playerId")
    fun getPlayerScore(playerId: Long): Flow<PlayerWithScore>
    @Query("select scores.scoreId, scores.playerId, scores.score, players.name, players.email " +
            "from players inner join scores on scores.playerId = players.playerId")
    fun getPlayersWithScore(): Flow<List<PlayerWithScore>>
}