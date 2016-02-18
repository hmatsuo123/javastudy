package ch01.ex16;

import java.io.FileInputStream;
import java.io.IOException;

public class ExeptionSample {
	class BadDataSetExeption extends Exception {
		String fileName;
		IOException e;

		public BadDataSetExeption(String fileName, IOException e){
			this.fileName = fileName;
			this.e = e;
		}
	}

	class MyUtilities {
		public double [] getDataSet(String setName)
		throws BadDataSetExeption
		{
			String file = setName + ".dset";
			FileInputStream in = null;
			try
			{
				in = new FileInputStream(file);
				return readDataSet(in);
			} catch (IOException e) {
				throw new BadDataSetExeption(file, e);
			} finally {
				try {
					if (in != null)
						in.close();
				} catch (IOException e) {

				}
			}
		}

		public double [] readDataSet(FileInputStream in) {
			return new double[5];
		}
	}
}
