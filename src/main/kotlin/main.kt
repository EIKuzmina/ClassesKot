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

data class Comment(
    val id: Int = 0, val fromId: Int = 0, val date: Int = 0,
    val text: String = "Yra", val donutIsDon: Boolean = true,
)

data class Likes(
    val count: Int = 0, val userLikes: Boolean = true, val canLike: Boolean = true, val canPublish: Boolean = false
)

interface Attachment {
    val type: String
}

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

class PostNotFoundException (message: String) : RuntimeException(message)

object wallService {
    private var posts = emptyArray<Post>()
    private var idNum = 0
    private var comments = emptyArray<Comment>()
    private var postId = 0
    fun add(post: Post): Post {
        posts += post.copy(id = ++ idNum)
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

    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts) {
            if (post.id == postId) {
                comments += comment
                return comments.last()
            }
        }
        throw PostNotFoundException ("No post with $idNum")
    }

    fun clear() {
        posts = emptyArray()
        idNum = 0
        comments = emptyArray()
        postId = 0
    }
}

fun main() {
    val attachment = AudioAttachment(Audio("Sting","Yra!",
        2,3,12))
    val post = Post(
        1, 2, 12, "Yra!", "copy", true, null, null)
    val delete = if (post.canDelete !== null) post.canDelete else post
    val canPins = post.canPin ?: post.canPin
    val comment = Comment(2,6)
    wallService.add(post)
    wallService.createComment(1,comment)

    println(delete)
    println(canPins)
    println(attachment)

    println(comment.fromId)
}