import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: etsvigun
 *
 * Example from
 * Effective Java™ Second Edition by Joshua Bloch
 * ITEM 28: USE BOUNDED WILDCARDS TO INCREASE API FLEXIBILITY
 */
public class RecursiveTypes {
    static class A<T extends Comparable<T>> implements Comparable<A<T>>{
        private T value;

        A(T value) { this.value = value;}

        public int compareTo(A<T> o) {
            return value.compareTo(o.value);
        }
    }

    static class B<T extends Comparable<T>> extends A<T>{
        B(T value) { super(value); }
    }

    static class C<T extends Comparable<T>> extends A<T>{
        C(T value) { super(value); }
    }
    
    static <T extends Comparable<? super T>> T max(List<? extends T> list) {
        Iterator<? extends T> i = list.iterator();
        T result = i.next();
        while (i.hasNext()) {
            T t = i.next();
            if (t.compareTo(result) > 0)
                result = t;
        }
        return result;
    }

    public static void main(String[] args) {
        List<A<Integer>> list =   new ArrayList<A<Integer>>();
        list.add(new B<Integer>(10));
        list.add(new C<Integer>(5));
        list.add(new B<Integer>(20));
        list.add(new C<Integer>(15));

        A<Integer> maximum= max(list);
        System.out.println("maximum.value = " + maximum.value);
    }
}
