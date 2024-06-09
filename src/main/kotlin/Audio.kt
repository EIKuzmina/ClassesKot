data class AudioAttachment(val audio : Audio): Attachment {
    override val type: String = "audio"
}

data class Audio (
    val artist : String,
    val title : String,
    val albumId : Int,
    val duration : Int,
    val genreId : Int
)