package co.chimeralabs.analytics.datareceiver.filesystem;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class BigDataOutputStreamHDFSImpl implements BigDataOutputStream{
	private FSDataOutputStream outStream;
	public BigDataOutputStreamHDFSImpl(FileSystem fs, String filenamePath) throws IOException{
		outStream = fs.append(new Path(filenamePath));
	}
	@Override
	public void write(byte[] b, int offset, int length) throws IOException {
		outStream.write(b, offset, length);
	}
	@Override
	public void write(String str) throws IOException {
		outStream.writeBytes(str);
	}
	@Override
	public Long position() throws IOException {
		return outStream.getPos();
	}
	@Override
	public void flush() throws IOException {
		outStream.flush();
	}
	@Override
	public void close() throws IOException {
		outStream.close();
	}
	@Override
	public void writeLine(String str) throws IOException {
		str += "\n";
		outStream.writeBytes(str);
	}
}
