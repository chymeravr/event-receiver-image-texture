package co.chimeralabs.analytics.datareceiver.filesystem;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BigDataOutputStreamNormalImpl implements BigDataOutputStream{
	private FileOutputStream fos;
	
	public BigDataOutputStreamNormalImpl(String fileNamePath) throws FileNotFoundException {
		fos = new FileOutputStream(fileNamePath, true);
	}
	
	@Override
	public void write(byte[] b, int offset, int length) throws IOException {
		// TODO Auto-generated method stub
		fos.write(b, offset, length);
	}

	@Override
	public void write(String str) throws IOException {
		fos.write(str.getBytes());
	}

	@Override
	public void writeLine(String str) throws IOException {
		str += "\n";
		fos.write(str.getBytes());
	}

	@Override
	public Long position() throws IOException {
		return null;
	}

	@Override
	public void flush() throws IOException {
		fos.flush();
		
	}

	@Override
	public void close() throws IOException {
		fos.close();
	}
	
}
