package bai.demo.ssedemo.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-27 14:54
 * @description
 */
@Component
public class SseEmitterManager {
    // 存储用户ID与SseEmitter的映射（线程安全）
    private static final Map<String, SseEmitter> userEmitters = new ConcurrentHashMap<>();

    /**
     * 创建连接
     *
     * @param userId 用户唯一标识
     */
    public SseEmitter createEmitter(String userId) {
        if (userId == null || userId.isEmpty())
            return null;
        // 设置超时时间（0表示永不超时）
        SseEmitter emitter = new SseEmitter(0L);

        // 注册回调：连接完成或出错时移除存储
        emitter.onCompletion(() -> userEmitters.remove(userId));
        emitter.onError(e -> {
            userEmitters.remove(userId);
            emitter.completeWithError(e);
        });

        // 存储当前连接
        userEmitters.put(userId, emitter);
        return emitter;
    }

    /**
     * 向指定用户发送消息
     *
     * @param userId 目标用户ID
     * @param data   发送的数据
     */
    public void sendToUser(String userId, Object data) {
        SseEmitter emitter = userEmitters.get(userId);
        if (emitter != null) {
            try {
                emitter.send(data);
            } catch (IOException e) {
                // 发送失败则移除该用户
                userEmitters.remove(userId);
                emitter.completeWithError(e);
            }
        }
    }

    // 获取所有在线用户（可选）
    public static Set<String> getOnlineUsers() {
        return userEmitters.keySet();
    }
}