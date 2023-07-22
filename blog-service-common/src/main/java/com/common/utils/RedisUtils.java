package com.common.utils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

/**
 * RedisUtils
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
@Component
public class RedisUtils extends CachingConfigurerSupport {
    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    public RedisUtils() {
    }

    public Long getTtl(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public void delete(String key) {
        this.stringRedisTemplate.delete(key);
    }

    public Set<String> likeKeys(String keys) {
        return this.stringRedisTemplate.keys(keys + "*");
    }

    public void delete(Collection<String> keys) {
        this.stringRedisTemplate.delete(keys);
    }

    public byte[] dump(String key) {
        return this.stringRedisTemplate.dump(key);
    }

    public Boolean hasKey(String key) {
        return this.stringRedisTemplate.hasKey(key);
    }

    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return this.stringRedisTemplate.expire(key, timeout, unit);
    }

    public Boolean expireAt(String key, Date date) {
        return this.stringRedisTemplate.expireAt(key, date);
    }

    public Set<String> keys(String pattern) {
        return this.stringRedisTemplate.keys(pattern);
    }

    public Boolean move(String key, int dbIndex) {
        return this.stringRedisTemplate.move(key, dbIndex);
    }

    public Boolean persist(String key) {
        return this.stringRedisTemplate.persist(key);
    }

    public Long getExpire(String key, TimeUnit unit) {
        return this.stringRedisTemplate.getExpire(key, unit);
    }

    public Long getExpire(String key) {
        return this.stringRedisTemplate.getExpire(key);
    }

    public String randomKey() {
        return (String)this.stringRedisTemplate.randomKey();
    }

    public void rename(String oldKey, String newKey) {
        this.stringRedisTemplate.rename(oldKey, newKey);
    }

    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return this.stringRedisTemplate.renameIfAbsent(oldKey, newKey);
    }

    public DataType type(String key) {
        return this.stringRedisTemplate.type(key);
    }

    public void set(String key, String value) {
        this.stringRedisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return (String)this.stringRedisTemplate.opsForValue().get(key);
    }

    public String getRange(String key, long start, long end) {
        return this.stringRedisTemplate.opsForValue().get(key, start, end);
    }

    public String getAndSet(String key, String value) {
        return (String)this.stringRedisTemplate.opsForValue().getAndSet(key, value);
    }

    public Boolean getBit(String key, long offset) {
        return this.stringRedisTemplate.opsForValue().getBit(key, offset);
    }

    public List<String> multiGet(Collection<String> keys) {
        return this.stringRedisTemplate.opsForValue().multiGet(keys);
    }

    public boolean setBit(String key, long offset, boolean value) {
        return this.stringRedisTemplate.opsForValue().setBit(key, offset, value);
    }

    public void setEx(String key, String value, long timeout, TimeUnit unit) {
        this.stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public void setExIfAbsent(String key, String value, long timeout, TimeUnit unit) {
        this.stringRedisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
    }

    public boolean setExIfAbsentRe(String key, String value, long timeout, TimeUnit unit) {
        return this.stringRedisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
    }

    public boolean setIfAbsent(String key, String value) {
        return this.stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public void setRange(String key, String value, long offset) {
        this.stringRedisTemplate.opsForValue().set(key, value, offset);
    }

    public Long size(String key) {
        return this.stringRedisTemplate.opsForValue().size(key);
    }

    public void multiSet(Map<String, String> maps) {
        this.stringRedisTemplate.opsForValue().multiSet(maps);
    }

    public boolean multiSetIfAbsent(Map<String, String> maps) {
        return this.stringRedisTemplate.opsForValue().multiSetIfAbsent(maps);
    }

    public Long incrBy(String key, long increment) {
        return this.stringRedisTemplate.opsForValue().increment(key, increment);
    }

    public Double incrByFloat(String key, double increment) {
        return this.stringRedisTemplate.opsForValue().increment(key, increment);
    }

    public Integer append(String key, String value) {
        return this.stringRedisTemplate.opsForValue().append(key, value);
    }

    public Object hGet(String key, String field) {
        return this.stringRedisTemplate.opsForHash().get(key, field);
    }

    public Map<Object, Object> hGetAll(String key) {
        return this.stringRedisTemplate.opsForHash().entries(key);
    }

    public List<Object> hMultiGet(String key, Collection<Object> fields) {
        return this.stringRedisTemplate.opsForHash().multiGet(key, fields);
    }

    public void hPut(String key, String hashKey, Object value) {
        this.stringRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    public void hPutAll(String key, Map<Object, Object> maps) {
        this.stringRedisTemplate.opsForHash().putAll(key, maps);
    }

    public Boolean hPutIfAbsent(String key, String hashKey, String value) {
        return this.stringRedisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    public Long hDelete(String key, Object... fields) {
        return this.stringRedisTemplate.opsForHash().delete(key, fields);
    }

    public boolean hExists(String key, String field) {
        return this.stringRedisTemplate.opsForHash().hasKey(key, field);
    }

    public Long hIncrBy(String key, Object field, long increment) {
        return this.stringRedisTemplate.opsForHash().increment(key, field, increment);
    }

    public Double hIncrByFloat(String key, Object field, double delta) {
        return this.stringRedisTemplate.opsForHash().increment(key, field, delta);
    }

    public Set<Object> hKeys(String key) {
        return this.stringRedisTemplate.opsForHash().keys(key);
    }

    public Long hSize(String key) {
        return this.stringRedisTemplate.opsForHash().size(key);
    }

    public List<Object> hValues(String key) {
        return this.stringRedisTemplate.opsForHash().values(key);
    }

    public Cursor<Map.Entry<Object, Object>> hScan(String key, ScanOptions options) {
        return this.stringRedisTemplate.opsForHash().scan(key, options);
    }

    public String lIndex(String key, long index) {
        return (String)this.stringRedisTemplate.opsForList().index(key, index);
    }

    public List<String> lRange(String key, long start, long end) {
        return this.stringRedisTemplate.opsForList().range(key, start, end);
    }

    public Long lLeftPush(String key, String value) {
        return this.stringRedisTemplate.opsForList().leftPush(key, value);
    }

    public Long lLeftPushAll(String key, String... value) {
        return this.stringRedisTemplate.opsForList().leftPushAll(key, value);
    }

    public Long lLeftPushAll(String key, Collection<String> value) {
        return this.stringRedisTemplate.opsForList().leftPushAll(key, value);
    }

    public Long lLeftPushIfPresent(String key, String value) {
        return this.stringRedisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    public Long lLeftPush(String key, String pivot, String value) {
        return this.stringRedisTemplate.opsForList().leftPush(key, pivot, value);
    }

    public Long lRightPush(String key, String value) {
        return this.stringRedisTemplate.opsForList().rightPush(key, value);
    }

    public Long lRightPushAll(String key, String... value) {
        return this.stringRedisTemplate.opsForList().rightPushAll(key, value);
    }

    public Long lRightPushAll(String key, Collection<String> value) {
        return this.stringRedisTemplate.opsForList().rightPushAll(key, value);
    }

    public Long lRightPushIfPresent(String key, String value) {
        return this.stringRedisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    public Long lRightPush(String key, String pivot, String value) {
        return this.stringRedisTemplate.opsForList().rightPush(key, pivot, value);
    }

    public void lSet(String key, long index, String value) {
        this.stringRedisTemplate.opsForList().set(key, index, value);
    }

    public String lLeftPop(String key) {
        return (String)this.stringRedisTemplate.opsForList().leftPop(key);
    }

    public String lBLeftPop(String key, long timeout, TimeUnit unit) {
        return (String)this.stringRedisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    public String lRightPop(String key) {
        return (String)this.stringRedisTemplate.opsForList().rightPop(key);
    }

    public String lBRightPop(String key, long timeout, TimeUnit unit) {
        return (String)this.stringRedisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    public String lRightPopAndLeftPush(String sourceKey, String destinationKey) {
        return (String)this.stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
    }

    public String lBRightPopAndLeftPush(String sourceKey, String destinationKey, long timeout, TimeUnit unit) {
        return (String)this.stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
    }

    public Long lRemove(String key, long index, String value) {
        return this.stringRedisTemplate.opsForList().remove(key, index, value);
    }

    public void lTrim(String key, long start, long end) {
        this.stringRedisTemplate.opsForList().trim(key, start, end);
    }

    public Long lLen(String key) {
        return this.stringRedisTemplate.opsForList().size(key);
    }

    public Long sAdd(String key, String... values) {
        return this.stringRedisTemplate.opsForSet().add(key, values);
    }

    public Long sRemove(String key, Object... values) {
        return this.stringRedisTemplate.opsForSet().remove(key, values);
    }

    public String sPop(String key) {
        return (String)this.stringRedisTemplate.opsForSet().pop(key);
    }

    public Boolean sMove(String key, String value, String destKey) {
        return this.stringRedisTemplate.opsForSet().move(key, value, destKey);
    }

    public Long sSize(String key) {
        return this.stringRedisTemplate.opsForSet().size(key);
    }

    public Boolean sIsMember(String key, Object value) {
        return this.stringRedisTemplate.opsForSet().isMember(key, value);
    }

    public Set<String> sIntersect(String key, String otherKey) {
        return this.stringRedisTemplate.opsForSet().intersect(key, otherKey);
    }

    public Set<String> sIntersect(String key, Collection<String> otherKeys) {
        return this.stringRedisTemplate.opsForSet().intersect(key, otherKeys);
    }

    public Long sIntersectAndStore(String key, String otherKey, String destKey) {
        return this.stringRedisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey);
    }

    public Long sIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return this.stringRedisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
    }

    public Set<String> sUnion(String key, String otherKeys) {
        return this.stringRedisTemplate.opsForSet().union(key, otherKeys);
    }

    public Set<String> sUnion(String key, Collection<String> otherKeys) {
        return this.stringRedisTemplate.opsForSet().union(key, otherKeys);
    }

    public Long sUnionAndStore(String key, String otherKey, String destKey) {
        return this.stringRedisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }

    public Long sUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return this.stringRedisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    public Set<String> sDifference(String key, String otherKey) {
        return this.stringRedisTemplate.opsForSet().difference(key, otherKey);
    }

    public Set<String> sDifference(String key, Collection<String> otherKeys) {
        return this.stringRedisTemplate.opsForSet().difference(key, otherKeys);
    }

    public Long sDifference(String key, String otherKey, String destKey) {
        return this.stringRedisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
    }

    public Long sDifference(String key, Collection<String> otherKeys, String destKey) {
        return this.stringRedisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
    }

    public Set<String> setMembers(String key) {
        return this.stringRedisTemplate.opsForSet().members(key);
    }

    public String sRandomMember(String key) {
        return (String)this.stringRedisTemplate.opsForSet().randomMember(key);
    }

    public List<String> sRandomMembers(String key, long count) {
        return this.stringRedisTemplate.opsForSet().randomMembers(key, count);
    }

    public Set<String> sDistinctRandomMembers(String key, long count) {
        return this.stringRedisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    public Cursor<String> sScan(String key, ScanOptions options) {
        return this.stringRedisTemplate.opsForSet().scan(key, options);
    }

    public Boolean zAdd(String key, String value, double score) {
        return this.stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    public Long zAdd(String key, Set<ZSetOperations.TypedTuple<String>> values) {
        return this.stringRedisTemplate.opsForZSet().add(key, values);
    }

    public Long zRemove(String key, Object... values) {
        return this.stringRedisTemplate.opsForZSet().remove(key, values);
    }

    public Double zIncrementScore(String key, String value, double delta) {
        return this.stringRedisTemplate.opsForZSet().incrementScore(key, value, delta);
    }

    public Long zRank(String key, Object value) {
        return this.stringRedisTemplate.opsForZSet().rank(key, value);
    }

    public Long zReverseRank(String key, Object value) {
        return this.stringRedisTemplate.opsForZSet().reverseRank(key, value);
    }

    public Set<String> zRange(String key, long start, long end) {
        return this.stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    public Set<ZSetOperations.TypedTuple<String>> zRangeWithScores(String key, long start, long end) {
        return this.stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    public Set<String> zRangeByScore(String key, double min, double max) {
        return this.stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max) {
        return this.stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max, long start, long end) {
        return this.stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, start, end);
    }

    public Set<String> zReverseRange(String key, long start, long end) {
        return this.stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeWithScores(String key, long start, long end) {
        return this.stringRedisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    public Set<String> zReverseRangeByScore(String key, double min, double max) {
        return this.stringRedisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    public Set<ZSetOperations.TypedTuple<String>> zReverseRangeByScoreWithScores(String key, double min, double max) {
        return this.stringRedisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    public Set<String> zReverseRangeByScore(String key, double min, double max, long start, long end) {
        return this.stringRedisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
    }

    public Long zCount(String key, double min, double max) {
        return this.stringRedisTemplate.opsForZSet().count(key, min, max);
    }

    public Long zSize(String key) {
        return this.stringRedisTemplate.opsForZSet().size(key);
    }

    public Long zZCard(String key) {
        return this.stringRedisTemplate.opsForZSet().zCard(key);
    }

    public Double zScore(String key, Object value) {
        return this.stringRedisTemplate.opsForZSet().score(key, value);
    }

    public Long zRemoveRange(String key, long start, long end) {
        return this.stringRedisTemplate.opsForZSet().removeRange(key, start, end);
    }

    public Long zRemoveRangeByScore(String key, double min, double max) {
        return this.stringRedisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    public Long zUnionAndStore(String key, String otherKey, String destKey) {
        return this.stringRedisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    public Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return this.stringRedisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    public Long zIntersectAndStore(String key, String otherKey, String destKey) {
        return this.stringRedisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
    }

    public Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return this.stringRedisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }

    public Cursor<ZSetOperations.TypedTuple<String>> zScan(String key, ScanOptions options) {
        return this.stringRedisTemplate.opsForZSet().scan(key, options);
    }
}