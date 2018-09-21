package com.revature.dao;

import java.util.List;

import com.revature.models.Vote;

public interface VoteDao {
	List<Vote> getVotes();
	Vote getVoteById(int id);
	List<Vote> getVotesByUserId(String id);
	int createVote(Vote vote);
}
