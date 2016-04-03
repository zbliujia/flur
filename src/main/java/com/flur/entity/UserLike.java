package com.flur.entity;

import com.flur.persistence.db.annotation.Table;

/**
 * Created by marvin on 15/9/11.
 */
@Table(name = "user_like")
public class UserLike {

	private int id;
	private int userId;
	private int likeUserId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLikeUserId() {
		return likeUserId;
	}
	public void setLikeUserId(int likeUserId) {
		this.likeUserId = likeUserId;
	}	
	
}
