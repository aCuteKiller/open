package bai.demo.ssedemo.controller;

import bai.demo.ssedemo.pojo.MessageVO;
import bai.demo.ssedemo.utils.SseEmitterManager;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-27 14:28
 * @description
 */
@RestController
@RequestMapping("/sse")
public class SseController {
    @Autowired
    private SseEmitterManager sseEmitterManager;

    /**
     * 用户连接SSE
     *
     * @param userId 从请求参数或Header中获取用户ID
     */
    @GetMapping("/connect")
    public SseEmitter connect(@RequestParam String userId) {
        return sseEmitterManager.createEmitter(userId);
    }

    /**
     * 测试：向指定用户推送消息
     */
    @PostMapping("/send")
    public String sendMessage(@RequestParam String userId, @RequestParam String message) {
        sseEmitterManager.sendToUser(userId, JSONUtil.toJsonStr(new MessageVO(message, userId)));
        return "Sent to user: " + userId;
    }
}
