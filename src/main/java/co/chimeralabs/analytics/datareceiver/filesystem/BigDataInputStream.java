package co.chimeralabs.analytics.datareceiver.filesystem;

import java.io.IOException;

public interface BigDataInputStream {
	String readLine() throws IOException;
	void close() throws IOException;
}
