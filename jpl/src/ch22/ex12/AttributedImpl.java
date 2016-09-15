package ch22.ex12;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AttributedImpl implements Attributed<String> {
	protected Map<String, Attr<String>> attrTable = new HashMap<String, Attr<String>>();

    @Override
    public void add(Attr<String> newAttr) {
        attrTable.put(newAttr.getName(), newAttr);
    }

    @Override
    public Attr<String> find(String attrName) {
        return attrTable.get(attrName);
    }

    @Override
    public Attr<String> remove(String attrName) {
        Attr<String> ret = attrTable.remove(attrName);
        return ret;
    }

    @Override
    public Iterator<Attr<String>> attrs() {
        return attrTable.values().iterator();
    }
}
