package ch24.ex01;

import java.util.ListResourceBundle;

public class GlobalRes_ja extends ListResourceBundle {
	private static final String Hello = "こんにちは";
	private static final String Goodbye = "さようなら";

	@Override
    protected Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
        { GlobalRes.HELLO, Hello },
        { GlobalRes.GOODBYE, Goodbye },
    };
}
