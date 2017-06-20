package Controller;

import Controller.DAO.StudentsDAO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

@Controller
public class StudentsController {
    @RequestMapping(value = "/Students/delete",method = RequestMethod.POST)
    public String Delete(@RequestParam(value = "table") String table,
                                @RequestParam(value = "Sid") String Sid){
        String s = "true";
        StudentsDAO sdao = new StudentsDAO();
        try {
            if(!sdao.delete(Sid))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }

    @RequestMapping(value = "/Students/updateoradd",method = RequestMethod.GET)
    public ModelAndView EditPage(HttpSession session){
        StudentsDAO sdao = new StudentsDAO();
        try {
            HashMap map = sdao.selectAll();
            map.get("data");
            session.setAttribute("data", map.get("data"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("updateStudents");
    }

    @RequestMapping(value = "/Students/updateoradd",method = RequestMethod.POST)
    @ResponseBody
    public String Update(
            @RequestParam(value = "table") String table,
            @RequestParam(value = "Sid") String Sid,
            @RequestParam(value = "Sname") String Sname,
            @RequestParam(value = "Sex") String Sex,
            @RequestParam(value = "recruitdate")Integer recruitdate,
            @RequestParam(value = "Mid") String Mid
    ){
        System.out.println(recruitdate);
        String s = "true";
        try{
            //更新Students
            StudentsDAO sdao = new StudentsDAO();
            if(!sdao.updateOradd(Sid, Sname, Sex, recruitdate,Mid))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }
}
