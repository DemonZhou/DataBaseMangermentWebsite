package Controller;

import Controller.DAO.DepartmentsDAO;
import Controller.DAO.SCDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class SCController {
    @RequestMapping(value = "/SC/delete",method = RequestMethod.POST)
    public String Delete(@RequestParam(value = "table") String table,
                                 @RequestParam(value = "id") Integer id){
        String s = "true";
        SCDAO scdao = new SCDAO();
        try {
            if(!scdao.delete(id))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }

    @RequestMapping(value = "/SC/updateoradd",method = RequestMethod.GET)
    public ModelAndView EditPage(HttpSession session){
        SCDAO scdao = new SCDAO();
        try {
            HashMap map = scdao.selectAll();
            map.get("data");
            session.setAttribute("data", map.get("data"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("updateSC");
    }

    @RequestMapping(value = "/SC/updateoradd",method = RequestMethod.POST)
    @ResponseBody
    public String Update(
            @RequestParam(value = "table") String table,
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "Sid") String Sid,
            @RequestParam(value = "Cid") String Cid
    ){
        String s = "true";
        try{
            //更新SC
            SCDAO scdao = new SCDAO();
            if(!scdao.updateOradd(id,Sid,Cid))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }
}
