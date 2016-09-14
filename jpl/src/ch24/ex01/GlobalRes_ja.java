package ch24.ex01;

import java.util.ListResourceBundle;

public class GlobalRes_ja extends ListResourceBundle {
	@Override
    protected Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
        { GlobalRes.HELLO, "こんにちは" },
        { GlobalRes.GOODBYE, "さようなら" },
    };
}
