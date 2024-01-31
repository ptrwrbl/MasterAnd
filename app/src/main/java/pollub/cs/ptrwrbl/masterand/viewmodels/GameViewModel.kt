package pollub.cs.ptrwrbl.masterand.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.firstOrNull
import pollub.cs.ptrwrbl.masterand.models.Score
import pollub.cs.ptrwrbl.masterand.repositories.ScoreRepository

class GameViewModel(private val scoreRepository: ScoreRepository) : ViewModel() {
    var scoreId by mutableStateOf(0L)
    var playerId by mutableStateOf(0L)
    var score by mutableStateOf(0)

    suspend fun saveScore() {
        if (scoreId == 0L) {
            val scoreToUpdate = scoreRepository.getScoreByPlayerId(playerId).firstOrNull()

            if(scoreToUpdate != null) {
                scoreId = scoreToUpdate.scoreId
                if(score == null || score > scoreToUpdate.score)
                    score = scoreToUpdate.score
            }
        }

        val score = Score(scoreId, playerId, score)

        if (scoreId == 0L) {
            scoreId = scoreRepository.insertScore(score)
        } else {
            scoreRepository.updateScore(score)
        }
    }
}