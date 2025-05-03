package bai.demo.fileuploaddemo.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-05-02 13:53
 * @description
 */

public class ChunkInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String hash;
    private List<Integer> chunkPaths;
    private Integer chunkSize;

    public ChunkInfo() {
        chunkPaths = new ArrayList<>();
    }

    public ChunkInfo(String hash, Integer chunkSize) {
        chunkPaths = new ArrayList<>();
        this.hash = hash;
        this.chunkSize = chunkSize;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<Integer> getChunkPaths() {
        return chunkPaths;
    }

    public void setChunkPaths(List<Integer> chunkPaths) {
        this.chunkPaths = chunkPaths;
    }

    public Integer getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(Integer chunkSize) {
        this.chunkSize = chunkSize;
    }
}
