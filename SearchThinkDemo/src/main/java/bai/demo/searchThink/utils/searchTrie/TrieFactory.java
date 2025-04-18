package bai.demo.searchThink.utils.searchTrie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 14:02
 * @description 字典树工厂
 */

@Configuration
public class TrieFactory {
    @Bean("stdTrie")
    public ITrie getTrie() {
        return CacheTrie.getInstance();
    }
}
