package Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ModelAndView test(HttpSession session){
        ModelAndView mv = new ModelAndView("test");
        return mv;
    }
}
