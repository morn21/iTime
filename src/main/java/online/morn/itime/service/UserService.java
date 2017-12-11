package online.morn.itime.service;


import online.morn.itime.DO.UserDO;
import online.morn.itime.util.MyException;

/**
 * 用户服务
 * @auther Horner 2017/12/11 23:08
 */
public interface UserService {

    /**
     * 登录
     * @auther Horner 2017/11/26 15:25
     * @return
     * @throws MyException
     */
    public UserDO login(String name, String password) throws MyException;
}
