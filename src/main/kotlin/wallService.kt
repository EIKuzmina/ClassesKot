object wallService {
    private var posts = emptyArray<Post>()
    private var idNum = 0
    private var comments = emptyArray<Comment>()
    private var postId = 0
    private var note = mutableListOf<Notes>()

    fun add(post: Post): Post {
        posts += post.copy(id = ++idNum)
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
        throw PostNotFoundException("No post with $idNum")
    }
    fun addNote(notes: Notes): Notes {
        note += notes.copy(noteId = ++idNum)
        return note.last()
    }
    fun createCommentNote(noteId: Int, comment: Comment): Comment {
        for (notes in note)
            if (notes.noteId == noteId) {
                notes.coments += comment.copy(commentId = ++ idNum)
                return notes.coments.last()
            }
        throw NotesNotFoundException("No notes with $idNum")
    }
    fun delete (noteId: Int) : Int {
        for (notes in note)
            if (notes.noteId == noteId) {
                note.remove(notes)
            return 1
        }
        throw NotesNotFoundException("No notes with $idNum")
    }
    fun deleteComment(noteId: Int, commentId: Int): Int {
        for (notes in note)
            if (notes.noteId == noteId) {
                for (comment in notes.coments) {
                    if (comment.commentId == commentId && !comment.delete) {
                        comment.delete = true
                        return 1
                }
            }
        }
        throw NotesNotFoundException("No notes with $idNum")
    }
    fun editNotes(notesEd: Notes) : Int {
        for ((index, notes) in note.withIndex())
            if (notes.noteId == notesEd.noteId) {
                note[index] = notesEd
                return 2
        }
        throw NotesNotFoundException("No notes with $idNum")
    }
fun editComment (noteId: Int, commentId: Int, newCom : Comment) : Int {
    for (notes in note)
        if (notes.noteId == noteId) {
            for (comment in notes.coments) {
                if (comment.commentId == commentId && !comment.delete) {
                    comment.message = newCom.message
                    return 2
            }
        }
    }
    throw NotesNotFoundException("No notes with $idNum")
}
    fun getNote(): List<Notes> {
        if (note.isNotEmpty()) {
            return note
        }
        throw NotesNotFoundException("No notes with $idNum")
    }

    fun getById(noteId: Int): Notes? {
        for (notes in note) {
            if (notes.noteId == noteId) {
                return notes
            }
        }
        throw NotesNotFoundException("No notes with $idNum")
    }

    fun getComments(noteId: Int): List<Comment>? {
        val coments = mutableListOf<Comment>()
        for (notes in note) {
            if (notes.noteId == noteId) {
                for (comment in notes.coments) {
                    if (!comment.delete) {
                        coments.add(comment)
                    }
                }
                return coments
            }
    }
        throw NotesNotFoundException("No notes with $idNum")
    }

    fun restoreComment (noteId: Int, commentId: Int) : Int {
        for (notes in note)
            if (notes.noteId == noteId) {
                for (comment in notes.coments) {
                    if (comment.commentId == commentId && comment.delete) {
                        comment.delete = false
                        return 1
                    }
                }
            }
        throw NotesNotFoundException("No notes with $idNum")
        }

    fun clear() {
        posts = emptyArray()
        idNum = 0
        comments = emptyArray()
        postId = 0
        note = mutableListOf()
    }
}