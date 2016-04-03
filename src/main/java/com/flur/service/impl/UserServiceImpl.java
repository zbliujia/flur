package com.flur.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.flur.common.util.Constants;
import com.flur.common.util.DateUtil;
import com.flur.common.util.UserContextUtil;
import com.flur.entity.User;
import com.flur.entity.UserSecure;
import com.flur.persistence.db.SqlOperations;
import com.flur.persistence.db.support.SqlCriteria;
import com.flur.persistence.db.support.SqlQuery;
import com.flur.persistence.db.support.SqlUpdate;
import com.flur.persistence.redis.RedisManager;
import com.flur.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private SqlOperations sqlOperations;
	
	@Resource
	private RedisManager redisManager;
	
	@Override
	@Transactional
	public String addUser(User user, String password) {
		user.setLoginCount(1);
		String now = DateUtil.currentTime();
		user.setCreateTime(now);
		user.setLastLogin(now);
		sqlOperations.insert(user);
		UserSecure secure = new UserSecure();
		String token = UUID.randomUUID().toString().toLowerCase();
		secure.setId(user.getId());
		secure.setPassword(UserContextUtil.encryptPassword(password));
		secure.setToken(token);
		redisManager.setex(Constants.REDIS_KEY_TOKEN + user.getId(), Constants.REDIS_TOKEN_EXPIRE, token);
		sqlOperations.insert(secure);
		return token;
	}

	@Override
	@Transactional
	public void updateUserInfo(int userId, String birthday, String job, MultipartFile photo) {
		SqlUpdate update = new SqlUpdate();
		update.set("birthday", birthday);
		update.set("job", job);
		sqlOperations.update(userId, update, User.class);
	}

	@Override
	public User findByPhone(String phone) {
		SqlQuery query = new SqlQuery(SqlCriteria.where("phone").is(phone));
		return sqlOperations.findOne(query, User.class);
	}

	@Override
	public UserSecure findSecureById(int id) {
		return sqlOperations.findById(id, UserSecure.class);
	}

	@Override
	@Transactional
	public String updateUserToken(int userId) {
		String token = UUID.randomUUID().toString().toLowerCase();
		SqlUpdate update = new SqlUpdate();
		update.set("token", token);
		sqlOperations.update(userId, update, UserSecure.class);
		redisManager.setex(Constants.REDIS_KEY_TOKEN + userId, Constants.REDIS_TOKEN_EXPIRE, token);
		update = new SqlUpdate().inc("loginCount", 1).set("lastLogin", DateUtil.currentTime());
		sqlOperations.update(userId, update, User.class);
		return token;
	}

}
