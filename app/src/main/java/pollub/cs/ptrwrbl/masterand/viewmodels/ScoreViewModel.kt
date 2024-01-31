package pollub.cs.ptrwrbl.masterand.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pollub.cs.ptrwrbl.masterand.models.Score
import pollub.cs.ptrwrbl.masterand.repositories.ScoreRepository

class ScoreViewModel(private val scoreRepository: ScoreRepository) : ViewModel() {
    var playerId by mutableStateOf(0L)
    var score by mutableStateOf(1)
        private set

    suspend fun insertScore() {
        scoreRepository.insertScore(Score(playerId = playerId, score = score))
    }

    fun updateScore(newScore: Int) {
        score = newScore
    }
}