import java.util.Iterator;

public class Range implements Iterable<Integer> {

	private final int start, end;

	// ranges from start (inclusive) to end (exclusive)
	public Range(int start, int end) {

		this.start = start;
		this.end = end;

	}

	// ranges from 0 (inclusive) to end (exclusive)
	public Range(int end) {

		this(0, end);

	}

	@Override
	public Iterator<Integer> iterator() {
	    return new RangeIterator(start, end-1);

	}

}
