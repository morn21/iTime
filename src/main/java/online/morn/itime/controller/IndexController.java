package online.morn.itime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面
 * @auther Horner 2017/11/26 0:22
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/","/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/login"})
    public String login() {
        return "login";
    }

}
