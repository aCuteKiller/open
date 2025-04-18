package bai.demo.searchThink.utils.redis;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 14:48
 * @description redis工具化组件
 */
@Component
public class RedisComponent {
    private static final String SEARCH_THINK_CACHE_KEY = "stc:";
    @Resource
    private RedisUtils<Object> redisUtils;

    public void setSearchThinkCache(String keyword, List<String> value) {
        redisUtils.set(SEARCH_THINK_CACHE_KEY + keyword, value, 60 * 60L);
    }

    public List<String> getSearchThinkCache(String keyword) {
        return (List<String>) redisUtils.get(SEARCH_THINK_CACHE_KEY + keyword);
    }
}
