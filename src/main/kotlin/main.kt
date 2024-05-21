data class Post(
    val id: Int = 0, val ownerId: Int = 0, val date: Int = 0,
    val text: String = "Meow", val postType: String = "suggest",
    val canDelete: Boolean = true, val isFavorite: Boolean = true
)

data class Likes(
    val count: Int = 0, val userLikes: Boolean = true,
    val canLike: Boolean = true, val canPublish: Boolean = false
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
        val post = Post()
        val likes = Likes()
        val like = likes.copy(count = likes.count + 2)
        val (_, _, _, _, postType) = post
        val (_, _, canLike) = likes
        val objects = wallService

        println(post.text)
        println(like)
        println(post)
        println("$postType, $canLike")
        println(
            objects.add(
                Post(
                    1, 2,12, "Yra!", "copy", false,
                    true
                )
            )
        )
        println(objects.update(Post(2)))
    }
