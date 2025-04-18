package bai.demo.searchThink.utils.searchTrie;

import java.util.List;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 13:40
 * @description 字典树接口
 */
public interface ITrie {
    void insert(String sentence);

    boolean search(String sentence);

    List<String> searchList(String sentence);
}
