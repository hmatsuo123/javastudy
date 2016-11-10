package ch24.ex01;

import java.util.ListResourceBundle;

public class GlobalRes_en extends ListResourceBundle {
	private static final String Hello = "Hello";
	private static final String Goodbye = "Goodbye";
	@Override
    protected Object[][] getContents() {
        return contents;
    }

    private static final Object[][] contents = {
        { GlobalRes.HELLO, Hello },
        { GlobalRes.GOODBYE, Goodbye },
    };
}
