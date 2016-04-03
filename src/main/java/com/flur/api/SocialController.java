package com.flur.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flur.common.util.PageInfo;
import com.flur.entity.User;
import com.flur.service.SocialService;

/**
 * 社交相关的Controller
 * @author wx
 *
 */
@RestController
@RequestMapping("/api/social")
public class SocialController {
	
	@Resource
	private SocialService socialService;
	
	/**
	 * 发现人
	 * @param loginUserId
	 * @param page
	 * @return
	 */
	@RequestMapping("/explorePeople")
	public ApiMsg explorePeople(int loginUserId, Integer page){
		PageInfo<User> pageInfo = socialService.explorePeople(loginUserId, page);
		return new ApiMsg(ApiMsg.SUCCESS, pageInfo.toApiJson());
	}
	
	/**
	 * 喜欢人
	 * @param loginUserId
	 * @param likeId
	 * @return
	 */
	@RequestMapping("/like")
	public ApiMsg explorePeople(int loginUserId, int likeId){
		socialService.saveUserLike(loginUserId, likeId);
		return new ApiMsg(ApiMsg.SUCCESS);
	}
	
}
