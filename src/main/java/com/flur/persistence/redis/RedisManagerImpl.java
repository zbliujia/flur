package com.flur.persistence.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

@Component
public class RedisManagerImpl implements RedisManager {

	@Resource
	private JedisConnectionFactory factory;
	
	@Override
	public Long del(String... keys) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.del(keys);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		
	}
	
	@Override
	public String type(String key){
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.type(key);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Set<String> keys(String pattern) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.keys(pattern);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.ttl(key);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Boolean exists(String key) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.exists(key);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.expire(key, seconds);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long persist(String key) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.persist(key);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.set(key, value);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long setnx(String key, String value) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.setnx(key, value);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public String setex(String key, int seconds, String value) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.setex(key, seconds, value);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public String get(String key) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.get(key);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.incr(key);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long incrBy(String key, long increase) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.incrBy(key, increase);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long decr(String key) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.decr(key);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long decrBy(String key, long decrease) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.decrBy(key, decrease);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long hset(String hkey, String field, String value) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.hset(hkey, field, value);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long hsetnx(String hkey, String field, String value) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.hsetnx(hkey, field, value);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public String hmset(String hkey, Map<String, String> hash) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.hmset(hkey, hash);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public String hget(String hkey, String field) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.hget(hkey, field);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Map<String, String> hgetAll(String hkey) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.hgetAll(hkey);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long hdel(String hkey, String field) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.hdel(hkey, field);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long hlen(String hkey) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.hlen(hkey);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Boolean hexists(String hkey, String field) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.hexists(hkey, field);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long hincrBy(String hkey, String field, long increase) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.hincrBy(hkey, field, increase);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Set<String> hkeys(String hkey) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.hkeys(hkey);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public List<String> hvals(String hkey) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.hvals(hkey);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long lpush(String lkey, String value) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.lpush(lkey, value);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long rpush(String lkey, String value) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.rpush(lkey, value);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public String lpop(String lkey) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.lpop(lkey);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public String rpop(String lkey) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.rpop(lkey);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	@Override
	public Long llen(String lkey) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.llen(lkey);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public List<String> lrange(String lkey, int start, int end) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.lrange(lkey, start, end);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long lrem(String lkey, int count, String value) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.lrem(lkey, count, value);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public String lset(String lkey, long index, String value) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.lset(lkey, index, value);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long sadd(String skey, String member) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.sadd(skey, member);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long srem(String skey, String member) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.srem(skey, member);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Set<String> smembers(String skey) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.smembers(skey);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Boolean sismember(String skey, String member) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.sismember(skey, member);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long scard(String skey) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.scard(skey);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public String spop(String skey) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.spop(skey);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Set<String> sinter(String... skeys) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.sinter(skeys);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Set<String> sunion(String... skeys) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.sunion(skeys);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Set<String> sdiff(String... skeys) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.sdiff(skeys);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long zadd(String zkey, double score, String member) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zadd(zkey, score, member);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long zrem(String zkey, String member) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zrem(zkey, member);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long zcard(String zkey) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zcard(zkey);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long zcount(String zkey, double min, double max) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zcount(zkey, min, max);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Double zscore(String zkey, String member) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zscore(zkey, member);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Double zincrby(String zkey, double score, String member) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zincrby(zkey, score, member);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Set<String> zrange(String zkey, int start, int end) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zrange(zkey, start, end);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Set<String> zrevrange(String zkey, int start, int end) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zrevrange(zkey, start, end);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	@Override
	public Set<String> zrangeByScore(String zkey, double min, double max){
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zrangeByScore(zkey, min, max);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	@Override
	public Set<String> zrangeByScore(String zkey, String min, String max){
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zrangeByScore(zkey, min, max);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long zrank(String zkey, String member) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zrank(zkey, member);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public Long zrevrank(String zkey, String member) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zrevrank(zkey, member);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	@Override
	public Long zremrangeByScore(String zkey, double start, double end){
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zremrangeByScore(zkey, start, end);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	@Override
	public Long zremrangeByScore(String zkey, String start, String end){
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.zremrangeByScore(zkey, start, end);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	@Override
	public Long publish(String channel, String message) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			return jedis.publish(channel, message);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	@Override
	public void subscribe(JedisPubSub pubSub, String... channels) {
		Jedis jedis = null;
		try{
			jedis = factory.getResource();
			jedis.subscribe(pubSub, channels);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

}
