package co.chimeralabs.analytics.datareceiver.filesystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class BigDataNormalFSImpl implements BigDataFS{

	@Override
	public void initialize() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean createFile(String filePath) throws IOException {
		File newFile = new File(filePath);
		return newFile.createNewFile();
	}

	@Override
	public Boolean createDirectory(String directoryPath) throws IOException {
		File newDir = new File(directoryPath);
		return newDir.mkdirs();
	}

	@Override
	public Boolean exists(String path) throws IOException {
		File file = new File(path);
		return file.exists();
	}

	@Override
	public Boolean isFile(String path) throws IOException {
		File file = new File(path);
		return file.isFile();
	}

	@Override
	public Boolean isDirectory(String path) throws IOException {
		File dir = new File(path);
		return dir.isDirectory();
	}

	@Override
	public void deleteFile(String path) throws IOException {
		File file = new File(path);
		boolean ret = file.delete();
	}

	@Override
	public void deleteDirectory(String path) throws IOException {
		File dir = new File(path);
		FileUtils.deleteDirectory(dir);
	}

	@Override
	public BigDataOutputStream getOutputStream(String filenamePath) throws IOException {
		return new BigDataOutputStreamNormalImpl(filenamePath);
	}

	@Override
	public BigDataInputStream getInputStream(String filenamePath) throws IOException {
		return new BigDataInputStreamNormalImpl(filenamePath);
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getLength(String path) throws IOException {
		File file = new File(path);
		return file.length();
	}

	@Override
	public List<String> listFiles(String path) throws IOException {
		File dir = new File(path);
		File[] files = dir.listFiles();
		List<String> returnFiles = new ArrayList<String>();
		for (File file : files) {
			if(file.isFile()){
				String str = file.getPath().toString();
				returnFiles.add(str);
			}
		}
		return returnFiles;
	}

	@Override
	public List<String> listDirectories(String path) throws IOException {
		File dir = new File(path);
		File[] files = dir.listFiles();
		List<String> returnFiles = new ArrayList<String>();
		for (File file : files) {
			if(file.isDirectory()){
				String str = file.getPath().toString();
				returnFiles.add(str);
			}
		}
		return returnFiles;
	}

}
