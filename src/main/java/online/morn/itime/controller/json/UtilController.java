package online.morn.itime.controller.json;

import com.alibaba.fastjson.JSONObject;
import online.morn.itime.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/util")
@RestController
public class UtilController {
    private Logger logger = Logger.getLogger(UtilController.class);

    /**
     * 查询当前时间
     * @auther Horner 2017/12/20 22:47
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/findCurrentTime.json", method = {RequestMethod.GET, RequestMethod.POST})
    public String findCurrentTime(ModelMap modelMap) {
        try{
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("nowDate", DateUtil.getDate());
            modelMap.put("data",dataMap);
            modelMap.put("success",true);
        } catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(modelMap);
    }
}
