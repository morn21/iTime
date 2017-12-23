package online.morn.itime.controller.json;

import com.alibaba.fastjson.JSONObject;
import online.morn.itime.DO.UserDO;
import online.morn.itime.service.UserService;
import online.morn.itime.util.DateUtil;
import online.morn.itime.util.MyException;
import online.morn.itime.util.SessionKey;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/user")
@RestController
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 查询当前用户信息
     * @auther Horner 2017/12/13 22:54
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "/findCurrentUser.json", method = {RequestMethod.GET, RequestMethod.POST})
    public String findCurrentUser(ModelMap modelMap, HttpServletRequest request) {
        try{
            /**Session取值*/
            UserDO userDO = (UserDO)request.getSession().getAttribute(SessionKey.USER);//获得用户实例
            if(userDO == null){
                throw new MyException("用户未登录");
            }
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("user",userDO);

            modelMap.put("data",dataMap);
            modelMap.put("success",true);
        } catch (MyException e) {
            modelMap.put("success",false);
            modelMap.put("msg",e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(modelMap);
    }

    /**
     * 登录用户
     * @auther Horner 2017/12/13 23:28
     * @param modelMap
     * @param request
     * @param response
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "/loginUser.json", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginUser(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, String name, String password) {
        try{
            if(StringUtils.isBlank(name) || StringUtils.isBlank(password)){
                throw new MyException("用户名密码不能为空");
            }
            UserDO userDO = userService.login(name,password);
            if(userDO == null){
                throw new MyException("用户名或密码错误");
            }
            request.getSession().setAttribute(SessionKey.USER, userDO);//设置用户实例
            /**设置Cookie*/
            Cookie cookie = new Cookie(SessionKey.COOKIE_USER_ID,userDO.getId());
            cookie.setMaxAge(315360000);//以秒为单位 60*60*24*365=10年
            cookie.setPath("/");
            response.addCookie(cookie);

            modelMap.put("success",true);
        } catch (MyException e) {
            modelMap.put("success",false);
            modelMap.put("msg",e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(modelMap);
    }

    /**
     * 退出用户
     * @auther Horner 2017/12/13 23:30
     * @param modelMap
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logoutUser.json", method = {RequestMethod.GET, RequestMethod.POST})
    public String logoutUser(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        try{
            request.getSession().removeAttribute(SessionKey.USER);//清除用户Session
            /**设置Cookie*/
            Cookie cookie = new Cookie(SessionKey.COOKIE_USER_ID, null);
            cookie.setMaxAge(0);//以秒为单位 设置为0 清除Cookie
            cookie.setPath("/");
            response.addCookie(cookie);
            modelMap.put("success",true);
        } catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(modelMap);
    }

    /**
     * 开始计时
     * @auther Horner 2017/12/16 16:40
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "/startTiming.json", method = {RequestMethod.GET, RequestMethod.POST})
    public String startTiming(ModelMap modelMap, HttpServletRequest request) {
        try{
            /**Session取值*/
            UserDO userDO = (UserDO)request.getSession().getAttribute(SessionKey.USER);//获得用户实例
            if(userDO == null){
                throw new MyException("用户未登录");
            }
            userDO.setIsIng(1);
            userDO.setIngStartTime(DateUtil.getDate());
            userService.changeById(userDO);//修改用户信息 根据ID
            request.getSession().setAttribute(SessionKey.USER, userDO);//设置用户实例
            modelMap.put("success",true);
        } catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(modelMap);
    }
}
