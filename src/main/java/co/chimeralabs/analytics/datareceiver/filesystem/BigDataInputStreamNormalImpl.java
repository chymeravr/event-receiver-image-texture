package co.chimeralabs.analytics.datareceiver.filesystem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class BigDataInputStreamNormalImpl implements BigDataInputStream{
	private FileInputStream fis;
	private InputStreamReader isr;
	private BufferedReader br;
	
	public BigDataInputStreamNormalImpl(String filepath) throws FileNotFoundException {
		fis = new FileInputStream(filepath);
		isr = new InputStreamReader(fis);
		br = new BufferedReader(isr);
	}

	@Override
	public String readLine() throws IOException {
		return br.readLine();
	}

	@Override
	public void close() throws IOException {
		br.close();
		isr.close();
		fis.close();
	}

}
