package Controller;

import Controller.DAO.CoursesDAO;
import Controller.DAO.SelectDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class ShowContent {
    @RequestMapping(value = "/showcontent")
    public ModelAndView GiveContent(HttpSession session){
        ModelAndView mv = new ModelAndView("showcontent");
        return mv;
    }
}
