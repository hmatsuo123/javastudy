package ch20.ex09;

import java.io.File;
import java.io.IOException;

public class PrintFileInfo {
	public static void main(String[] args) {
		for (String fileName : args) {
			File file = new File(fileName);
			if (!file.exists()) {
				System.out.println("指定されたファイルは存在しません。");
				break;
			}
			try {
				System.out.println("name: " + fileName);
				System.out.println("getAbsolutePath(): " + file.getAbsolutePath());
				System.out.println("CanonicalPath(): " + file.getCanonicalPath());
				System.out.println("getFreeSpace(): " + file.getFreeSpace());
				System.out.println("getName(): " + file.getName());
				System.out.println("getParent(): " + file.getParent());
				System.out.println("getPath(): " + file.getPath());
				System.out.println("getTotalSpace(): " + file.getTotalSpace());
				System.out.println("getUsableSpace(): " + file.getUsableSpace());
				System.out.println("getAbsoluteFile(): " + file.getAbsoluteFile());
				System.out.println("getCanonicalFile(): " + file.getCanonicalFile());
				System.out.println("getParentFile(): " + file.getParentFile());
				System.out.println("isAbsolute(): " + file.isAbsolute());
				System.out.println("isDirectory(): " + file.isDirectory());
				System.out.println("isFile(): " + file.isFile());
				System.out.println("isHidden(): " + file.isHidden());
				System.out.println("lastModified(): " + file.lastModified());
				System.out.println("hashCode(): " + file.hashCode());
				System.out.println("length(): " + file.length());
				System.out.println("toString(): " + file.toString());
				System.out.println("canExecute(): " + file.canExecute());
				System.out.println("canRead(): " + file.canRead());
				System.out.println("canWrite(): " + file.canWrite());
				System.out.println("toURI(): " + file.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
