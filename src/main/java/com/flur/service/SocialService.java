package com.flur.service;

import com.flur.common.util.PageInfo;
import com.flur.entity.User;

/**
 * 社交相关的服务接口
 * @author wx
 *
 */
public interface SocialService {
	
	/**
	 * 分页发现用户
	 * @param userId
	 * @param page
	 * @return
	 */
	public PageInfo<User> explorePeople(int userId, Integer page);	
	
	/**
	 * 保存用户喜欢
	 * @param userId
	 * @param likeUserId
	 */
	public void saveUserLike(int userId, int likeUserId);
}
