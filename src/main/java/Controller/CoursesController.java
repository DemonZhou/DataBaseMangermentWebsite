package Controller;

import Controller.DAO.CoursesDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.HashMap;

@Controller
public class CoursesController {
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("update");
    }

    @RequestMapping(value = "/modifyselect",method = RequestMethod.POST)
    public ModelAndView directPage(@RequestParam(value = "table") String table) {
        if (table.equals("课程")) {
            return new ModelAndView("redirect:/Courses/updateoradd");
        } else if (table.equals("学生")) {
            return new ModelAndView("redirect:/Students/updateoradd");
        } else if (table.equals("教师")) {
            return new ModelAndView("redirect:/Teachers/updateoradd");
        } else if (table.equals("选课表")) {
            return new ModelAndView("redirect:/SC/updateoradd");
        } else if (table.equals("授课表")) {
            return new ModelAndView("redirect:/CT/updateoradd");
        } else if (table.equals("院系表")) {
            return new ModelAndView("redirect:/Departments/updateoradd");
        } else if (table.equals("专业表")){
            return new ModelAndView("redirect:/Majors/updateoradd");
        }else
            return null;
    }
    @RequestMapping(value = "/Courses/delete",method = RequestMethod.POST)
    public String Delete(@RequestParam(value = "table") String table,
                                @RequestParam(value = "Cid") String Cid){
        String s = "true";
        CoursesDAO cdao = new CoursesDAO();
        try {
            if(!cdao.delete(Cid))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }

    @RequestMapping(value = "/Courses/updateoradd",method = RequestMethod.GET)
    public ModelAndView EditPage(HttpSession session){
        CoursesDAO cdao = new CoursesDAO();
        try {
            HashMap map = cdao.selectAll();
            map.get("data");
            session.setAttribute("data", map.get("data"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("updateCourses");
    }

    @RequestMapping(value = "/Courses/updateoradd",method = RequestMethod.POST)
    @ResponseBody
    public String Update(
            @RequestParam(value = "table") String table,
            @RequestParam(value = "Cid") String Cid,
            @RequestParam(value = "Cname") String Cname,
            @RequestParam(value = "Credit") Double Credit,
            @RequestParam(value = "Did") String Did,
            @RequestParam(value = "Cnum") Integer Cnum,
            @RequestParam(value = "Startweek") Integer Startweek,
            @RequestParam(value = "Endweek") Integer Endweek,
            @RequestParam(value = "Info") String Info
    ){
        String s = "true";
        try{
            //更新Course
            CoursesDAO cdao = new CoursesDAO();
            if(!cdao.updateOradd(Cid, Cname, Credit, Cnum,Did, Startweek, Endweek, Info))
                s = "false";
        }catch (Exception e){
            e.getMessage();
            s = "false";
            return s;
        }
        return s;
    }
}
