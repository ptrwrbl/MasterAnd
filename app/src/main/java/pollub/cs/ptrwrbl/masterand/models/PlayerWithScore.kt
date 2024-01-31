package pollub.cs.ptrwrbl.masterand.models

data class PlayerWithScore(
    val scoreId: Long?,
    val playerId: Long,
    var score: Int?,
    val name: String,
    val email: String
)