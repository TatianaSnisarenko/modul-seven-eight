import java.util.*;

public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private  E [] elementData;
    private int size;



    public MyArrayList() {
        this.elementData = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public boolean add(E value){
        if(size == elementData.length){
            resize();
        }
        elementData[size] = value;
        size++;
        return true;
    }

    private void resize(){
        elementData = (E[]) Arrays.copyOf(elementData, (size * 3 / 2 + 1));
    }

    public E remove(int index){
        Objects.checkIndex(index, size);
        E element = get(index);
        elementData[index] = null;

        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;


        return element;

    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }
    public int size(){
        return size;
    }

    public E get(int index){
        Objects.checkIndex(index, size);
        return elementData[index];
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }

}

class MainMyArrayListTester {
    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        list.add("Petro");
        list.add("Alex");
        list.add("Olga");
        System.out.println("Adding 3 elements");
        System.out.println(list);
        System.out.println("----------");

        System.out.println("Adding 10 more elements - checking resizing");
        for (int i = 0; i < 10; i++) {
            list.add("a");

        }
        System.out.println(list);
        System.out.println("----------");

        System.out.println("Getting element with index = 1");
        System.out.println("element 1 = " + list.get(1));
        Object element1 = list.get(1);
        System.out.println(list);
        System.out.println("----------");

        System.out.println("Removing element with index = 1");
        Object element = list.remove(1);
        System.out.println(element);
        System.out.println(list);
        System.out.println("----------");

        System.out.println("Getting size of list");
        System.out.println("size = " + list.size());
        System.out.println("----------");

        System.out.println("Checking method clear");
        list.clear();
        System.out.println("size = " + list.size());
        System.out.println(list);




    }
}
