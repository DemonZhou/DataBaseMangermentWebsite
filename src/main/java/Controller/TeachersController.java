package Controller;

import Controller.DAO.StudentsDAO;
import Controller.DAO.TeachersDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class TeachersController {
    @RequestMapping(value = "/Teachers/delete",method = RequestMethod.POST)
    public String Delete(@RequestParam(value = "table") String table,
                                 @RequestParam(value = "Tid") String Tid){
        String s = "true";
        TeachersDAO tdao = new TeachersDAO();
        try {
            if(!tdao.delete(Tid))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }

    @RequestMapping(value = "/Teachers/updateoradd",method = RequestMethod.GET)
    public ModelAndView EditPage(HttpSession session){
        TeachersDAO tdao = new TeachersDAO();
        try {
            HashMap map = tdao.selectAll();
            map.get("data");
            session.setAttribute("data", map.get("data"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("updateTeachers");
    }

    @RequestMapping(value = "/Teachers/updateoradd",method = RequestMethod.POST)
    @ResponseBody
    public String Update(
            @RequestParam(value = "table") String table,
            @RequestParam(value = "Tid") String Tid,
            @RequestParam(value = "Tname") String Tname,
            @RequestParam(value = "Sex") String Sex,
            @RequestParam(value = "Did") String Did
    ){
        String s = "true";
        try{
            //更新Students
            TeachersDAO tdao = new TeachersDAO();
            if(!tdao.updateOradd(Tid, Tname, Sex,Did))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }
}
