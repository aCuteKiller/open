package bai.demo.searchThink.service.impl;

import bai.demo.searchThink.mapper.STBlogMapper;
import bai.demo.searchThink.pojo.dto.STBlogPublishDTO;
import bai.demo.searchThink.pojo.po.STBLogPO;
import bai.demo.searchThink.service.ISTBlogService;
import bai.demo.searchThink.utils.searchTrie.ITrie;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 13:53
 * @description
 */
@Service
public class STBlogServiceImpl extends ServiceImpl<STBlogMapper, STBLogPO> implements ISTBlogService {

    @Resource(name = "stdTrie")
    private ITrie trie;

    @Override
    public void publish(STBlogPublishDTO dto) {
        // 插入博客
        this.save(BeanUtil.copyProperties(dto, STBLogPO.class));
        // 将博文标题建立字典树关系
        trie.insert(dto.getTitle());
    }

    @Override
    public List<String> searchAutoComplete(String sentence) {
        return trie.searchList(sentence);
    }
}
