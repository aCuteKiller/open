package bai.demo.searchThink.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 12:32
 * @description 测试博客表 PO
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("st_blog")
public class STBLogPO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Integer views;
    private Integer likes;
    private Timestamp createdAt;
}
