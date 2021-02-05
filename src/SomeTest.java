import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class SomeTest {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        System.out.println("list = " + list);
        list.forEach(System.out::println);
        System.out.println("__________");
        list.forEach((e) -> {
            if(e.equals("a")){
                System.out.println(e);
            }
        });

        ArrayList<String> arr = new ArrayList<>();
        arr.add(null);
        arr.add(null);
        System.out.println(arr);

        LinkedList<String> link = new LinkedList<>();
        link.add(null);
        link.add(null);
        System.out.println(link);

        Vector<String> vector = new Vector<>();
        vector.add(null);
        vector.add(null);
        System.out.println(vector);

        Stack<String> stack = new Stack<>();
        stack.add(null);
        stack.add(null);
        System.out.println(stack);

        Set<String> hset = new HashSet<>();
        hset.add(null);
        System.out.println(hset);

        Set<String> lset = new LinkedHashSet<>();
        lset.add(null);
        lset.add(null);
        lset.add(null);
        System.out.println(lset);

        Map<String, String> hmap = new HashMap();
        hmap.put(null, null);
        System.out.println(hmap);

        Map<String, String> lhmap = new LinkedHashMap<>();
        lhmap.put(null, null);
        System.out.println(lhmap);







    }

}
