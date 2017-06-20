package Controller;

import Controller.DAO.DepartmentsDAO;
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
public class DepartmentsConrtoller {
    @RequestMapping(value = "/Departments/delete",method = RequestMethod.POST)
    public String Delete(@RequestParam(value = "table") String table,
                                 @RequestParam(value = "Did") String Did){
        String s = "true";
        DepartmentsDAO ddao = new DepartmentsDAO();
        try {
            if(!ddao.delete(Did))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }

    @RequestMapping(value = "/Departments/updateoradd",method = RequestMethod.GET)
    public ModelAndView EditPage(HttpSession session){
        DepartmentsDAO ddao = new DepartmentsDAO();
        try {
            HashMap map = ddao.selectAll();
            map.get("data");
            session.setAttribute("data", map.get("data"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("updateDepartments");
    }

    @RequestMapping(value = "/Departments/updateoradd",method = RequestMethod.POST)
    @ResponseBody
    public String Update(
            @RequestParam(value = "table") String table,
            @RequestParam(value = "Did") String Did,
            @RequestParam(value = "Dname") String Dname
    ){
        String s = "true";
        try{
            //更新Departments
            DepartmentsDAO ddao = new DepartmentsDAO();
            if(!ddao.updateOradd(Did,Dname))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }
}
