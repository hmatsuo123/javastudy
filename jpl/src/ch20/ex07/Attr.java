package ch20.ex07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Attr {
	private final String name;
	private Object value = null;

	public Attr(String name) {
		this.name = name;
	}

	public Attr(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	// 追加
	public Attr(String name, InputStream in) throws IOException {
        this.name = name;
        DataInputStream input = new DataInputStream(in);
        value = input.readInt();
        input.close();
    }

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public Object setValue(Object newValue) {
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString() {
		return name + "='" + value + "'";
	}

	// 追加
	public void writeData(String file, Object value) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        out.writeBytes(value.toString());
        out.close();
    }

}
