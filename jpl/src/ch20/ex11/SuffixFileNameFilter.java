package ch20.ex11;

import java.io.File;
import java.io.FilenameFilter;

public class SuffixFileNameFilter implements FilenameFilter {
    private final File dir;
    private final String suffix;

    public static void main(String[] args) {
    	SuffixFileNameFilter filter = new SuffixFileNameFilter(args[0], args[1]);
        for (String list : filter.list()) {
        	System.out.println(list);
        }
    }

    public SuffixFileNameFilter(String dir, String suffix) {
        this.dir = new File(dir);
        this.suffix = suffix;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(suffix);
    }

    public String[] list() {
        return dir.list(this);
    }
}
