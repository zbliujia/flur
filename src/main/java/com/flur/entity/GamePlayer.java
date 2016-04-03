package com.flur.entity;

import com.flur.persistence.db.annotation.Table;

@Table(name="game_players")
public class GamePlayer {
	
	private int id;
	private int gameId;
	private int playerId;
	private int type;	//0被邀请，1主动进入
	private int status;	//0未参加，1已参加，2已经完成
	private String finishTime;	//完成时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
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
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	
}
