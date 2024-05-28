data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val date: Int = 0,
    val text: String = "Meow",
    val postType: String = "suggest",
    val isFavorite: Boolean = true,
    val canDelete: Post?,
    val canPin: Post?,
    val attachments: Array<Attachment>? = emptyArray()
)

data class Likes(
    val count: Int = 0, val userLikes: Boolean = true, val canLike: Boolean = true, val canPublish: Boolean = false
)

interface Attachment {
    val type: String
}

class AudioAttachment(addAudio: Audio): Attachment {
    override val type: String = "audio"
    val audio = addAudio
}

class Audio (
    val artist : String,
    val title : String,
    val albumId : Int,
    val duration : Int,
    val genreId : Int
)

class VideoAttachment(addVideo : Video) : Attachment {
    override val type: String = "video"
    val video = addVideo
}

class Video (
    val title : String,
    val description : String,
    val duration : Int,
    val views : Int,
    val comments : Int,
)


object wallService {
    private var posts = emptyArray<Post>()
    private var idNum = 0
    fun add(post: Post): Post {
        idNum += 1
        posts += post.copy(id = idNum)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postAll) in posts.withIndex()) {
            if (post.id == postAll.id) {
                posts[index] = post.copy(id = postAll.id)
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        idNum = 0
    }
}

fun main() {
    val audio1 = Audio("Sting","Volna",3,5,12)
    val audioAt = AudioAttachment(audio1)
    val post = Post(
        1, 2, 12, "Yra!", "copy", true, null, null, arrayOf(audioAt)
    )
    val delete = if (post.canDelete !== null) post.canDelete else post
    val canPins = post.canPin ?: post.canPin

    println(delete)
    println(canPins)
    println(audioAt)
}