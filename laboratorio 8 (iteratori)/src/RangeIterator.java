import java.util.Iterator;

public class RangeIterator implements Iterator<Integer> {

    private final int start, end;
    private int current;

    public RangeIterator(int start, int end){

        this.start = start;
        this.end = end;
        current = start;
    }


    @Override
    public boolean hasNext() {

        return current <= end;
    }

    @Override
    public Integer next() {
        return current++;
    }
}
