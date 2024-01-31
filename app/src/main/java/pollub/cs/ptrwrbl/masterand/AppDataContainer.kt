package pollub.cs.ptrwrbl.masterand

import android.content.Context

import pollub.cs.ptrwrbl.masterand.repositories.PlayerRepository
import pollub.cs.ptrwrbl.masterand.repositories.PlayerRepositoryImpl
import pollub.cs.ptrwrbl.masterand.repositories.PlayerScoreRepository
import pollub.cs.ptrwrbl.masterand.repositories.PlayerScoreRepositoryImpl
import pollub.cs.ptrwrbl.masterand.repositories.ScoreRepository
import pollub.cs.ptrwrbl.masterand.repositories.ScoreRepositoryImpl


interface AppContainer {
    val playerRepository: PlayerRepository
    val scoreRepository: ScoreRepository
    val playerScoreRepository: PlayerScoreRepository
}
class AppDataContainer(private val context: Context) : AppContainer {
    override val playerRepository: PlayerRepository by lazy {
        PlayerRepositoryImpl(HighScoreDatabase.getDatabase(context).getPlayerDao())
    }

    override val scoreRepository: ScoreRepository by lazy {
        ScoreRepositoryImpl(HighScoreDatabase.getDatabase(context).getScoreDao())
    }

    override val playerScoreRepository: PlayerScoreRepository by lazy {
        PlayerScoreRepositoryImpl(HighScoreDatabase.getDatabase(context).getPlayerScoreDao())
    }
}