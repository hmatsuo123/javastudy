package ch22.ex04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

public class AttributedImplement extends Observable implements Attributed<Integer>{
	private Map<String, Attr<Integer>> map = new HashMap<String, Attr<Integer>>();

    @Override
    public void add(Attr<Integer> newAttr) {
    	map.put(newAttr.getName(), newAttr);
        setChanged();
        notifyObservers();
    }

    @Override
    public Attr<Integer> find(String attrName) {
        return map.get(attrName);
    }

    @Override
    public Attr<Integer> remove(String attrName) {
        Attr<Integer> attr = map.remove(attrName);
        setChanged();
        notifyObservers();
        return attr;
    }

    @Override
    public Iterator<Attr<Integer>> attrs() {
        return attrs();
    }
}
