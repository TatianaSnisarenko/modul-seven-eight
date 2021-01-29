import java.util.LinkedList;
import java.util.StringJoiner;

public class MyLinkedList<E> {
    private int size;
    private Node<E> firstNode;
    private Node<E> lastNode;

    public MyLinkedList() {
        this.lastNode = new Node(null, firstNode, null);
        this.firstNode = new Node(null, null, lastNode);
        lastNode.setPrevElement(firstNode);
        this.size = 0;
    }

    public boolean add(E e){
        addLast(e);
        return true;
    }

    public void addLast(E e){
        Node<E> addedNode = lastNode;
        addedNode.setCurrentElement(e);
        lastNode = new Node<>(null, addedNode, null);
        addedNode.setNextElement(lastNode);
        size++;
    }

    public void addFirst(E e){
        Node<E> addedNode = firstNode;
        addedNode.setCurrentElement(e);
        firstNode = new Node<>(null, null, addedNode);
        addedNode.setPrevElement(firstNode);
        size++;
    }

    public int getSize() {
        return size;
    }



    public E getElementByIndex(int counter){
        Node<E> target = firstNode.getNextElement();
        for (int i = 0; i < counter; i++) {
            target = getNextElement(target);
        }

        return target.getCurrentElement();
    }

    private Node<E> getNextElement(Node<E> current){
        return current.getNextElement();
    }

    public boolean remove(int index){
        if(index >=0 && index < size){
            Node<E> nodeToRemove = getNodeByIndex(index);
            Node<E> nodePrevious = nodeToRemove.getPrevElement();
            Node<E> nodeNext = nodeToRemove.getNextElement();
            nodePrevious.setNextElement(nodeNext);
            nodeNext.setPrevElement(nodePrevious);
            nodeToRemove = new Node(null, null, null);
            size--;
           return true;
        }else{
            return false;
        }
    }

    private Node<E> getNodeByIndex(int counter){
        Node<E> target = firstNode.getNextElement();
        for (int i = 0; i < counter; i++) {
            target = getNextElement(target);
        }

        return target;
    }

    @Override
    public String toString() {
        String beginning = "MyLinkedList: " + "size=" + size + ", [";
        StringJoiner joiner = new StringJoiner(", ", beginning, "]");
        for (int i = 0; i < size; i++) {
            joiner.add("" + getElementByIndex(i));
        }

        return joiner.toString();
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            Node<E> node = getNodeByIndex(0);
            node.setPrevElement(null);
            node.setCurrentElement(null);
            node.setNextElement(null);
        }
       // firstNode = lastNode = null;
        size = 0;
    }

    private static class Node<E>{
        private E currentElement;
        Node<E> nextElement;
        Node<E> prevElement;

        public Node(E currentElement, Node<E> prevElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
            this.prevElement = prevElement;
        }

        public E getCurrentElement() {
            return currentElement;
        }

        public void setCurrentElement(E currentElement) {
            this.currentElement = currentElement;
        }

        public Node<E> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<E> nextElement) {
            this.nextElement = nextElement;
        }

        public Node<E> getPrevElement() {
            return prevElement;
        }

        public void setPrevElement(Node<E> prevElement) {
            this.prevElement = prevElement;
        }
    }
}

class MainMyLinkedListTester{
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        System.out.println("Size of a new created list = " + list.getSize());
        list.addLast("Tanya");
        list.addLast("Alex");
        System.out.println("New size should e 2 = " + list.getSize());
        System.out.println(list);
        list.addFirst("FirstOne");
        list.addFirst("SecondOne");
        System.out.println("Adding to the head two elements");
        System.out.println(list);
        System.out.println("Removed first item " + list.remove(0));
        System.out.println(list);
        System.out.println("Trying to remove 10th item " + list.remove(10));
        System.out.println(list);
        list.clear();
        System.out.println(list);

    }

}