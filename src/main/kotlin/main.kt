class PostNotFoundException (message: String) : RuntimeException(message)
class NotesNotFoundException(message: String) : RuntimeException(message)

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

    val notte = Notes("Summer", "Hello!", 2, 1,
        "Yes", "No",1)
    val com = Comment(1, message = "Kot")
    wallService.addNote(notte)
    wallService.createCommentNote(2,com)
    println(com.message)





    println(noteService.getOrNull(1))

}