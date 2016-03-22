package co.chimeralabs.analytics.datareceiver.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

@Service
public class BigDataHDFSImpl implements BigDataFS{

	private final String hdfsPropFile = "conf/hdfs.properties";

	private Configuration conf;
	private Properties properties;
	private FileSystem filesystem;
	
	private void loadPropertiesFiles() throws IOException{
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(hdfsPropFile);
		properties = new Properties();
		if(inputStream!=null){
			properties.load(inputStream);
		}
	}
	
	@Override
	public void initialize() throws IOException {
		// TODO Auto-generated method stub
		loadPropertiesFiles();
		conf = new Configuration(true);
		//conf.addResource(new Path("/home/sushil/Softies/hadoop-2.7.2/etc/hadoop/core-site.xml"));
		//conf.addResource(new Path("/home/sushil/Softies/hadoop-2.7.2/etc/hadoop/hdfs-site.xml"));
		conf.set("fs.default.name", properties.getProperty("namenode.address"));
		filesystem = FileSystem.get(conf);
	}
	
	@Override
	public Boolean createFile(String filePath) throws IOException {
		Path path = new Path(filePath);
		if(filesystem.create(path) != null)
			return true;
		else
			return false;
	}

	@Override
	public Boolean createDirectory(String directoryPath) throws IOException {
		return filesystem.mkdirs(new Path(directoryPath));
	}

	@Override
	public Boolean exists(String path) throws IOException {
		Boolean returnValue = filesystem.exists(new Path(path));
		return returnValue;
	}

	@Override
	public Boolean isFile(String path) throws IOException {
		return filesystem.isFile(new Path(path));
	}

	@Override
	public Boolean isDirectory(String path) throws IOException {
		return filesystem.isDirectory(new Path(path));
	}

	@Override
	public void deleteFile(String path) throws IOException {
		filesystem.delete(new Path(path), false);
	}

	@Override
	public void deleteDirectory(String path) throws IOException{
		filesystem.delete(new Path(path), true);
	}

	@Override
	public BigDataOutputStream getOutputStream(String filenamePath) throws IOException {
		return new BigDataOutputStreamHDFSImpl(filesystem, filenamePath);
	}

	@Override
	public BigDataInputStream getInputStream(String filenamePath) throws IOException {
		return new BigDataInputStreamHDFSImpl(filesystem, filenamePath);
	}

	@Override
	public void close() throws IOException{
		filesystem.close();
	}

	@Override
	public Long getLength(String path) throws IOException {
		return new Long(filesystem.getFileStatus(new Path(path)).getLen());
	}

	@Override
	public List<String> listFiles(String path) throws IOException {
		FileStatus[] children = filesystem.listStatus(new Path(path));
		List<String> files = new ArrayList<String>();
		for (FileStatus child : children) {
			if(child.isFile()){
				String str = child.getPath().toString();
				str = str.split("/", 4)[3];
				files.add("/"+str);
			}
		}
		return files;
	}

	@Override
	public List<String> listDirectories(String path) throws IOException {
		FileStatus[] children = filesystem.listStatus(new Path(path));
		List<String> directories = new ArrayList<String>();
		for (FileStatus child : children) {
			if(child.isDirectory()){
				String str = child.getPath().toString();
				str = str.split("/", 4)[3];
				directories.add("/"+str);
			}
		}
		return directories;
	}
}
