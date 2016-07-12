package ch20.ex08;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class SplitEntry {
	public static final String splitText = "%%";

    public static void main(String[] args) {
        if (args.length == 0) {
        	throw new IllegalArgumentException("specify name of file");
        }
        List<Long> table = new ArrayList<>();
        RandomAccessFile file;
        try {
            file = new RandomAccessFile(new File(args[0]), "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            String line;
            // 改行もファイルポインタにカウントされるようなので改行数をカウントする
            int newLineCount = 0;
            while ((line = file.readLine()) != null) {
            	newLineCount++;
                if (line.startsWith(splitText)) {
                	// "%%"から開始される行の"%%"を除いたエントリー位置をリストに保持する
                	// TODO: 三つ目の位置がずれてしまう
                	table.add(file.getFilePointer() - line.length() - newLineCount + splitText.length());
                }
            }

            for (Long e : table) {
            	System.out.println("エントリー開始位置: " + e);
            }

            // 表示する単語を選択
            int target = (int) (Math.random() * 1000) % table.size();

            file.seek(table.get(target));
            if ((line = file.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
