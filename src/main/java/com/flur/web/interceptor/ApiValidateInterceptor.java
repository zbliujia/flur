package com.flur.web.interceptor;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.flur.api.ApiMsg;
import com.flur.common.util.Constants;
import com.flur.common.util.DateUtil;
import com.flur.common.util.Validator;
import com.flur.entity.User;
import com.flur.entity.UserSecure;
import com.flur.persistence.db.SqlOperations;
import com.flur.persistence.db.support.SqlUpdate;
import com.flur.persistence.redis.RedisManager;
import com.flur.service.UserService;


/**
 * 手机客户端验证拦截器
 * @author wx
 * 
 */
public class ApiValidateInterceptor implements HandlerInterceptor {
	
	@Resource
	private RedisManager redisManager;
	
	@Resource
	private UserService userService;
	
	@Resource
	private SqlOperations sqlOperations;
	
	/**
	 * 在类映射之前处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		String uri = request.getRequestURI();
		//验证客户端请求头信息是否合法
//		String version = request.getParameter("appVersion");//软件版本号
		if(uri.startsWith("/api/user/login")
				|| uri.startsWith("/api/user/sendVerification")
				|| uri.startsWith("/api/user/register")){
			//某些url不需要验证
			return true;
		}
		String token = request.getParameter("loginUserToken");
		String userId = request.getParameter("loginUserId");
		if(Validator.isEmpty(token) || Validator.isEmpty(userId)){
			ApiMsg msg = new ApiMsg(ApiMsg.EXPIRED);
			response.setContentType("application/json");
			response.getWriter().write(JSONObject.toJSONString(msg));
			return false;
		}
		//取用户的登录信息，查找顺序 redis -》database
		String redisKey = Constants.REDIS_KEY_TOKEN + userId;
		String serverToken = redisManager.get(redisKey);
		if(Validator.notEmpty(serverToken)){
			if(serverToken.equals(token)){
				//匹配，重新设置两小时过期，实际感觉不需要，因为当redis中找不到时，只需要去数据库中查一次，两小时内不用重新查，比每次请求都要刷redis时间要好
//				redisManager.expire(redisKey, 60 * 60 * 2);
				return true;
			}else{
				ApiMsg msg = new ApiMsg(ApiMsg.EXPIRED);
				response.setContentType("application/json");
				response.getWriter().write(JSONObject.toJSONString(msg));
				return false;
			}
		}else{
			//redis中不存在，查找数据库
			UserSecure secure = userService.findSecureById(Integer.parseInt(userId));
			if(secure != null && token.equals(secure.getToken())){
				//数据库中验证成功，添加到redis
				redisManager.setex(redisKey, Constants.REDIS_TOKEN_EXPIRE, secure.getToken());
				//也认为是一次成功登录
				SqlUpdate update = new SqlUpdate().inc("loginCount", 1).set("lastLogin", DateUtil.currentTime());
				sqlOperations.update(Integer.parseInt(userId), update, User.class);
				return true;
			}else{
				ApiMsg msg = new ApiMsg(ApiMsg.EXPIRED);
				response.setContentType("application/json");
				response.getWriter().write(JSONObject.toJSONString(msg));
				return false;
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
