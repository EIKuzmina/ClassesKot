import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class wallServiceTest {

    @Before
    fun clearBeforeTest() {
        wallService.clear()
    }

    @Test
    fun add() {
        val attachment = AudioAttachment(
            Audio(
                "Sting", "Volna",
                3, 5, 12
            )
        )
        val post = Post(
            1, 1, 1, "da", "copy",
            true, null, null
        )
        val addId = wallService.add(post)

        assertTrue(addId.id > 0)
    }

    @Test
    fun updateOne() {
        val attachment = AudioAttachment(
            Audio(
                "Sting", "Volna",
                3, 5, 12
            )
        )
        val post = Post(
            1, 1, 1, "da", "copy",
            true, null, null
        )
        val upOne = Post(
            1, 1, 1, "da", "copy",
            true, null, null
        )
        wallService.add(post)
        wallService.add(upOne)
        val result = wallService.update(upOne)

        assertTrue(result)
    }

    @Test
    fun updateTwo() {
        val video1 = Video("Sting", "Volna", 3, 5, 12)
        val videoAt = VideoAttachment(video1)
        val post = Post(
            1, 1, 1, "da", "copy",
            true, null, null
        )
        val upOne = Post(
            2, 1, 4, "da", "copy",
            true, null, null
        )
        wallService.add(upOne)
        val result = wallService.update(upOne)

        assertFalse(result)
    }

    @Test
    fun createComment() {
        val post1 = Post(
            1, 1, 1, "da", "copy",
            true, null, null
        )
        val comment1 = Comment(2, 6)
        val expected = 6
        wallService.add(post1)
        wallService.createComment(1, comment1)
        val result = comment1.fromId
        assertEquals(expected, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val comment2 = Comment(3, 7)
        wallService.createComment(4, comment2)
    }

    @Test
    fun addNote() {
        val notte = Notes(
            title = "Moscow", text = "Yes", 1, 5,
            "No", "Meow", 14
        )
        val result = wallService.addNote(notte)
        assertEquals(1, result.noteId)
    }

    @Test
    fun createCommentNote() {
        val note1 = Notes(
            title = "Kot", text = "Yes", 1, 5,
            "No", "Meow", 1
        )
        val comment = Comment(1, message = "Kot")
        val expected = "Kot"
        wallService.addNote(note1)
        wallService.createCommentNote(1, comment)
        val result = comment.message
        assertEquals(expected, result)
    }

    @Test(expected = NotesNotFoundException::class)
    fun createCommentExc() {
        val comment = Comment(3, message = "Op")
        wallService.createCommentNote(0, comment)
    }

    @Test
    fun deleteNote() {
        val note = Notes(
            title = "Moscow", text = "Yes", 1, 5,
            "No", "Meow", 14
        )
        wallService.addNote(note)
        val result = wallService.delete(1)
        assertEquals(1, result)
    }

    @Test(expected = NotesNotFoundException::class)
    fun deleteNoteEx() {
        wallService.delete(2)
    }

    @Test
    fun deleteComment() {
        val note1 = Notes(
            title = "Moscow", text = "Yes", 1, 5, "No",
            "Meow", 14
        )
        val comment3 = Comment(1, message = "Yra")
        wallService.addNote(note1)
        wallService.createCommentNote(1, comment3)
        val result = wallService.deleteComment(1, 2)
        assertEquals(1, result)
    }

    @Test(expected = NotesNotFoundException::class)
    fun deleteCommentEx() {
        wallService.deleteComment(2, 3)
    }

    @Test
    fun editNotes() {
        val note = Notes(
            title = "Moscow", text = "Piter", 1, 5, "No",
            "Meow", 14
        )
        wallService.addNote(note)
        val note1 = Notes(
            title = "Moscow", text = "Sochi", 2, 5,
            "No", "Meow", 1
        )
        val result = wallService.editNotes(note1)
        assertEquals(2, result)
    }

    @Test(expected = NotesNotFoundException::class)
    fun editNoteEx() {
        val note = Notes(
            title = "Moscow", text = "Piter", 1, 5,
            "No", "Meow", 1
        )
        wallService.addNote(note)
        val note1 = Notes(
            title = "Moscow", text = "Sochi", 2, 5,
            "No", "Meow", 2
        )
        wallService.editNotes(note1)
    }

    @Test
    fun editComment() {
        val note = Notes(
            title = "Moscow", text = "Piter", 1, 5,
            "No", "Meow", 14
        )
        val comment = Comment(1, message = "Kot")
        wallService.addNote(note)
        wallService.createCommentNote(1, comment)
        val result = wallService.editComment(1, 2,comment.copy(message = "Da", delete = false) )
        assertEquals(2, result)
    }

    @Test(expected = NotesNotFoundException::class)
    fun editCommentEx() {
        val newComment = Comment(1, message = "Top", delete = false)
        wallService.editComment(1, 1, newComment)
    }

    @Test
    fun getNote() {
        val note = Notes(
            title = "Moscow", text = "Piter", 1, 5,
            "No", "Meow", 14
        )
        val note1 = Notes(
            title = "Moscow", text = "Sochi", 2, 5,
            "No", "Meow", 1
        )

        wallService.addNote(note)
        wallService.addNote(note1)
        val expected = listOf(note.copy(noteId = 1), note1.copy(noteId = 2))
        val result = wallService.getNote()
        assertEquals(expected, result)
    }

    @Test(expected = NotesNotFoundException::class)
    fun getEx() {
        wallService.getNote()
    }

    @Test
    fun getById() {
        val note = Notes(
            title = "Moscow", text = "Piter", 1, 5,
            "No", "Meow", 1
        )
        wallService.addNote(note)
        val result = wallService.getById(1)
        assertEquals(note, result)
    }

    @Test(expected = NotesNotFoundException::class)
    fun getByIdEx() {
        wallService.getById(1)
    }

    @Test
    fun getComments() {
        val note = Notes(
            title = "Moscow", text = "Piter", 1, 5,
            "No", "Meow", 14
        )
        val comment = Comment(2, message = "Kot")
        wallService.addNote(note)
        wallService.createCommentNote(1, comment)
        val expected = listOf(comment.copy(commentId = 2))
        val result = wallService.getComments(1)
        assertEquals(expected, result)
    }

    @Test(expected = NotesNotFoundException::class)
    fun getCommentsEx() {
        wallService.getComments(1)
    }

    @Test
    fun restoreComment() {
        val note = Notes(
            title = "Moscow", text = "Piter", 1, 5,
            "No", "Meow", 2
        )
        val comment = Comment(1, message = "Kot", delete = true)
        wallService.addNote(note)
        wallService.createCommentNote(1, comment)
        val result = wallService.restoreComment(1, 2)
        assertEquals(1, result)
    }

    @Test(expected = NotesNotFoundException::class)
    fun restoreCommentEx() {
        val note = Notes(
            title = "Moscow", text = "Piter", 1, 5,
            "No", "Meow", 14
        )
        val comment = Comment(1, message = "Kot",delete = true)
        wallService.addNote(note)
        wallService.createCommentNote(1, comment)
        wallService.restoreComment(2, 1)
    }
}