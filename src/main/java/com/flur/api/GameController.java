package com.flur.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.flur.common.util.PageInfo;
import com.flur.common.util.UserContextUtil;
import com.flur.common.util.Validator;
import com.flur.entity.Game;
import com.flur.entity.view.GameForPlayer;
import com.flur.entity.view.GameWithCreator;
import com.flur.service.GameService;

@RestController
@RequestMapping("/api/game")
public class GameController {
	
	@Resource
	private GameService gameService;
	
	/**
	 * 创建新游戏
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/create")
	public ApiMsg create(HttpServletRequest request, String title, 
			String[] questions, Integer type, Integer minutes, String[] players){
		boolean valid = Validator.checkParameter(request, "title", "questions", "type", "minutes");
		if(!valid){
			return new ApiMsg(ApiMsg.FAILED, "验证失败，请重新填写");
		}
		int userId = UserContextUtil.getUserId(request);
		gameService.createGame(title, questions, type, minutes, userId, players);
		return new ApiMsg(ApiMsg.SUCCESS);
	}
	
	/**
	 * 获取我的游戏
	 * @param request
	 * @param status 按状态查询，不传则查询全部
	 * @return
	 */
	@RequestMapping(value = "/getCreatedGames")
	public ApiMsg getCreatedGames(int loginUserId, Integer status){
		List<Game> games = gameService.getCreatedGames(loginUserId, status);
		return new ApiMsg(ApiMsg.SUCCESS, games);
	}
	
	/**
	 * 获取邀请我的游戏
	 * @return
	 */
	@RequestMapping("/getInvitedGames")
	public ApiMsg getInvitedGames(int loginUserId){
		List<GameForPlayer> games = gameService.getInvitedGames(loginUserId);
		return new ApiMsg(ApiMsg.SUCCESS, games);
	}
	
	/**
	 * 获取公共开可加入的游戏
	 * @param loginUserId
	 * @return
	 */
	@RequestMapping("/getPublicGames")
	public ApiMsg getPublicGames(int loginUserId, Integer page){
		PageInfo<GameWithCreator> games = gameService.getPublicGames(loginUserId, page);
		return new ApiMsg(ApiMsg.SUCCESS, games.toApiJson());
	}
	
	/**
	 * 获取我参与过的游戏
	 * @param loginUserId
	 * @return
	 */
	@RequestMapping("/getPlayedGames")
	public ApiMsg getPlayedGames(int loginUserId, Integer page){
		PageInfo<GameForPlayer> games = gameService.getPlayedGames(loginUserId, page);
		return new ApiMsg(ApiMsg.SUCCESS, games.toApiJson());
	}
	
	/**
	 * 加入游戏
	 * @param loginUserId
	 * @param gameId
	 * @return
	 */
	@RequestMapping("/attendGame")
	public ApiMsg attendGame(int loginUserId, int gameId){
		gameService.attendGame(loginUserId, gameId);
		return new ApiMsg(ApiMsg.SUCCESS);
	}
	
	/**
	 * 获取游戏的详情
	 * @param gameId
	 * @return
	 */
	@RequestMapping("/getGameDetails")
	public ApiMsg getGameDetails(int gameId){
		JSONObject detials = gameService.getGameDetails(gameId);
		return new ApiMsg(ApiMsg.SUCCESS, detials);
	}
	
	/**
	 * 回答问题
	 * @param gameId
	 * @param answers
	 * @return
	 */
	@RequestMapping("/setAnswers")
	public ApiMsg setAnswers(int loginUserId, int gameId, String[] answers){
		gameService.setAnswers(loginUserId, gameId, answers);
		return new ApiMsg(ApiMsg.SUCCESS);
	}
}
