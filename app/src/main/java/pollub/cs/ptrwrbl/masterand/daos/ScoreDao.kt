package pollub.cs.ptrwrbl.masterand.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pollub.cs.ptrwrbl.masterand.models.Score

@Dao
interface ScoreDao {
    @Query("select * from scores where playerId = :playerId")
    fun getScoreByPlayerId(playerId: Long): Flow<Score>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertScore(score: Score) : Long

    @Update
    suspend fun updateScore(score: Score)

}