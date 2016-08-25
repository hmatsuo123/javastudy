package ch21.ex03;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeakValueMap<K, V> implements Map<K, V>  {
	private Map<K, WeakReference<V>> map = new HashMap<>();

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsKey(value);
	}

	@Override
	public V get(Object key) {
		return map.get(key).get();
	}

	@Override
	public V put(K key, V value) {
		return map.put(key, new WeakReference<>(value)).get();
	}

	@Override
	public V remove(Object key) {
		return map.remove(key).get();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for(java.util.Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
			map.put(e.getKey(), new WeakReference<>(e.getValue()));
		}
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<V> values() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
