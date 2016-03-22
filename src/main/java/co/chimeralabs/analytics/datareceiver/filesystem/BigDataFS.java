package co.chimeralabs.analytics.datareceiver.filesystem;

import java.io.IOException;
import java.util.List;


public interface BigDataFS {
	void initialize() throws IOException;
	Boolean createFile(String filePath) throws IOException;
	Boolean createDirectory(String directoryPath) throws IOException;
	Boolean exists(String path) throws IOException;
	Boolean isFile(String path) throws IOException;
	Boolean isDirectory(String path) throws IOException;
	void deleteFile(String path) throws IOException;
	void deleteDirectory(String path) throws IOException;
	BigDataOutputStream getOutputStream(String filenamePath) throws IOException;
	BigDataInputStream getInputStream(String filenamePath) throws IOException;
	void close() throws IOException;
	Long getLength(String path) throws IOException;
	List<String> listFiles(String path) throws IOException;
	List<String> listDirectories(String path) throws IOException;
}