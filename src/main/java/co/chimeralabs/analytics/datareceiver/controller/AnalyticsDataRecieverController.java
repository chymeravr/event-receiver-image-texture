package co.chimeralabs.analytics.datareceiver.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.chimeralabs.analytics.datareceiver.filesystem.BigDataFS;
import co.chimeralabs.analytics.datareceiver.filesystem.BigDataInputStreamNormalImpl;
import co.chimeralabs.analytics.datareceiver.filesystem.BigDataOutputStreamNormalImpl;
import co.chimeralabs.analytics.datareceiver.model.AnalyticsDataReceiverDTO;
import co.chimeralabs.analytics.datareceiver.model.AnalyticsDataReceiverDTO.TYPE;
import co.chimeralabs.analytics.datareceiver.util.RetrieveResources;


@Controller
public class AnalyticsDataRecieverController {

	@Autowired
	BigDataFS bigDataFS;

	@ResponseBody
	@RequestMapping(value="/analytics", method=RequestMethod.POST, headers = {"Content-type=application/json"})
	public String TestingAnalyticsAPI(@RequestBody List<String> logs) throws JsonParseException, JsonMappingException, IOException{
		InputStream inputStream = getClass().getResourceAsStream("/machine/MachineConstants.xml");
		String metafilepath = RetrieveResources.retrieveResourcesAppConatants(inputStream, "storageinfofilepath").get(0);
		BigDataInputStreamNormalImpl metain = (BigDataInputStreamNormalImpl)bigDataFS.getInputStream(metafilepath);
		String filepath = metain.readLine();
		metain.close();
		
		BigDataOutputStreamNormalImpl os = (BigDataOutputStreamNormalImpl)bigDataFS.getOutputStream(filepath);
		for (String log : logs) {
			os.writeLine(log);
		}
		os.flush();
		os.close();
		return "success";
	}
	//	
	//	private class FileNameChanger{
	//		private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	//		public void startScheduler(){
	//			final Runnable runner = new Runnable(){
	//				public void run(){
	//					
	//				}
	//			};
	//			scheduler.scheduleAtFixedRate(runner, 20, 20, TimeUnit.SECONDS);
	//		}
	//	}
}
