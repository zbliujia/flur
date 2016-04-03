package com.flur.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.flur.common.util.PageInfo;
import com.flur.entity.Game;
import com.flur.entity.view.GameForPlayer;
import com.flur.entity.view.GameWithCreator;

/**
 * @author morris
 *
 */
public interface GameService {
	
	/**
	 * 创建
	 * @param title
	 * @param questions
	 * @param type
	 * @param minutes
	 */
	public void createGame(String title, String[] questions, 
			Integer type, Integer minutes, Integer creatorId, String[] players);
	
	/**
	 * 获取我的游戏
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Game> getCreatedGames(int userId, Integer status);
	
	/**
	 * 获取邀请我的游戏
	 * @param userId
	 * @return
	 */
	public List<GameForPlayer> getInvitedGames(int userId);
	
	/**
	 * 获取公开可加入的游戏，未结束的
	 * @param userId
	 * @param page
	 * @return
	 */
	public PageInfo<GameWithCreator> getPublicGames(int userId, Integer page);
	
	/**
	 * 获取我参与过的游戏
	 * @param userId
	 * @param page
	 * @return
	 */
	public PageInfo<GameForPlayer> getPlayedGames(int userId, Integer page);
	
	/**
	 * 加入游戏
	 * @param userId
	 * @param gameId
	 */
	public void attendGame(int userId, int gameId);
	
	/**
	 * 获取游戏的详情
	 * @param gameId
	 * @return
	 */
	public JSONObject getGameDetails(int gameId);
	
	/**
	 * 保存答案
	 * @param userId
	 * @param gameId
	 * @param answers
	 */
	public void setAnswers(int userId, int gameId, String[] answers);
	
}
