package pollub.cs.ptrwrbl.masterand.viewmodels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import pollub.cs.ptrwrbl.masterand.MasterAndApplication

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            LoginViewModel(masterAndApplication().container.playerRepository)
        }
        initializer {
            GameViewModel(masterAndApplication().container.scoreRepository)
        }
        initializer {
            ProfileViewModel(masterAndApplication().container.playerScoreRepository)
        }
        initializer {
            LeaderboardViewModel(masterAndApplication().container.playerScoreRepository)
        }
    }
}

fun CreationExtras.masterAndApplication(): MasterAndApplication = (
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MasterAndApplication
        )