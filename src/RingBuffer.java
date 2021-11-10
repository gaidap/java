import java.util.Arrays;
import java.util.Objects;

public class RingBuffer {
    private final static int CAPACITY = 3;
    private final Integer[] elements = new Integer[CAPACITY];
    private int head = 0;
    private int tail = 0;

    public void add(int element) {
        if (checkCapacity(head)) {
            resetHead();
        }
        if (checkValueOverwrite()) {
            incrementTailAndCheckCapacity();
        }
        elements[head++] = element;
    }

    public boolean isEmpty() {
        return Arrays.stream(elements).noneMatch(Objects::nonNull);
    }

    public Integer get() {
        if (checkCapacity(tail)) {
            resetTail();
        }
        Integer firstElement = elements[tail];
        elements[tail++] = null;

        return firstElement;
    }

    public long count() {
        return Arrays.stream(elements).filter(Objects::nonNull).count();
    }

    private boolean checkValueOverwrite() {
        return elements[head] != null;
    }

    private boolean checkCapacity(int pointer) {
        return pointer == CAPACITY || isEmpty();
    }

    private void incrementTailAndCheckCapacity() {
        if (tail++ == CAPACITY) {
            resetTail();
        }
    }

    private void resetHead() {
        head = 0;
    }

    private void resetTail() {
        tail = 0;
    }
}
