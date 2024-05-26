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
        val audio1 = Audio("Sting","Volna",3,5,12)
        val audioAt = AudioAttachment(audio1)
        val post = Post(1,1,1,"da","copy",
            true,null,null,arrayOf(audioAt))
        val addId = wallService.add(post)

        assertTrue(addId.id > 0)
    }

    @Test
    fun updateOne() {
        val audio1 = Audio("Sting","Volna",3,5,12)
        val audioAt = AudioAttachment(audio1)
        val post = Post(1,1,1,"da","copy",
            true,null,null, arrayOf(audioAt))
        val upOne = Post(1,1,1,"da","copy",
            true,null,null,arrayOf(audioAt))
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
            true,null,null,arrayOf(videoAt))
        val upOne = Post(2,1,4,"da","copy",
            true,null,null,arrayOf(videoAt))
        wallService.add(upOne)
        val result = wallService.update(upOne)
        
        assertFalse(result)
    }
}