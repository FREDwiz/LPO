import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class sumAll {


    public static int sumAllFor(Collection<Integer> col) {

        Object array[] = col.toArray();
        int sum = 0;
        for (int i = 0; i < col.size(); i++) sum += (Integer) array[i];
        return sum;
    }

    public static int sumAllForEach(Collection<Integer> col) {

        int sum = 0;
        for (Integer i : col) sum += i;
        return sum;

    }

    public static int sumAllWhile(Collection<Integer> col) {

        int sum = 0;
        Iterator<Integer> it = col.iterator();

        while (it.hasNext()) {

            sum += it.next();
        }

        return sum;
    }

    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) list.add(i);

        System.out.println(sumAllFor(list));
        System.out.println(sumAllForEach(list));
        System.out.println(sumAllWhile(list));



    }


}

