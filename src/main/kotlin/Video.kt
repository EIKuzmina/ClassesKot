data class VideoAttachment(val video : Video ): Attachment {
    override val type: String = "video"
}

data class Video (
    val title : String,
    val description : String,
    val duration : Int,
    val views : Int,
    val comments : Int,
)