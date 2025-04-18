package bai.demo.searchThink;


import bai.demo.searchThink.service.ISTBlogService;
import bai.demo.searchThink.service.impl.STBlogServiceImpl;
import bai.demo.searchThink.utils.searchTrie.ITrie;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartupRunner.class);
    @Resource(type = STBlogServiceImpl.class)
    private ISTBlogService stBlogService;
    @Resource(name = "stdTrie")
    private ITrie trie;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        stBlogService.list().forEach(stbLogPO -> {
            trie.insert(stbLogPO.getTitle());
        });
    }

}