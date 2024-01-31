package pollub.cs.ptrwrbl.masterand.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import pollub.cs.ptrwrbl.masterand.repositories.PlayerScoreRepository

class PlayerScoreViewModel(private val playerScoreRepository: PlayerScoreRepository) : ViewModel() {
    val profileWithScore = playerScoreRepository
        .getAllPlayersWithScores()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}