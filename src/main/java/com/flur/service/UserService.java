package com.flur.service;

import org.springframework.web.multipart.MultipartFile;

import com.flur.entity.User;
import com.flur.entity.UserSecure;

/**
 * 用户服务接口
 * @author marvin
 *
 */
public interface UserService {
	
	/**
	 * 注册时添加用户
	 * @param user
	 * @param password
	 * @return 创建用户后新的token
	 */
	public String addUser(User user, String password);
	
	/**
	 * 更新用户信息
	 * @param userId
	 * @param birthday
	 * @param job
	 * @param file
	 */
	public void updateUserInfo(int userId, String birthday, String job, MultipartFile photo);
	
	/**
	 * 通过电话号码查找用户
	 * @param phone
	 * @return
	 */
	public User findByPhone(String phone);
	
	/**
	 * 通过id查找secure信息
	 * @param id
	 * @return
	 */
	public UserSecure findSecureById(int id);
	
	/**
	 * 更新用户的token
	 * @param userId
	 * @return
	 */
	public String updateUserToken(int userId);
	
}
