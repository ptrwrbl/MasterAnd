package pollub.cs.ptrwrbl.masterand.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import pollub.cs.ptrwrbl.masterand.models.Score

@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertScore(score: Score)

    @Update
    suspend fun updateScore(score: Score)

}