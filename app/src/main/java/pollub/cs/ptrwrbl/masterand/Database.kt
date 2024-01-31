package pollub.cs.ptrwrbl.masterand


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pollub.cs.ptrwrbl.masterand.daos.PlayerDao
import pollub.cs.ptrwrbl.masterand.daos.PlayerScoreDao
import pollub.cs.ptrwrbl.masterand.daos.ScoreDao
import pollub.cs.ptrwrbl.masterand.models.Player
import pollub.cs.ptrwrbl.masterand.models.Score
import kotlin.jvm.Volatile

@Database(
    entities = [Player::class, Score::class],
    version = 1,
    exportSchema = false
)
abstract class HighScoreDatabase: RoomDatabase() {
    abstract fun getPlayerDao(): PlayerDao
    abstract fun getScoreDao(): ScoreDao
    abstract fun getPlayerScoreDao(): PlayerScoreDao
    companion object {
        @Volatile
        private var Instance: HighScoreDatabase? = null
        fun getDatabase(current: Context): HighScoreDatabase {
            return Room.databaseBuilder(
                current,
                HighScoreDatabase::class.java,
                "masterand-database")
                .build().also { Instance = it }
        }
    }
}