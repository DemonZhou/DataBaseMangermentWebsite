package Controller;
import Controller.DAO.AdminDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class LoginController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value="inputAccount") String inputAccount,
                                    @RequestParam(value="inputPassword") String inputpassword) {
        AdminDAO adao = new AdminDAO();
        try {
            boolean bool = adao.verfiy(inputAccount, inputpassword);
            if(bool)
                return new ModelAndView("redirect:/singleselect");
            else
                return new ModelAndView("error");
        }catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
