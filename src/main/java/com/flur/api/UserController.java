package com.flur.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.flur.common.util.Constants;
import com.flur.common.util.UserContextUtil;
import com.flur.common.util.Validator;
import com.flur.entity.User;
import com.flur.entity.UserSecure;
import com.flur.persistence.redis.RedisManager;
import com.flur.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private RedisManager redisManager;
	
	/**
	 * 发送验证码
	 * @param request
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/sendVerification")
	public ApiMsg sendVerification(HttpServletRequest request, String phone){
		if(!Validator.isPhone(phone)){
			return new ApiMsg(ApiMsg.FAILED, "请输入正确的手机号");
		}
		String code = "666666";
		//存储到redis
		redisManager.setex(Constants.REDIS_KEY_VERIFY_CODE + phone, 30 * 60, code);
		return new ApiMsg(ApiMsg.SUCCESS);
	}
	
	/**
	 * 提交第一步注册
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register")
	public ApiMsg register(HttpServletRequest request, User user){
		boolean valid = Validator.checkParameter(request, "phone", "verifyCode", "name", "password", "gender");
		if(!valid){
			return new ApiMsg(ApiMsg.FAILED, "验证失败，请重新填写");
		}
		String phone = request.getParameter("phone");
		String verifyCode = request.getParameter("verifyCode");
		String sendCode = redisManager.get(Constants.REDIS_KEY_VERIFY_CODE + phone);
		redisManager.del(Constants.REDIS_KEY_VERIFY_CODE + phone);
		if(sendCode == null){
			return new ApiMsg(ApiMsg.FAILED, "验证码已经失效");
		}else{
			if(sendCode.equals(verifyCode)){
				//验证码验证成功
				//保存用户
				String token = userService.addUser(user, request.getParameter("password"));
				//构建返回前台的数据
				JSONObject result = (JSONObject)JSONObject.toJSON(user);
				result.put("token", token);
				return new ApiMsg(ApiMsg.SUCCESS, result);
			}else{
				return new ApiMsg(ApiMsg.FAILED, "验证码不正确");
			}
		}
	}
	
	/**
	 * 更新用户信息
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateInfo")
	public ApiMsg updateInfo(HttpServletRequest request, @RequestParam(value="photo", required=false)MultipartFile photo){
		boolean valid = Validator.checkParameter(request, "birthday", "job");
		if(!valid){
			return new ApiMsg(ApiMsg.FAILED, "验证失败，请重新填写");
		}
		String birthday = request.getParameter("birthday");
		String job = request.getParameter("job");
		int userId = UserContextUtil.getUserId(request);
		userService.updateUserInfo(userId, birthday, job, photo);
		return new ApiMsg(ApiMsg.SUCCESS);
	}
	
	/**
	 * 登录接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ApiMsg login(HttpServletRequest request, String phone, String password){
		boolean valid = Validator.checkParameter(request, "phone", "password");
		if(!valid){
			return new ApiMsg(ApiMsg.FAILED, "验证失败，请重新填写");
		}
		User user = userService.findByPhone(phone);
		if(user == null){
			return new ApiMsg(ApiMsg.FAILED, "手机号码不存在");
		}else{
			UserSecure secure = userService.findSecureById(user.getId());
			if(UserContextUtil.encryptPassword(password).equals(secure.getPassword())){
				//每次登录构建用户的token
				String token = userService.updateUserToken(user.getId());
				JSONObject result = (JSONObject)JSONObject.toJSON(user);
				result.put("token", token);
				return new ApiMsg(ApiMsg.SUCCESS, result);
			}else{
				return new ApiMsg(ApiMsg.FAILED, "密码不正确");
			}
		}
	}
	
}
