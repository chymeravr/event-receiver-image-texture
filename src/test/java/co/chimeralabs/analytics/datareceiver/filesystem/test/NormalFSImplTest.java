package co.chimeralabs.analytics.datareceiver.filesystem.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.chimeralabs.analytics.datareceiver.filesystem.BigDataFS;
import co.chimeralabs.analytics.datareceiver.filesystem.BigDataInputStream;
import co.chimeralabs.analytics.datareceiver.filesystem.BigDataNormalFSImpl;
import co.chimeralabs.analytics.datareceiver.filesystem.BigDataOutputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/spring/applicationContext.xml"})
public class NormalFSImplTest {
		private BigDataFS fs = new BigDataNormalFSImpl();
	
		private String directoryName1 = "/parent";
		private String directoryName2 = "/parent/child1";
		private String filename1 = "/parent/child1/file1";
		private String filename2 = "/parent/child1/file2";
		private String filename3 = "/parent/file3";
		private String str1 = "this is string 1";
		private String str2 = "this is string 2";
		
		public NormalFSImplTest() throws IOException{
		}
		
		@Test
		public void createAndDeleteDirectoryAndFiles() throws IOException{
			assertNotNull(fs);
			fs.initialize();
			Boolean val = fs.exists(directoryName1);
			assertFalse("Checking if " + directoryName1 + " exists before creation.", val);
			fs.createDirectory(directoryName1);
			assertTrue("Checking existence of "+directoryName1+" after its creation", fs.exists(directoryName1));
			assertTrue("Checking if "+directoryName1+" is a directory", fs.isDirectory(directoryName1));
			assertFalse("Checking if "+directoryName2+" does not exist before creation", fs.exists(directoryName2));
			fs.createDirectory(directoryName2);
			assertTrue("Checking if " + directoryName2 + " exists after its creation",fs.exists(directoryName2));
			
			assertFalse("Checking if " + filename1 + " exists before creation", fs.exists(filename1));
			fs.createFile(filename1);
			assertTrue("Checking if " + filename1 + " exists after creation", fs.exists(filename1));
			assertTrue("check if " + filename1 + " is a file", fs.isFile(filename1));
			fs.createFile(filename2);
			fs.createFile(filename3);
			
			String[] directoryname2files = new String[2];
			directoryname2files[0] = filename1;
			directoryname2files[1] = filename2;
			assertEquals("Checking all the files of " + directoryName2, directoryname2files.length , fs.listFiles(directoryName2).size());
			
			String[] directoryname1directories = new String[1];
			directoryname1directories[0] = directoryName2;
			assertEquals("Checking all the directories of " + directoryName1, directoryname1directories.length , fs.listDirectories(directoryName1).size());
			
			/**
			 * File operations
			 */
			fs.close();
			fs.initialize();
			BigDataOutputStream out = fs.getOutputStream(filename1);
			out.writeLine(str1);
			out.writeLine(str2);
			out.close();
			
			BigDataInputStream in = fs.getInputStream(filename1);
			String tempStr = in.readLine();
			assertEquals("Checking str1 after writing and then reading for file " + filename1, str1, tempStr);
			tempStr = in.readLine();
			assertEquals("Checking str2 after writing and then reading for file " + filename1, str2, tempStr);
			tempStr = in.readLine();
			assertNull(tempStr);
			in.close();
			
			/**
			 * Teardown starts to make filesystem get into original state
			 */
			
			fs.deleteFile(filename1);
			assertFalse("Checking if " + filename1 + " exists after its deletion",fs.exists(filename1));
			fs.deleteFile(filename2);
			fs.deleteFile(filename3);
			
			fs.deleteDirectory(directoryName2);
			assertFalse("Checking if " + directoryName2 + " exists after its deletion",fs.exists(directoryName2));
			fs.deleteDirectory(directoryName1);
			assertFalse("Checking if " + directoryName1 + " exists after its deletion",fs.exists(directoryName1));
			fs.close();
		}
		
}
