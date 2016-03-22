package co.chimeralabs.analytics.datareceiver.filesystem;

import java.io.IOException;

public interface BigDataOutputStream {
	void write(byte[] b, int offset, int length) throws IOException;
	void write(String str) throws IOException;
	void writeLine(String str) throws IOException;
	Long position() throws IOException;
	void flush() throws IOException;
	void close() throws IOException;
}
