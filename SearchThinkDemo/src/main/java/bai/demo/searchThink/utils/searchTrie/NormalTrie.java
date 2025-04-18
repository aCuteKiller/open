package bai.demo.searchThink.utils.searchTrie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 12:44
 * @description 字典搜索树，用于搜索联想
 */
public class NormalTrie implements Serializable, ITrie {
    public static final int MAX_SIZE = 10;

    private static volatile NormalTrie instance;
    private final TrieNode root;

    private NormalTrie() {
        root = new TrieNode();
    }

    public static NormalTrie getInstance() {
        if (instance == null) {
            synchronized (NormalTrie.class) {
                if (instance == null) {
                    instance = new NormalTrie();
                }
            }
        }
        return instance;
    }

    static class TrieNode {
        // 标识是否为终节点(用于判断当前词是否是一个句子的结束词)
        Boolean isEnd;
        // 多少个路线通过该节点
        Integer num;
        // 保存所有儿子节点
        Map<Character, TrieNode> sonMap;

        TrieNode() {
            isEnd = false;
            num = 1;
            sonMap = new HashMap<Character, TrieNode>();
        }
    }

    /**
     * 将句子插入树
     *
     * @param sentence
     */
    @Override
    public void insert(String sentence) {
        TrieNode cur = root;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (!cur.sonMap.containsKey(c)) {
                cur.sonMap.put(c, new TrieNode());
            }
            cur = cur.sonMap.get(c);
            cur.num++;
        }
        cur.isEnd = true;
    }

    /**
     * 在树种查找句子是否存在
     *
     * @param sentence
     * @return
     */
    @Override
    public boolean search(String sentence) {
        TrieNode cur = root;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (cur.sonMap.containsKey(c)) {
                cur = cur.sonMap.get(c);
            } else {
                return false;
            }
        }
        return cur.isEnd;
    }

    /**
     * 在树种查找句子的前缀，返回前缀列表
     *
     * @param sentence
     * @return
     */
    @Override
    public List<String> searchList(String sentence) {
        List<String> resultList = new ArrayList<>();
        TrieNode cur = root;

        // 检查当前句子是否存在于树中
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (cur.sonMap.containsKey(c)) {
                cur = cur.sonMap.get(c);
            } else {
                // 如果树中不包含该字符，则返回空列表
                return resultList;
            }
        }

        // 使用递归遍历数，找到以当前句子为前缀的所有句子
        this.traverse(cur, sentence, resultList, MAX_SIZE);

        return resultList;
    }

    /**
     * 递归遍历树，找到以当前句子为前缀的所有句子
     *
     * @param startNode
     * @param prefix
     * @param resultList
     * @param max
     */
    private void traverse(TrieNode startNode, String prefix, List<String> resultList, int max) {
        if (resultList.size() >= max) {
            return;
        }
        // 如果当前节点是一个句子的结束节点，则将其添加到结果列表中
        if (startNode.isEnd) {
            resultList.add(prefix);
        }

        for (Map.Entry<Character, TrieNode> entry : startNode.sonMap.entrySet()) {
            char c = entry.getKey();
            TrieNode nextNode = entry.getValue();
            traverse(nextNode, prefix + c, resultList, max);
        }
    }

}
