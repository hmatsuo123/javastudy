package ch06.ex03;

public class VerboseEnum {
	interface Verbose {
		enum Level {
			SILENT,
			TERSE,
			NORMAL,
			VERBOSE
		}

		void setVerbosity(int level);
		int getVerbosity();
	}
}
