package co.chimeralabs.analytics.datareceiver.filesystem;

/**********************************************************************************
 * Commented because Hadoop libraries were creating problems in deploying to tomcat
 **********************************************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//import org.apache.hadoop.fs.FSDataInputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;

public abstract class BigDataInputStreamHDFSImpl implements BigDataInputStream{
//	private FSDataInputStream in;
//	private BufferedReader br;
//	
//	public BigDataInputStreamHDFSImpl(FileSystem fs, String filenamePath) throws IOException{
//		in = fs.open(new Path(filenamePath));
//		br = new BufferedReader(new InputStreamReader(in));
//	}
//	
//	@Override
//	public String readLine() throws IOException {
//		return br.readLine();
//	}
//
//	@Override
//	public void close() throws IOException {
//		br.close();
//		in.close();
//	}
}
