package bai.demo.searchThink.controller;

import bai.demo.searchThink.core.AjaxResult;
import bai.demo.searchThink.pojo.dto.STBlogPublishDTO;
import bai.demo.searchThink.service.ISTBlogService;
import bai.demo.searchThink.service.impl.STBlogServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 13:45
 * @description 博客接口控制器
 */

@Tag(name = "博客接口控制器")
@RestController
@RequestMapping("/st/blog")
public class STBlogController {

    @Resource(type = STBlogServiceImpl.class)
    private ISTBlogService service;

    @PostMapping("/publish")
    @Operation(summary = "发布博客", description = "发布博客",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "博客发布DTO",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = STBlogPublishDTO.class),
                            examples = {
                                    @io.swagger.v3.oas.annotations.media.ExampleObject(
                                            description = "博客发布DTO",
                                            value = """
                                                    {
                                                    "title":"测试文章",
                                                    "content":"测试标题"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
    )
    public AjaxResult publish(@Valid @RequestBody STBlogPublishDTO dto) {
        service.publish(dto);
        return AjaxResult.successMsg();
    }

    @GetMapping("/search/autocomplete")
    public AjaxResult searchAutoComplete(@RequestParam String sentence) {
        return AjaxResult.success(service.searchAutoComplete(sentence));
    }


}
