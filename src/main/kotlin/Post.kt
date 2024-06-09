data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val date: Int = 0,
    val text: String = "Meow",
    val postType: String = "suggest",
    val isFavorite: Boolean = true,
    val canDelete: Post?,
    val canPin: Post?
)