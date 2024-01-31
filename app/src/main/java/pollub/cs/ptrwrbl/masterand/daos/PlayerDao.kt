package pollub.cs.ptrwrbl.masterand.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pollub.cs.ptrwrbl.masterand.models.Player

@Dao
interface PlayerDao {

    @Query("select * from players")
    fun getAllPlayers(): Flow<List<Player>>

    @Query("select * from players where playerId = :playerId")
    fun getPlayerById(playerId: Long): Flow<Player>

    @Query("select * from players where email = :email")
    fun getPlayerByEmail(email: String): Flow<Player>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlayer(profile: Player) : Long

    @Update
    suspend fun updatePlayer(profile: Player)

    @Delete
    suspend fun deletePlayer(profile: Player)
}