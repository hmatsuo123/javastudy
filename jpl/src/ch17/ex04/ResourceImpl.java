package ch17.ex04;

import java.lang.ref.WeakReference;

public class ResourceImpl implements Resource{
	private WeakReference<Object> keyRef;
	boolean needsRelease = false;

	ResourceImpl(Object key) {
		keyRef = new WeakReference<Object>(key);

		// 外部リソースの設定

		needsRelease = true;
	}

	public void use(Object key, Object... args) {
		if (!keyRef.get().equals(key)) {
			throw new IllegalArgumentException("wrong key");
		}

		// リソースの使用
	}

	public synchronized void release() {
		if (needsRelease) {
			needsRelease = false;

			// リソースの開放
		}
	}
}
