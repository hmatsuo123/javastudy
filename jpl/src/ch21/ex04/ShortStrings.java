package ch21.ex04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStrings implements ListIterator<String>{
	private ListIterator<String> strings;
    private String nextShort;
    private String prevShort;
    private final int maxLen;
    private int index;

    public ShortStrings(ListIterator<String> strings, int maxLen) {
        this.strings = strings;
        this.maxLen = maxLen;
        nextShort = null;
        prevShort = null;
        index = 0;
    }

	@Override
	public boolean hasNext() {
		if (nextShort != null)
            return true;
        while (strings.hasNext()) {
            nextShort = strings.next();
            if (nextShort.length() <= maxLen)
                return true;
        }
        nextShort = null;
        return false;
	}

	@Override
	public String next() {
		if (nextShort == null && !hasNext())
            throw new NoSuchElementException();
        String n = nextShort;
        nextShort = null;
        index++;
        return n;
	}

	@Override
	public boolean hasPrevious() {
		if (prevShort != null)
            return true;
        while (strings.hasPrevious()) {
            prevShort = strings.previous();
            if (prevShort.length() <= maxLen)
                return true;
        }
        prevShort = null;
        return false;
	}

	@Override
	public String previous() {
		if (prevShort == null && !hasPrevious())
            throw new NoSuchElementException();
        String n = prevShort;
        prevShort = null;
        index--;
        return n;
	}

	@Override
	public int nextIndex() {
		return index++;
	}

	@Override
	public int previousIndex() {
		return index--;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void set(String e) {
		strings.set(e);
	}

	@Override
	public void add(String e) {
		strings.add(e);
	}
}
