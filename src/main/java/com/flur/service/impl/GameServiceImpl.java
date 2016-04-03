package com.flur.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.flur.common.util.DateUtil;
import com.flur.common.util.PageInfo;
import com.flur.common.util.Validator;
import com.flur.entity.Game;
import com.flur.entity.GameAnswer;
import com.flur.entity.GamePlayer;
import com.flur.entity.GameQuestion;
import com.flur.entity.view.GameForPlayer;
import com.flur.entity.view.GameWithCreator;
import com.flur.persistence.db.SqlOperations;
import com.flur.persistence.db.support.SqlCriteria;
import com.flur.persistence.db.support.SqlOrder;
import com.flur.persistence.db.support.SqlQuery;
import com.flur.persistence.db.support.SqlUpdate;
import com.flur.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	@Resource
	private SqlOperations sqlOperations;
	
	@Override
	@Transactional
	public void createGame(String title, String[] questions, Integer type,
			Integer minutes, Integer creatorId, String[] players) {
		int questionsCount = 0;
		for(String question : questions){
			if(Validator.notEmpty(question)){
				questionsCount++;
			}
		}
		Game game = new Game();
		game.setCreateTime(DateUtil.currentTime());
		game.setMinutes(minutes);
		game.setType(type);
		game.setTitle(title);
		game.setPlayerCount(0);
		game.setQuestionCount(questionsCount);
		game.setCreatorId(creatorId);
		game.setStatus(0);
		game.setPlayers(JSONObject.toJSONString(players));
		sqlOperations.insert(game);
		//保存问题
		for(String question : questions){
			if(Validator.isEmpty(question)){
				continue;
			}
			GameQuestion gameQuestion = new GameQuestion();
			gameQuestion.setContent(question);
			gameQuestion.setGameId(game.getId());
			sqlOperations.insert(gameQuestion);
		}
		if(players != null){
			//保存参与者
			for(String playId : players){
				GamePlayer player = new GamePlayer();
				player.setGameId(game.getId());
				player.setStatus(0);
				player.setType(0);
				player.setPlayerId(Integer.parseInt(playId));
				sqlOperations.insert(player);
			}
		}
	}

	@Override
	public List<Game> getCreatedGames(int userId, Integer status) {
		SqlCriteria criteria = SqlCriteria.where("creatorId").is(userId);
		if(status != null){
			criteria.and("status").is(status);
		}
		SqlQuery query = new SqlQuery(criteria);
		query.sort().on("id", SqlOrder.DESCENDING);
		return sqlOperations.find(query, Game.class);
	}

	@Override
	public List<GameForPlayer> getInvitedGames(int userId) {
		SqlQuery query = new SqlQuery(SqlCriteria.where("playerId").is(userId)
				.and("playerType").is(0)
				.and("playerStatus").is(0)
				.and("status").is(0));
		query.sort().on("id", SqlOrder.DESCENDING);
		List<GameForPlayer> games = sqlOperations.find(query, GameForPlayer.class);
		return games;
	}

	@Override
	public PageInfo<GameWithCreator> getPublicGames(int userId, Integer page) {
		int curPage = 1;
		int pageSize = 10;
		if(page != null){
			curPage = page;
		}
		SqlCriteria criteria = SqlCriteria.where("status").is(0).and("type").is(1).and("players").notlike("%\"" + userId + "\"%");
		SqlQuery query = new SqlQuery(criteria);
		query.sort().on("id", SqlOrder.DESCENDING);
		PageInfo<GameWithCreator> pageInfo = new PageInfo<GameWithCreator>(curPage, pageSize, query, GameWithCreator.class);
		pageInfo = sqlOperations.getObjectPagination(pageInfo);
		return pageInfo;
	}
	
	@Override
	public PageInfo<GameForPlayer> getPlayedGames(int userId, Integer page){
		int curPage = 1;
		int pageSize = 10;
		if(page != null){
			curPage = page;
		}
		SqlQuery query = new SqlQuery(SqlCriteria.where("playerId").is(userId)
				.and("playerStatus").is(2));
		query.sort().on("finishTime", SqlOrder.DESCENDING);
		PageInfo<GameForPlayer> pageInfo = new PageInfo<GameForPlayer>(curPage, pageSize, query, GameForPlayer.class);
		pageInfo = sqlOperations.getObjectPagination(pageInfo);
		return pageInfo;
	}

	@Override
	public void attendGame(int userId, int gameId) {
		//查询是否存在
		SqlQuery query = new SqlQuery(SqlCriteria.where("playerId").is(userId).and("gameId").is(gameId));
		GamePlayer player = sqlOperations.findOne(query, GamePlayer.class);
		if(player == null){
			//不存在才处理，并且肯定是公开加入的，因为如果是邀请加入，在创建游戏时就会创建此记录
			player = new GamePlayer();
			player.setGameId(gameId);
			player.setPlayerId(userId);
			player.setStatus(1);
			player.setType(1);
			sqlOperations.insert(player);
		}
		
	}

	@Override
	public JSONObject getGameDetails(int gameId) {
		GameWithCreator game = sqlOperations.findById(gameId, GameWithCreator.class);
		SqlQuery query = new SqlQuery(SqlCriteria.where("gameId").is(gameId));
		query.sort().on("id", SqlOrder.ASCENDING);
		List<GameQuestion> questions = sqlOperations.find(query, GameQuestion.class);
		JSONObject result = (JSONObject)JSONObject.toJSON(game);
		result.put("questions", questions);
		return result;
	}

	@Override
	@Transactional
	public void setAnswers(int userId, int gameId, String[] answers) {
		//更新用户状态
		SqlQuery playerQuery = new SqlQuery(SqlCriteria.where("playerId").is(userId).and("gameId").is(gameId));
		SqlUpdate playerUpdate = new SqlUpdate().set("status", 2).set("finishTime", DateUtil.currentTime());
		sqlOperations.update(playerQuery, playerUpdate, GamePlayer.class);
		//查询出所有问题
		SqlQuery query = new SqlQuery(SqlCriteria.where("gameId").is(gameId));
		query.sort().on("id", SqlOrder.ASCENDING);
		List<GameQuestion> questions = sqlOperations.find(query, GameQuestion.class);
		//然后依次添加答案
		for(int i = 0; i < questions.size() && i < answers.length; i++){
			GameQuestion question = questions.get(i);
			String answer = answers[i];
			GameAnswer an = new GameAnswer();
			an.setContent(answer);
			an.setGameId(gameId);
			an.setQuestionId(question.getId());
			an.setUserId(userId);
			sqlOperations.save(an);
		}
	}

}
