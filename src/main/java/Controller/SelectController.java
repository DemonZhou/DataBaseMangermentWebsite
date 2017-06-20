package Controller;
import Controller.DAO.SelectDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class SelectController {


    @RequestMapping(value = "/singleselect",method  =RequestMethod.GET)
    public ModelAndView SingleselectPage(){
        ModelAndView mv = new ModelAndView("singleselect");
        return mv;
    }
    @RequestMapping(value = "/singleselect",method  =RequestMethod.POST)
    @ResponseBody
    public String SingleselectDeal(@RequestParam(value="tablename") String tablename,
                                         @RequestParam(value="data") String data,HttpSession session){
        String s = "true";
        SelectDAO sdao = new SelectDAO();
        try {
            HashMap map = sdao.select(tablename, data);
            session.setAttribute("namelist", map.get("namelist"));
            session.setAttribute("data", map.get("data"));
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }

    @RequestMapping(value = "/multiselect",method  =RequestMethod.GET)
    public ModelAndView MultiselectPage(){
        ModelAndView mv = new ModelAndView("multiselect");
        return mv;
    }

    @RequestMapping(value = "/multiselect",method  =RequestMethod.POST)
    @ResponseBody
    public String MultiselectDeal(@RequestParam(value="tablename") String tablename,
                                   @RequestParam(value="data") String data,HttpSession session){
        String s = "true";
        SelectDAO sdao = new SelectDAO();
        try {
            HashMap map = sdao.select(tablename, data);
            session.setAttribute("namelist", map.get("namelist"));
            session.setAttribute("data", map.get("data"));
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }

}
