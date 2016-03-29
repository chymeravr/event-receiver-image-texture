package co.chimeralabs.analytics.datareceiver.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

import co.chimeralabs.analytics.datareceiver.filesystem.BigDataFS;
import co.chimeralabs.analytics.datareceiver.filesystem.BigDataNormalFSImpl;
import co.chimeralabs.analytics.datareceiver.filesystem.BigDataOutputStream;
import co.chimeralabs.analytics.datareceiver.filesystem.BigDataOutputStreamNormalImpl;


public class LogsWriter implements Runnable{

	private Queue<String> queue;
	BigDataOutputStream os;
	private String directory = "/data";
	private String filename = directory+"/"+"1";
	private Lock queueLock = new ReentrantLock();
	Thread t;
	
	public LogsWriter() throws IOException{
		queue = new LinkedList<String>();
		BigDataFS bigDataFS = new BigDataNormalFSImpl();
		if(!bigDataFS.exists(directory)){
			bigDataFS.createDirectory(directory);
		}
		if(!bigDataFS.exists(filename)){
			bigDataFS.createFile(filename);
		}
		os = (BigDataOutputStreamNormalImpl)bigDataFS.getOutputStream(filename);
		t = new Thread(this, "queueThread");
		t.start();
	}
	
	public void push(List<String> logs){
		queueLock.tryLock();
		for (String string : logs) {
			queue.add(string);
		}
		queueLock.unlock();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			queueLock.tryLock();
			String log = queue.element();
			queue.remove();
			try {
				os.writeLine(log);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			queueLock.unlock();
		}
	}
}