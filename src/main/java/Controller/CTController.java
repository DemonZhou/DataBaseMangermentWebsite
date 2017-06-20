package Controller;

import Controller.DAO.CTDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class CTController {
    @RequestMapping(value = "/CT/delete",method = RequestMethod.POST)
    public String Delete(@RequestParam(value = "table") String table,
                         @RequestParam(value = "id") Integer id){
        String s = "true";
        CTDAO ctdao = new CTDAO();
        try {
            if(!ctdao.delete(id))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }

    @RequestMapping(value = "/CT/updateoradd",method = RequestMethod.GET)
    public ModelAndView EditPage(HttpSession session){
        CTDAO ctdao = new CTDAO();
        try {
            HashMap map = ctdao.selectAll();
            map.get("data");
            session.setAttribute("data", map.get("data"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("updateCT");
    }

    @RequestMapping(value = "/CT/updateoradd",method = RequestMethod.POST)
    @ResponseBody
    public String Update(
            @RequestParam(value = "table") String table,
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "Cid") String Cid,
            @RequestParam(value = "Tid") String Tid
    ){
        String s = "true";
        try{
            //更新CT
            CTDAO ctdao = new CTDAO();
            if(!ctdao.updateOradd(id,Cid,Tid))
                s = "false";
        }catch (Exception e){
            e.printStackTrace();
            s = "false";
            return s;
        }
        return s;
    }
}
