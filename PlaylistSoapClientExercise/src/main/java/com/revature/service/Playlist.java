package com.revature.service;

import java.util.List;

import javax.jws.WebService;

import com.revature.models.Video;

@WebService
public interface Playlist {
	
	public List<Video> getAllVideos();
	public String addVideo(Video video);
	public List<Video> getVideosByTitle(String title);

}
