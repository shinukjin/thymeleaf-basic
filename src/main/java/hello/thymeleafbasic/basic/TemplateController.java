package hello.thymeleafbasic.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {

    @GetMapping("/fragment")
    public String template(Model model){
        model.addAttribute("data2", "data2");
        model.addAttribute("data1", "data1");
        return "template/fragment/fragmentMain";
    }

    @GetMapping("/layout")
    public String layout(){
        return "template/layout/layoutMain";
    }
}
