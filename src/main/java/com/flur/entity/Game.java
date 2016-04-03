package com.flur.entity;

import com.flur.persistence.db.annotation.Table;

/**
 * Created by marvin on 15/9/11.
 */
@Table(name = "games")
public class Game {

	private int id;
	private String title;
	private int creatorId;
	private int minutes;		//游戏时间
	private int questionCount;	//问题总数
	private int playerCount;	//完成答题的人数
	private int type;			//0私有，1公开
	private int status;			//0进行中，1已结束
	private String createTime;
	private String players;		//参与者，包括被邀请的和主动加入的，是一个json数组，["123", "123", "123"]这种格式
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public int getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPlayerCount() {
		return playerCount;
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	public String getPlayers() {
		return players;
	}
	public void setPlayers(String players) {
		this.players = players;
	}

}
