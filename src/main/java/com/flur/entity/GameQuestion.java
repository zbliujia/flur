package com.flur.entity;

import com.flur.persistence.db.annotation.Table;

/**
 * 问题回答
 * @author morris
 *
 */
@Table(name = "game_questions")
public class GameQuestion {
	
	private int id;
	private int gameId;
	private String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

}
