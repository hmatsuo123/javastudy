package ch20.ex10;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WordCount {
	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new FileReader(new File(args[0])));
		Map<String, Integer> map = new HashMap<>();
		int token;
		while ((token = tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
			if (token == StreamTokenizer.TT_WORD) {
				String word = tokenizer.sval;
				Integer count = map.get(word);
				if (count == null)
					map.put(word, 1);
				else {
					map.put(word, count + 1);
				}
			}
		}
		for (Entry<String, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}
	}
}