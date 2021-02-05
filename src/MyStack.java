import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

public class MyStack<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elementData;
    private int size;


    public MyStack() {
        this.elementData = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public E push(E value) {
        if (size == elementData.length) {
            resize();
        }
        elementData[size] = value;
        size++;
        return value;
    }

    private void resize() {
        elementData = (E[]) Arrays.copyOf(elementData, (size * 3 / 2 + 1));
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);
        E element = get(index);
        elementData[index] = null;

        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;

        return element;

    }

    private E get(int index) {
        Objects.checkIndex(index, size);
        return elementData[index];
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public E peek() {
        if (size == 0) throw new EmptyStackException();
        return get(size-1);
    }

    public E pop() {
        if (size == 0) throw new EmptyStackException();
        return remove(size-1);
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "elementData=" + Arrays.toString(Arrays.copyOf(elementData, size)) +
                ", size=" + size +
                '}';
    }

}

class MyStackTester{
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
        for (int i = 5; i < 15; i++) {
            stack.push(i);
        }
        System.out.println("Testing resizing by adding 10 elements ");
        System.out.println(stack);
        System.out.println("Peeking element - should be 14: " + stack.peek() );
        System.out.println(stack);
        System.out.println("Testing poping element - should be 14: " + stack.pop());
        System.out.println(stack);
        System.out.println("Testing removing element ");
        System.out.println("Testing pushing by adding element 15 to stack");
        stack.push(15);
        System.out.println(stack);
        System.out.println("Testing removing element by index 3");
        stack.remove(3);
        System.out.println(stack);
        System.out.println("Testing clearing");
        stack.clear();
        System.out.println(stack);

    }
}
