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
        val attachment = AudioAttachment(Audio("Sting","Volna",
            3,5,12))
        val post = Post(1,1,1,"da","copy",
            true,null,null)
        val addId = wallService.add(post)

        assertTrue(addId.id > 0)
    }

    @Test
    fun updateOne() {
        val attachment = AudioAttachment(Audio("Sting","Volna",
            3,5,12))
        val post = Post(1,1,1,"da","copy",
            true,null,null)
        val upOne = Post(1,1,1,"da","copy",
            true,null,null)
        wallService.add(post)
        wallService.add(upOne)
        val result = wallService.update(upOne)

        assertTrue(result)
    }

    @Test
    fun updateTwo() {
        val video1 = Video("Sting","Volna",3,5,12)
        val videoAt = VideoAttachment(video1)
        val post = Post(1,1,1,"da","copy",
            true,null,null)
        val upOne = Post(2,1,4,"da","copy",
            true,null,null)
        wallService.add(upOne)
        val result = wallService.update(upOne)
        
        assertFalse(result)
    }
    @Test
    fun createComment() {
        val post1 = Post(1,1,1,"da","copy",
            true,null,null)
        val comment1 = Comment(2,6)
        val expected = 6
        wallService.add(post1)
        wallService.createComment(1,comment1)
        val result = comment1.fromId
        assertEquals(expected, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val comment2 = Comment(3,7)
        wallService.createComment(4,comment2)
    }
}