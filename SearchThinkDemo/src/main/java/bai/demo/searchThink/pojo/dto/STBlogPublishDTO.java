package bai.demo.searchThink.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-18 12:35
 * @description 博文发布请求参数
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class STBlogPublishDTO {
    @NotBlank(message = "标题不能为空")
    @Length(max = 100, message = "标题长度不能超过100个字符")
    private String title;
    @NotBlank(message = "内容不能为空")
    @Length(max = 10000, message = "内容长度不能超过10000个字符")
    private String content;
}
