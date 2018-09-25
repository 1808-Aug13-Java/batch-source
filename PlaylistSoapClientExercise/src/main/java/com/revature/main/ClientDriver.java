package com.revature.main;

import java.io.PrintWriter;
import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.revature.models.Video;
import com.revature.service.Playlist;

public class ClientDriver {
	
public static void main(String[] args) {
		
		String serviceUrl = "http://192.168.61.233:8082/PlaylistSoapServiceExercise/playlist";

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(Playlist.class); //provide our interface
		factory.setAddress(serviceUrl);
		
		LoggingInInterceptor inInterceptor = new LoggingInInterceptor();
		LoggingOutInterceptor outInterceptor = new LoggingOutInterceptor();
		
		factory.getInInterceptors().add(inInterceptor);
		factory.getOutInterceptors().add(outInterceptor);
		
		inInterceptor.setPrintWriter(new PrintWriter(System.out));
		outInterceptor.setPrintWriter(new PrintWriter(System.out));
		
		Playlist playlist = (Playlist) factory.create();
		List<Video> videos = playlist.getAllVideos();
		for(Video v : videos) {
			System.out.println(v);
		}
		
		//System.out.println(playlist.getAllVideos());

		
//		Video video = new Video("Video 1", "youtube.com/video/12345", 200);
//				
//		System.out.println(library.addVideo(video));
		
		System.out.println(playlist.getVideosByTitle("Marvel Infinity War Trailer"));
		
		
	}


}
