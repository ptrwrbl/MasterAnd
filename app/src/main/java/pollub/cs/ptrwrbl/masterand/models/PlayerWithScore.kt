package pollub.cs.ptrwrbl.masterand.models

import android.net.Uri

data class PlayerWithScore(
    val scoreId: Long,
    val playerId: Long,
    val score: Int,
    val name: String,
    val email: String,
    val profileImageUri: Uri?
)