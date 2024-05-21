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
        val post = Post(0)
        val addId = wallService.add(post)

        assertNotEquals(0, addId.id)
    }

    @Test
    fun updateOne() {
        val post = Post(0)
        wallService.add(post)
        val upOne = Post(1)
        val result = wallService.update(upOne)

        assertEquals(true, result)
    }

    @Test
    fun updateTwo() {
        val post = Post(2)
        wallService.add(post)
        val upOne = wallService.update(post)
        
        assertEquals(false, upOne)
    }
}