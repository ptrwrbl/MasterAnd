package pollub.cs.ptrwrbl.masterand.viewmodels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import pollub.cs.ptrwrbl.masterand.MasterAndApplication

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            PlayerViewModel(masterAndApplication().container.playerRepository)
        }
        initializer {
            ScoreViewModel(masterAndApplication().container.scoreRepository)
        }
        initializer {
            PlayerScoreViewModel(masterAndApplication().container.playerScoreRepository)
        }
    }
}

fun CreationExtras.masterAndApplication(): MasterAndApplication = (
        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MasterAndApplication
        )