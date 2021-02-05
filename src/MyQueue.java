import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

public class MyQueue<E> {
    private int size;
    private E[] data;
    private static final int DEFAULT_CAPACITY = 11;

    public MyQueue() {
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public boolean add(E element){
        if (element == null)
            throw new NullPointerException();
        if(size == data.length){
            resize();
        }
        data[size] = element;
        size++;
        return true;
    }

    private void resize(){
        data = (E[]) Arrays.copyOf(data, (size * 3 / 2 + 1));
    }

    public E remove(int index){
        Objects.checkIndex(index, size);
        E element = data[index];
        data[index] = null;

        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return element;
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            data[i] = null;

        }
        size = 0;
    }

   public E peek(){
        return data[0];
   }

   public E poll(){
       return remove(0);
   }

   public int size(){
        return size;
   }

   @Override
    public String toString(){
       return "MyQueue{" +
               "data=" + Arrays.toString(Arrays.copyOf(data, size)) +
               ", size=" + size +
               '}';
   }

}

class MyQueueTester{
    public static void main(String[] args) {
        MyQueue<String> strings = new MyQueue<>();
        strings.add("first");
        strings.add("second");
        strings.add("third");
        strings.add("fourth");
        strings.add("fifth");
        System.out.println(strings);
        String first = strings.peek();
        System.out.println("Using peek - should get first: " + first);
        System.out.println(strings);
        System.out.println("Using poll - should get and remove first: " + strings.poll());
        System.out.println(strings);
        for (int i = 0; i < 10; i++) {
            strings.add("abc");
        }
        System.out.println("Testing resizing by adding 10 elements: " + strings);
        System.out.println("Getting size: " + strings.size());
        System.out.println("Testing removing by index 3 - should remove fifth: " + strings.remove(3));
        System.out.println(strings);
        System.out.println("Testing clearing");
        strings.clear();
        System.out.println(strings);
    }
}


