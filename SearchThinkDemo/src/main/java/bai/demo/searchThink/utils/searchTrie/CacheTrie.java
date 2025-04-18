package bai.demo.searchThink.utils.searchTrie;

import bai.demo.searchThink.utils.redis.RedisComponent;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 13:42
 * @description 缓存字段树，通过redis缓存，将上次结果存放，下次访问直接拿取缓存
 */
@Component
public class CacheTrie implements ITrie {
    private static volatile CacheTrie instance;
    @Resource
    private RedisComponent redisComponent;

    private CacheTrie() {

    }

    public static CacheTrie getInstance() {
        if (instance == null) {
            synchronized (CacheTrie.class) {
                if (instance == null) {
                    instance = new CacheTrie();
                }
            }
        }
        return instance;
    }

    @Override
    public void insert(String sentence) {
        NormalTrie.getInstance().insert(sentence);
    }

    @Override
    public boolean search(String sentence) {
        NormalTrie.getInstance().search(sentence);
        return false;
    }

    @Override
    public List<String> searchList(String sentence) {
        List<String> resultInCache = redisComponent.getSearchThinkCache(sentence);
        if (resultInCache == null) {
            List<String> results = NormalTrie.getInstance().searchList(sentence);
            if (results.size() >= NormalTrie.MAX_SIZE) {
                redisComponent.setSearchThinkCache(sentence, results);
            }
            return results;
        }
        return resultInCache;
    }
}
