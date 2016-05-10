package com.froad.cache.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.froad.comon.util.Logger;
import com.froad.comon.util.PropertyUtil;

/**
 * @ClassName: RedisManager
 * @author FQ
 * @date 2015年3月12日 下午12:00:24
 */
public class RedisManager implements RedisService{
	private final static Logger logger = Logger.getLogger(RedisManager.class);
	private static JedisPool readPool = null;
	private static JedisPool writePool = null;
	
	public static final int read  = 0;	// 读
	public static final int write = 1;	// 写

	private static int pageCountExpireTime = 0;
	
	public void initial(){
		JedisPoolConfig readConfig = new JedisPoolConfig();
		// 设置池配置项值
		readConfig.setMaxTotal(Integer.valueOf(PropertyUtil.getProperties("redis.pool.maxTotal")));
		readConfig.setMaxIdle(Integer.valueOf(PropertyUtil.getProperties("redis.pool.maxIdle")));
		readConfig.setMaxWaitMillis(Long.valueOf(PropertyUtil.getProperties("redis.pool.maxWaitMillis")));
		readConfig.setTestOnBorrow(Boolean.valueOf(PropertyUtil.getProperties("redis.pool.testOnBorrow")));
		readConfig.setTestOnReturn(Boolean.valueOf(PropertyUtil.getProperties("redis.pool.testOnReturn")));
		
		readConfig.setTestOnBorrow(Boolean.valueOf(PropertyUtil.getProperties("read.redis.pool.testOnBorrow")));
		readConfig.setTestOnReturn(Boolean.valueOf(PropertyUtil.getProperties("read.redis.pool.testOnReturn")));

		String ip=PropertyUtil.getProperties("read.redis.ip");
		Integer port=Integer.parseInt(PropertyUtil.getProperties("read.redis.port"));
		
		// 根据配置实例化jedis池
		readPool = new JedisPool(readConfig,ip,port);
		writePool = new JedisPool(readConfig,ip,port);
		
		// 分页查找总数量过期时间
//		pageCountExpireTime = Integer.valueOf(props.getProperty("page.query.expire.time"));
	}
	/**
	 * 获取Redis实例
	 * @return
	 * Jedis
	 * 
	 * @author: FQ
	 * @date:2015年3月12日 下午1:03:37
	 */
	public static Jedis getJedis(int type) {
		switch (type) {
		case RedisManager.read:
			if (readPool != null) {
				return readPool.getResource();
			} 
			break;
		case RedisManager.write:
			if (writePool != null) {
				return writePool.getResource();
			}
			break;
		}
		return null;
	}
	
	/**
	 * 释放redis实例到连接池
	 * @param jedis
	 * void
	 * 
	 * @author: FQ
	 * @date:2015年3月12日 下午1:03:47
	 */
	public static void returnJedis(Jedis jedis,int type) {
		
		switch (type) {
		case RedisManager.read:
			if (jedis != null) {
				readPool.returnResource(jedis);
			} 
			break;
		case RedisManager.write:
			if (jedis != null) {
				writePool.returnResource(jedis);
			}
			break;
		}
		
	}

