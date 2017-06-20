package Controller;

import Controller.DAO.DAO;
import Controller.DAO.DepartmentsDAO;
import Controller.DAO.MajorsDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
@Controller
public class MajorsController extends DAO{
    @RequestMapping(value = "/Majors/delete",method = RequestMethod.POST)
    public String Delete(@RequestParam(value = "table") String table,
                                 @RequestParam(value = "Mid") String Mid){
        String s = "true";
        MajorsDAO mdao = new MajorsDAO();
        try {
            if(!mdao.delete(Mid))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }

    @RequestMapping(value = "/Majors/updateoradd",method = RequestMethod.GET)
    public ModelAndView EditPage(HttpSession session){
        MajorsDAO mdao = new MajorsDAO();
        try {
            HashMap map = mdao.selectAll();
            map.get("data");
            session.setAttribute("data", map.get("data"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("updateMajors");
    }

    @RequestMapping(value = "/Majors/updateoradd",method = RequestMethod.POST)
    @ResponseBody
    public String Update(
            @RequestParam(value = "table") String table,
            @RequestParam(value = "Mid") String Mid,
            @RequestParam(value = "Mname") String Mname,
            @RequestParam(value = "Did") String Did
    ){
        String s = "true";
        try{
            //更新Majors
            MajorsDAO mdao = new MajorsDAO();
            if(!mdao.updateOradd(Mid,Mname,Did))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }
}
