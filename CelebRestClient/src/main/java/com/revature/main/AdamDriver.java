package com.revature.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.revature.bean.Celebrity;

public class AdamDriver 
{
	private static final Logger loggy = LoggerFactory.getLogger(AdamDriver.class);

	public static void main(String[] args)
	{
		RestTemplate resty = new RestTemplate();
		String urlIp = "http://192.168.61.233:8086/celebrities";
	//	For Posts
		
		Celebrity c1 = new Celebrity(5, "Madonna", 60, "Singer");
////		Celebrity c2 = new Celebrity(14, "Miranda Cosgrove", 25, "Actress");
////		Celebrity c3 = new Celebrity(93, "Adam Driver", 34, "Actor");
//		
//		
//		
		try
		{
			Celebrity cp1 = resty.postForObject(urlIp, c1, Celebrity.class);
			loggy.info("cp1 generated: " + cp1);
////			Celebrity cp2 = resty.postForObject(urlIp, c2, Celebrity.class);
////			loggy.info("cp2 generated: " + cp2);
////			Celebrity cp3 = resty.postForObject(urlIp, c3, Celebrity.class);
////			loggy.info("cp3 generated: " + cp3);
//			
//			
		}
		catch(Exception e)
		{
			loggy.error("Issue with POST: " + e.getMessage());
		}
		
		
		// For Get
		
		try
		{
			Celebrity cg1 = resty.getForObject(urlIp + "/5", Celebrity.class);
			loggy.info("Get Celeb 5 " + cg1);
			Celebrity cg2 = resty.getForObject(urlIp + "/14", Celebrity.class);
			loggy.info("Get Celeb 14 " + cg2);
			Celebrity cg3 = resty.getForObject(urlIp + "/93", Celebrity.class);
			loggy.info("Get Celeb 93 " + cg3);
			Celebrity cg4 = resty.getForObject(urlIp + "/3", Celebrity.class);
			loggy.info("Get Celeb 3 " + cg4);
		}
		catch(Exception e)
		{
			loggy.error("We have Failed! " + e.getMessage());
		}
		
		
		// GET ALL
		
		try
		{
			Celebrity[] celebs = resty.getForObject(urlIp, Celebrity[].class);
			loggy.info("Printing out Celebrities in GET ALL");
			for(Celebrity cel : celebs)
				loggy.info(cel.toString());
		}
		catch(Exception e)
		{
			loggy.error("Exception detected in GET ALL! " + e.getMessage());
		}
		
		// Update
		
		try
		{
			Celebrity cg2 = resty.getForObject(urlIp + "/14", Celebrity.class);
			loggy.info("About to change celeb: " + cg2);
			cg2.setName("Miranda T. Cosgrove");
			resty.put(urlIp + "/14", cg2);
			
			Celebrity cg2u = resty.getForObject(urlIp + "/14", Celebrity.class);
			loggy.info("Changed Celebrity: " + cg2u);
		}
		catch(Exception e)
		{
			loggy.error("Exception detected in Update! " + e.getMessage());
		}
		
		// DELETE
		
		try
		{
			//Celebrity mad = resty.getForObject(urlIp + "/5", Celebrity.class);
			
			loggy.info("About to Delete!");
			
			resty.delete(urlIp + "/5");
			
			loggy.info("Deleted Celeb");
			
			Celebrity[] celebs = resty.getForObject(urlIp, Celebrity[].class);
			loggy.info("Printing out Celebrities To Confirm Deletion");
			for(Celebrity cel : celebs)
				loggy.info(cel.toString());
		}
		catch(Exception e)
		{
			loggy.error("Exception detected in Delete! " + e.getMessage());
		}
		
	}
}
