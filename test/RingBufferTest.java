import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RingBufferTest {

    private RingBuffer rb;

    @BeforeEach
    public void setUp() {
        rb = new RingBuffer();
    }

    @Test
    void testAddElementToEmptyRingBufferAndGetElementReturnsTheElement() {
        rb.add(1);
        assertFalse(rb.isEmpty());
        assertEquals(1, rb.get());
        assertTrue(rb.isEmpty());
    }

    @Test
    void testGetElementFromEmptyRingBufferReturnsNull() {
        assertNull(rb.get());
    }

    @Test
    void testCountReturnsCountOfElementsInRingBuffer() {
        rb.add(1);
        rb.add(2);
        assertEquals(2, rb.count());
    }

    @Test
    void testAddThreeElementsAndGetThreeElements() {
        rb.add(1);
        rb.add(2);
        rb.add(3);
        assertEquals(1, rb.get());
        assertEquals(2, rb.get());
        assertEquals(3, rb.get());
    }

    @Test
    void testOverwriteFirstElementIfRingBufferIsFullReturnNextOldestElement() {
        rb.add(1);
        rb.add(2);
        rb.add(3);
        rb.add(4);
        assertEquals(2, rb.get());
        assertEquals(3, rb.get());
        assertEquals(4, rb.get());
        rb.add(1);
        rb.add(2);
        rb.add(3);
        rb.add(4);
        rb.add(5);
        rb.add(6);
        assertEquals(4, rb.get());
        assertEquals(5, rb.get());
        assertEquals(6, rb.get());
    }
}
