package bai.demo.searchThink.service;

import bai.demo.searchThink.pojo.dto.STBlogPublishDTO;
import bai.demo.searchThink.pojo.po.STBLogPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 13:50
 * @description 博客服务层接口
 */
public interface ISTBlogService extends IService<STBLogPO> {

    void publish(STBlogPublishDTO dto);

    List<String> searchAutoComplete(String sentence);
}
