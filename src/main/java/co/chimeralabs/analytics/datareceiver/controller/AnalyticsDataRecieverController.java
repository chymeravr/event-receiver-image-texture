package co.chimeralabs.analytics.datareceiver.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import co.chimeralabs.analytics.datareceiver.filesystem.BigDataFS;
import co.chimeralabs.analytics.datareceiver.filesystem.BigDataInputStreamNormalImpl;
import co.chimeralabs.analytics.datareceiver.filesystem.BigDataOutputStreamNormalImpl;
import co.chimeralabs.analytics.datareceiver.model.AnalyticsDataReceiverDTO;
import co.chimeralabs.analytics.datareceiver.util.RetrieveResources;

@Controller
public class AnalyticsDataRecieverController {
	private static final Logger analyticsLogger = LoggerFactory.getLogger(AnalyticsDataRecieverController.class);
	//private static final Logger analyticsLogger = LogManager.getLogger(AnalyticsDataRecieverController.class);
	
	@Autowired
	BigDataFS bigDataFS;

	@ResponseBody
	@RequestMapping(value="/analytics_1", method=RequestMethod.POST, headers = {"Content-type=application/json"})
	public String TestingAnalyticsAPI1(@RequestBody List<AnalyticsDataReceiverDTO> logs) throws JsonParseException, JsonMappingException, IOException{
		InputStream inputStream = getClass().getResourceAsStream("/machine/MachineConstants.xml");
		String metafilepath = RetrieveResources.retrieveResourcesAppConatants(inputStream, "storageinfofilepath").get(0);
		BigDataInputStreamNormalImpl metain = (BigDataInputStreamNormalImpl)bigDataFS.getInputStream(metafilepath);
		String appStartFilename = metain.readLine();
		String appEndFilename = metain.readLine();
		String adDisplayedFilename = metain.readLine();
		String visibilityMetricFilename = metain.readLine();
		String adServiceFilename = metain.readLine();
		metain.close();
		BigDataOutputStreamNormalImpl appStartOS = null;
		BigDataOutputStreamNormalImpl appEndOS = null;
		BigDataOutputStreamNormalImpl adDisplayedOS = null;
		BigDataOutputStreamNormalImpl visibilityMetricOS = null;
		BigDataOutputStreamNormalImpl adServiceOS = null;
		Long timestamp = System.currentTimeMillis();
		
		for (AnalyticsDataReceiverDTO log : logs) {
			log.setDtoObj(timestamp.toString() + "\t" + log.getDtoObj());
			switch(log.getType()){
			case 1:
				if(appStartOS==null)
					appStartOS = (BigDataOutputStreamNormalImpl)bigDataFS.getOutputStream(appStartFilename);
				appStartOS.writeLine(log.getDtoObj());
				break;
			case 2:
				if(appEndOS==null)
					appEndOS = (BigDataOutputStreamNormalImpl)bigDataFS.getOutputStream(appEndFilename);
				appEndOS.writeLine(log.getDtoObj());
				break;
			case 3:
				if(adDisplayedOS == null)
					adDisplayedOS = (BigDataOutputStreamNormalImpl)bigDataFS.getOutputStream(adDisplayedFilename);
				adDisplayedOS.writeLine(log.getDtoObj());
				break;
			case 4:
				if(visibilityMetricOS == null)
					visibilityMetricOS = (BigDataOutputStreamNormalImpl)bigDataFS.getOutputStream(visibilityMetricFilename);
				visibilityMetricOS.writeLine(log.getDtoObj());
				break;
			case 5:
				if(adServiceOS == null)
					adServiceOS = (BigDataOutputStreamNormalImpl)bigDataFS.getOutputStream(adServiceFilename);
				adServiceOS.writeLine(log.getDtoObj());
				break;
			}
		}
		if(appStartOS!=null){
			appStartOS.flush();
			appStartOS.close();
		}
		if(appEndOS!=null){
			appEndOS.flush();
			appEndOS.close();
		}
		if(adDisplayedOS!=null){
			adDisplayedOS.flush();
			adDisplayedOS.close();
		}
		if(visibilityMetricOS!=null){
			visibilityMetricOS.flush();
			visibilityMetricOS.close();
		}
		if(adServiceOS!=null){
			adServiceOS.flush();
			adServiceOS.close();
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/analytics", method=RequestMethod.POST, headers = {"Content-type=application/json"})
	public String TestingAnalyticsAPI(@RequestBody List<AnalyticsDataReceiverDTO> logs) throws JsonParseException, JsonMappingException, IOException{
		Long timestamp = System.currentTimeMillis();
		for (AnalyticsDataReceiverDTO log : logs) {
			log.setDtoObj(timestamp.toString() + "\t" + log.getType() + "\t" +log.getDtoObj());
			switch(log.getType()){
			case 1:
				analyticsLogger.info(log.getDtoObj());
				break;
			case 2:
				analyticsLogger.info(log.getDtoObj());
				break;
			case 3:
				analyticsLogger.info(log.getDtoObj());
				break;
			case 4:
				analyticsLogger.info(log.getDtoObj());
				break;
			case 5:
				analyticsLogger.info(log.getDtoObj());
				break;
			}
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/testinglog4j2", method=RequestMethod.GET)
	public String testing(){
		analyticsLogger.info("test");
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
