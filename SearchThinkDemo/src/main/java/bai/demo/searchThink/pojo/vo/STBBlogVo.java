package bai.demo.searchThink.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 12:37
 * @description 博文VO
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class STBBlogVo {

    private Integer id;
    private String title;
    private String content;
    private Integer views;
    private Integer likes;
    private Timestamp createdAt;

    private String formatCreateAt;

    public STBBlogVo setCreateAt(Timestamp createAt) {
        this.createdAt = createAt;
        this.formatCreateAt = createAt.toString();
        return this;
    }
}
