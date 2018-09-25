package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.revature.exceptions.VideoNotFoundException;
import com.revature.models.Video;

@WebService(endpointInterface="com.revature.service.Playlist")
public class PlaylistImpl implements Playlist {

	private List<Video> videoList = new ArrayList<>();
	
	public PlaylistImpl() {
		videoList.add(new Video("Marvel Infinity War Trailer", "https://www.youtube.com/results?search_query=marvel+infinity+war+trailer", 145));
		videoList.add(new Video("Weezer - Africa (starring Weird Al Yankovic)", "https://www.youtube.com/watch?v=mk5Dwg5zm2U", 243));
	}
	
	@Override
	public List<Video> getAllVideos() {
		return this.videoList;
	}

	@Override
	public String addVideo(Video video) {
		this.videoList.add(video);
		return video.getTitle()+" added video to the playlist";
	}

	@Override
	public List<Video> getVideosByTitle(String title) throws VideoNotFoundException {
		List<Video> videos = new ArrayList<>();
		for (int i = 0; i < videoList.size(); i++) {
			if (videoList.get(i).getTitle().equals(title)) {
				videos.add(videoList.get(i));
			}
		}
		if (videos.size()==0) {
			throw new VideoNotFoundException();
		}
		return videos;
	}
	
	
}
