package bai.demo.fileuploaddemo.controller;

import bai.demo.fileuploaddemo.domain.ChunkInfo;
import bai.demo.fileuploaddemo.domain.StdResult;
import com.alibaba.fastjson2.JSON;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-05-02 11:01
 * @description
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private static final String UPLOAD_PATH = System.getProperty("user.dir");
    // 使用ConcurrentHashMap维护每个文件的上传锁
    private static final ConcurrentHashMap<String, Object> fileLocks = new ConcurrentHashMap<>();

    @PostMapping("/single")
    public StdResult single(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty())
            return StdResult.fail();
        saveFile(file, "single", UUID.randomUUID() + "-" + file.getOriginalFilename());
        return StdResult.success();
    }

    @PostMapping("/list")
    public StdResult list(@RequestParam("files") MultipartFile[] files) throws Exception {
        for (MultipartFile file : files)
            if (file.isEmpty()) return StdResult.fail();
        for (MultipartFile file : files)
            saveFile(file, "list", UUID.randomUUID() + "-" + file.getOriginalFilename());
        return StdResult.success();
    }

    @GetMapping("/chunk/progress")
    public StdResult chunkProgress(@RequestParam("fileHash") String hash) throws Exception {
        File file = Path.of(UPLOAD_PATH, "upload", "chunk", hash, "chunkInfo.json").toFile();
        if (!file.exists())
            return StdResult.fail();
        try {
            ChunkInfo chunkInfo;
            if (file.exists()) {
                return StdResult.success(JSON.parseObject(Files.readString(file.toPath()), ChunkInfo.class));
            }
        } catch (IOException ignore) {
            return StdResult.fail();
        }
        return StdResult.fail();
    }

    @PostMapping("/chunk/in")
    public StdResult chunk(@RequestParam("fileHash") String hash, @RequestParam("file") MultipartFile chunk,
                           @RequestParam("chunkNumber") Integer chunkNumber, @RequestParam("chunkSize") Integer chunkSize) throws Exception {
        Path dir = Path.of(UPLOAD_PATH, "upload", "chunk", hash);
        if (!dir.toFile().exists())
            dir.toFile().mkdirs();

        // 获取文件特定的锁对象
        Object fileLock = fileLocks.computeIfAbsent(hash, k -> new Object());
        /// 同步更新chunkInfo.json
        synchronized (fileLock) {
            File file = Path.of(dir.toFile().getAbsolutePath(), "chunkInfo.json").toFile();

            try {
                ChunkInfo chunkInfo;
                if (file.exists()) {
                    String content = Files.readString(file.toPath());
                    chunkInfo = JSON.parseObject(content, ChunkInfo.class);
                } else {
                    chunkInfo = new ChunkInfo(hash, chunkSize);
                }

                // 检查分片信息
                if (!Objects.equals(chunkInfo.getChunkSize(), chunkSize)
                        || chunkNumber >= chunkInfo.getChunkSize()
                        || chunkNumber < 0)
                    return StdResult.fail();

                // 写入分片
                Path path = Path.of(dir.toFile().getAbsolutePath(), chunkNumber + ".part");
                chunk.transferTo(path);

                // 更新分片信息
                if (!chunkInfo.getChunkPaths().contains(chunkNumber)) {
                    chunkInfo.getChunkPaths().add(chunkNumber);
                    Files.writeString(file.toPath(), JSON.toJSONString(chunkInfo));
                }
            } catch (IOException e) {
                return StdResult.fail();
            }
        }

        return StdResult.success();
    }

    @PostMapping("/chunk/merge")
    public StdResult merge(@RequestParam("fileName") String fileName, @RequestParam("fileHash") String hash,
                           @RequestParam("chunkSize") Integer chunkSize) {
        Path dir = Path.of(UPLOAD_PATH, "upload", "chunk", hash);
        if (!dir.toFile().exists())
            return StdResult.fail();
        File file = Path.of(dir.toFile().getAbsolutePath(), "chunkInfo.json").toFile();
        try {
            ChunkInfo chunkInfo = JSON.parseObject(Files.readString(file.toPath()), ChunkInfo.class);
            if (!Objects.equals(chunkInfo.getChunkSize(), chunkSize) || !Objects.equals(chunkInfo.getChunkPaths().size(), chunkSize))
                return StdResult.fail();
            // 合并所有分片
            Path targetPath = Path.of(dir.toFile().getAbsolutePath(), UUID.randomUUID() + "-" + fileName);
            for (int i = 0; i < chunkSize; i++) {
                byte[] fileContent = Files.readAllBytes(Path.of(dir.toFile().getAbsolutePath(), i + ".part"));
                // 追加写入目标文件
                Files.write(targetPath, fileContent, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
            // 删除所有分片
            for (Integer chunkPath : chunkInfo.getChunkPaths()) {
                try {
                    Files.delete(Path.of(dir.toFile().getAbsolutePath(), chunkPath + ".part"));
                } catch (IOException ignored) {
                }
            }
        } catch (IOException e) {
            return StdResult.fail();
        }

        return StdResult.success();
    }

    private void saveFile(MultipartFile file, String sonDir, String fileName) throws Exception {
        Path uploadSite = Path.of(UPLOAD_PATH, "upload", sonDir, fileName);
        if (!uploadSite.toFile().getParentFile().exists())
            uploadSite.toFile().getParentFile().mkdirs();
        file.transferTo(uploadSite);
    }
}
