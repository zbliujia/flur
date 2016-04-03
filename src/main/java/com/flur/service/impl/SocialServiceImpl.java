package com.flur.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flur.common.util.PageInfo;
import com.flur.entity.User;
import com.flur.entity.UserLike;
import com.flur.persistence.db.SqlOperations;
import com.flur.persistence.db.support.SqlCriteria;
import com.flur.persistence.db.support.SqlOrder;
import com.flur.persistence.db.support.SqlQuery;
import com.flur.persistence.db.support.SqlUpdate;
import com.flur.service.SocialService;

@Service
public class SocialServiceImpl implements SocialService {

	@Resource
	private SqlOperations sqlOperations;
	
	@Override
	public PageInfo<User> explorePeople(int userId, Integer page) {
		//设置性别，查找异性
		String gender = "女";
		User user = sqlOperations.findById(userId, User.class);
		if(user.getGender().equals("女")){
			gender = "男";
		}
		int curPage = 1;
		int pageSize = 10;
		if(page != null){
			curPage = page;
		}
		SqlCriteria criteria = SqlCriteria.where("gender").is(gender);
		SqlQuery query = new SqlQuery(criteria);
		query.sort().on("id", SqlOrder.DESCENDING);
		PageInfo<User> pageInfo = new PageInfo<User>(curPage, pageSize, query, User.class);
		pageInfo = sqlOperations.getObjectPagination(pageInfo);
		return pageInfo;
	}

	@Override
	@Transactional
	public void saveUserLike(int userId, int likeUserId) {
		UserLike ul = new UserLike();
		ul.setUserId(userId);
		ul.setLikeUserId(likeUserId);
		sqlOperations.insert(ul);
		//更新用户被喜欢数统计
		SqlUpdate update = new SqlUpdate().inc("likedCount", 1);
		sqlOperations.update(likeUserId, update, User.class);
	}

}
