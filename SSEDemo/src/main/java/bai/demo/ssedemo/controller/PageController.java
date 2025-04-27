package bai.demo.ssedemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-27 13:37
 * @description
 */

@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/sse")
    public String sse() {
        return "sse";
    }

}
