data class Comment(
    val id: Int = 0, val fromId: Int = 0, val date: Int = 0,
    var text: String = "Yra", val donutIsDon: Boolean = true,
    var message: String ="Yes", val commentId: Int? = null, var delete: Boolean = false
)