	public String putString(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(RedisManager.write);// 获得jedis实例
			return jedis.set(key, value);
		}finally {
			returnJedis(jedis,RedisManager.write);
		}
	}

	public String putExpire(String key, String value, int seconds) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = getJedis(RedisManager.write);// 获得jedis实例
			return jedis.setex(key, seconds, value);
		}finally {
			returnJedis(jedis,RedisManager.write);
		}
	}
	
	
	public String getString(String key) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = getJedis(RedisManager.read);// 获得jedis实例
			value = jedis.get(key);
		}finally {
			returnJedis(jedis,RedisManager.read);
		}
		return value;
	}

	public String putMap(String key, Map<String, String> valueMap) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = getJedis(RedisManager.write);// 获得jedis实例
			res = jedis.hmset(key, valueMap);
		} finally {
			returnJedis(jedis,RedisManager.write);
		}
		return res;
	}

	public Set<String>  sinterMap(String... keys){
		Jedis jedis = null;
		Set<String> res = null;
		try {
			jedis = getJedis(RedisManager.read);// 获得jedis实例
			res = jedis.sinter(keys);
		} finally {
			returnJedis(jedis,RedisManager.read);
		}
		return res;
	}
	public Map<String, String> getMap(String key) {
		Jedis jedis = null;
		Map<String, String> res = null;
		try {
			jedis = getJedis(RedisManager.read);// 获得jedis实例
			res = jedis.hgetAll(key);
		} finally {
			returnJedis(jedis,RedisManager.read);
		}
		return res;
	}

	public Long putSet(String key, Set<String> valueSet) {
	    if(valueSet.size()<1){
	    	return 0l;
	    }
		Jedis jedis = null;
		Long res = null;
		try {
			String[] members = null;
			members = new String[valueSet.size()];
			valueSet.toArray(members);

			jedis = getJedis(RedisManager.write);// 获得jedis实例
			res = jedis.sadd(key, members);
		} finally {
			returnJedis(jedis,RedisManager.write);
		}
		return res;
	}
	
	public Long put(String key, String... members) {
		Jedis jedis = null;
		Long res = null;
		try {		

			jedis = getJedis(RedisManager.write);// 获得jedis实例
			res = jedis.sadd(key, members);
		} finally {
			returnJedis(jedis,RedisManager.write);
		}
		return res;
	}

	public Set<String> getSet(String key) {
		Jedis jedis = null;
		Set<String> res = null;
		try {
			jedis = getJedis(RedisManager.read);// 获得jedis实例
			res = jedis.smembers(key);
		}finally {
			returnJedis(jedis,RedisManager.read);
		}
		return res;
	}

	public Boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis(RedisManager.read);// 获得jedis实例
			return jedis.exists(key);
		}finally {
			returnJedis(jedis,RedisManager.read);
		}
	}

	public Long putList(String key, List<String> valueList) {
		Jedis jedis = null;
		Long res = null;
		try {
			String[] strings = null;
			strings = new String[valueList.size()];
			valueList.toArray(strings);

			jedis = getJedis(RedisManager.write);// 获得jedis实例
			res = jedis.rpush(key, strings);
		}finally {
			returnJedis(jedis,RedisManager.write);
		}
		return res;
	}

	public List<String> getList(String key) {
		Jedis jedis = null;
		List<String> res = null;
		try {
			jedis = getJedis(RedisManager.read);// 获得jedis实例
			res = jedis.hvals(key);
		}finally {
			returnJedis(jedis,RedisManager.read);
		}
		return res;
	}
	
	public Long del(String... keys) {
		Jedis jedis = null;
		try {
			jedis = getJedis(RedisManager.write);// 获得jedis实例
			return jedis.del(keys);
		}  finally {
			returnJedis(jedis,RedisManager.write);
		}
	}
	
	/**
	 * Get field value from redis hash
	 * 
	 * @param mapKey
	 * @param fieldKey
	 * @return
	 */
	public String getMapValue(String mapKey, String fieldKey){
		
		return this.hget(mapKey, fieldKey);
	}
	
	/**
	 * Get field value from redis hash
	 * 
	 * @param mapKey
	 * @param fieldKey
	 * @return
	 */
	public String hget(String key, String field){
		Jedis jedis = null;
		String value = null;
		
		try {
			jedis = getJedis(RedisManager.read);// 获得jedis实例
			value = jedis.hget(key, field);
		}  finally {
			returnJedis(jedis,RedisManager.read);
		}
		
		return value;
	}
	
	/**
	 * Set field value for redis hash
	 * 
	 * @param mapKey
	 * @param fieldKey
	 * @return
	 */
	public Long hset(String key, String field, String value){
		Jedis jedis = null;
		Long result = null;
		
		try {
			jedis = getJedis(RedisManager.write);// 获得jedis实例
			result = jedis.hset(key, field, value);
		}finally {
			returnJedis(jedis,RedisManager.write);
		}
		return result;
	}
	
	public Long incr(String key, Long max){
		Jedis jedis = null;
		Long value = -1l;
		
		try {
			jedis = getJedis(RedisManager.write);
			value = jedis.incr(key);
			if(value > max){
				jedis.set(key, "0");
				value = 0l;
			}
		}  finally {
			returnJedis(jedis,RedisManager.write);
		}
		return value;
	}

	@Override
	public Long srem(String key, String... values) {
		Jedis jedis = null;
		try {
			jedis = getJedis(RedisManager.write);
			return jedis.srem(key, values);
		} finally {
			returnJedis(jedis,RedisManager.write);
		}
	}
	
	public Long hincrBy(String key, String field, Long value) {
		Jedis jedis = null;
		try {
			jedis = getJedis(RedisManager.write);
			return jedis.hincrBy(key, field, value);
		} finally {
			returnJedis(jedis,RedisManager.write);
		}
	}
	
	/**
	 * 将 key中储存的数字值加increment
	 * @param key
	 * @param value
	 * @return
	 */
	public Long incrBy(String key, Long increment) {
		Jedis jedis = null;
		try {
			jedis = getJedis(RedisManager.write);
			return jedis.incrBy(key, increment);
		}  finally {
			returnJedis(jedis,RedisManager.write);
		}
	}
	
	/**
	 * 将 key中储存的数字值减value
	 * @param key
	 * @param value
	 * @return
	 */
	public Long decrBy(String key, Long value){
		Jedis jedis = null;
		try {
			jedis = getJedis(RedisManager.write);
			return jedis.decrBy(key,value);
		} finally {
			returnJedis(jedis,RedisManager.write);
		}
	}
	
	/**
	 * 
	 * @Title: zadd 
	 * @author vania
	 * @version 1.0
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long zadd(String key, double score, String member) {
		Jedis jedis = null;
		try {
			jedis = getJedis(RedisManager.write);
			return jedis.zadd(key, score, member);
		} finally {
			returnJedis(jedis,RedisManager.write);
		}
		
	}
	
	/**
	 * 
	 * @Title: zrem 
	 * @author vania
	 * @version 1.0
	 * @param key
	 * @param member
	 * @return
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long zrem(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = getJedis(RedisManager.write);
			return jedis.zrem(key, member);
		}  finally {
			returnJedis(jedis,RedisManager.write);
		}
		
	}
	
	
	public List<String> hmget(String key,String... fields){
		Jedis jedis = null;
		List<String> value = null;
		try {
			jedis = getJedis(RedisManager.read);// 获得jedis实例
			value = jedis.hmget(key, fields);
		}  finally {
			returnJedis(jedis,RedisManager.read);
		}
		
		return value;
	}
	
	public Long hdel (String key,String... fields){
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = getJedis(RedisManager.write);
			result = jedis.hdel(key, fields);
		} finally {
			returnJedis(jedis,RedisManager.write);
		}
		return result;
	}
	
	public Set<String> keys (String pattern) {
		Jedis jedis = null;
		Set<String> result = null;
		try {
			jedis = getJedis(RedisManager.read);
			result = jedis.keys(pattern);
		} finally {
			returnJedis(jedis,RedisManager.read);
		}
		return result;
	}
	
	public Long lpush(String key, String... values){
		Jedis jedis = null;
		Long result = 0l;
		try {
			jedis = getJedis(RedisManager.write);
			result = jedis.lpush(key, values);
		} finally {
			returnJedis(jedis,RedisManager.write);
		}
		return result;
	}
	
	public Long rpush(String key, String... values){
		Jedis jedis = null;
		Long result = 0l;
		try {
			jedis = getJedis(RedisManager.write);
			result = jedis.rpush(key, values);
		}  finally {
			returnJedis(jedis,RedisManager.write);
		}
		return result;
	}
	
	public List<String> lrange(String key, long start, long end) {
		Jedis jedis = null;
		List<String> result = null;
		try {
			jedis = getJedis(RedisManager.read);
			result = jedis.lrange(key, start, end);
		}  finally {
			returnJedis(jedis, RedisManager.read);
		}
		return result;
	}
	
	public Long llen(String key) {
		Jedis jedis = null;
		Long result = 0l;
		try {
			jedis = getJedis(RedisManager.read);
			result = jedis.llen(key);
		}  finally {
			returnJedis(jedis, RedisManager.read);
		}
		return result;
	}
	
	public Long expire (String key, int seconds) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = getJedis(RedisManager.write);// 获得jedis实例
			return jedis.expire(key, seconds);
		}  finally {
			returnJedis(jedis,RedisManager.write);
		}
	}
	
	public Long setnx (String key, String value) {
		Jedis jedis = null;
		Long res = 0l;
		try {
			jedis = getJedis(RedisManager.write);// 获得jedis实例
			return jedis.setnx(key, value);
		}finally{
			returnJedis(jedis,RedisManager.write);
		}
	}
	
	public String setex (String key,int seconds, String value) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = getJedis(RedisManager.write);// 获得jedis实例
			return jedis.setex(key,seconds, value);
		}  finally {
			returnJedis(jedis,RedisManager.write);
		}
	}
	
	@Override
	public Integer getPageQueryTotalCount(String md5PageKey) {
		Jedis jedis = null;
		Integer totalCount = 0;
		
		try {
			jedis = getJedis(RedisManager.read);// 获得jedis实例
			
			if (null != jedis.get(md5PageKey)){
				totalCount = Integer.valueOf(jedis.get(md5PageKey));
			}
		}  finally {
			returnJedis(jedis,RedisManager.read);
		}
		
		return totalCount;
	}

	@Override
	public void setPageQueryTotalCount(String md5PageKey, Integer count) {
		Jedis jedis = null;
		
		try {
			jedis = getJedis(RedisManager.write);// 获得jedis实例
			logger.debug("分页查询设置总记录数key:" + md5PageKey + ", count:"+ count);
			jedis.setex(md5PageKey, pageCountExpireTime, String.valueOf(count));
		} finally {
			returnJedis(jedis,RedisManager.write);
		}
	}
	
	/**
   * <p>通过key获取指定set中的交集</p>
   * @param keys 可以使一个string 也可以是一个string数组
   * @return
   */
	public Set<String> sinter(String...keys){
		Jedis jedis = null;
		Set<String> res = null;
		try {
			jedis = getJedis(RedisManager.read);// 获得jedis实例
			res = jedis.sinter(keys);
		}finally {
			returnJedis(jedis,RedisManager.read);
		}
		return res;
	}

	
	public static void main(String[] args) {
		
		//接口参考地址：  http://www.tuicool.com/articles/ieAvAzz
		
		RedisManager r=new RedisManager();
		System.out.println(r.putString("name", "String"));
		System.out.println(r.getString("name"));
		r.getJedis(write).sadd("sets1", "HashSet1");
		r.getJedis(write).sadd("sets1", "SortedSet1");
		r.getJedis(write).sadd("sets1", "TreeSet");
		r.getJedis(write).sadd("sets2", "HashSet2");
		r.getJedis(write).sadd("sets2", "SortedSet1");
		r.getJedis(write).sadd("sets2", "TreeSet1");
		System.out.println(r.sinterMap("sets1", "sets2"));
		ArrayList<String> a=new ArrayList<String>();
		ArrayList<String> aa=new ArrayList<String>();
		ArrayList<String> aaa=new ArrayList<String>();
		System.out.println(System.currentTimeMillis());
		for(int i=0;i<=1000000;i++){
			aaa.add("aaaaaaaaaaaaaaa"+String.valueOf(i));
			if(i%2==0){
				a.add("aaaaaaaaaaaaaaa"+String.valueOf(i));
			}
			if(i%6==0){
				aa.add("aaaaaaaaaaaaaaa"+String.valueOf(i));
			}
		}
		String[] sa = new String[a.size()];
		a.toArray(sa);
		
		String[] saa = new String[aa.size()];
		aa.toArray(saa);
		
		String[] saaa = new String[aaa.size()];
		aaa.toArray(saaa);
		r.put("test3", sa);
		r.put("test2", saa);
		r.put("test", saaa);
		
		// 交集
		Set<String> s=r.sinterMap("test3", "test2","test");
		System.out.println(s.size());
		r.del("test3");
		r.del("test2");
		r.del("test");
		System.out.println(System.currentTimeMillis());
	}
}
