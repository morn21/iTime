package online.morn.itime.service;


import online.morn.itime.DO.UserDO;
import online.morn.itime.util.MyException;
import org.apache.ibatis.annotations.Param;

/**
 * 用户服务
 * @auther Horner 2017/12/11 23:08
 */
public interface UserService {

    /**
     * 修改用户 根据用户ID
     * @auther Horner 2017/12/16 16:34
     * @param userDO
     * @return
     */
    public Integer changeById(UserDO userDO) throws MyException;

    /**
     * 登录
     * @auther Horner 2017/11/26 15:25
     * @return
     * @throws MyException
     */
    public UserDO login(String name, String password) throws MyException;

    /**
     * 查询用户根据ID
     * @auther Horner 2017/12/13 22:44
     * @param id
     * @return
     */
    public UserDO findUserById(String id) throws MyException;
}
