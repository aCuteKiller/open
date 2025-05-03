package bai.demo.fileuploaddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-05-02 10:59
 * @description
 */
@Controller
@RequestMapping("/page")
public class PageController {
    @GetMapping("/frontend")
    public String frontend() {
        return "frontend";
    }
}
