package com.flur.entity.view;

import com.flur.common.util.UserContextUtil;
import com.flur.persistence.db.annotation.Table;

/**
 * 此对象用于和视图关联查询
 * Created by marvin on 15/9/11.
 */
@Table(name = "player_games")
public class GameForPlayer {

	private int id;
	private int playerId;
	private int playerType;
	private int playerStatus;
	private String finishTime;	//完成时间
	private String title;
	private int creatorId;
	private int minutes;		//游戏时间
	private int questionCount;	//问题总数
	private int playerCount;	//完成答题的人数
	private int type;			//0私有，1公开
	private int status;			//0进行中，1已结束
	private String createTime;
	private String players;		//参与者，包括被邀请的和主动加入的，是一个json数组，["123", "123", "123"]这种格式
	private String name;
	private String gender;
	private String birthday;
	private String job;
	private String photoId;
	private int likedCount;		//被多少人喜欢
	
	public String getPhotoUrl(){
		return UserContextUtil.getPhotoUrl(this.photoId);
	}

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

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public int getLikedCount() {
		return likedCount;
	}

	public void setLikedCount(int likedCount) {
		this.likedCount = likedCount;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getPlayerType() {
		return playerType;
	}

	public void setPlayerType(int playerType) {
		this.playerType = playerType;
	}

	public int getPlayerStatus() {
		return playerStatus;
	}

	public void setPlayerStatus(int playerStatus) {
		this.playerStatus = playerStatus;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	
}
