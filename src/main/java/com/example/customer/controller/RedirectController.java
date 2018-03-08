package com.example.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 测试：重定向
 * @author 霍俊
 */
@Controller
public class RedirectController {

    @RequestMapping("/helloRedirect")
    public String helloRedirect(@RequestParam(value="name", required=false ) String name, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("name", name);
        return "redirect:setName";
    }

}
