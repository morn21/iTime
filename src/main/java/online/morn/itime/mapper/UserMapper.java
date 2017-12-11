package online.morn.itime.mapper;

import online.morn.itime.DO.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表
 * @auther Horner 2017/12/11 23:30
 */
public interface UserMapper {

    /**
     * 查询用户 根据用户名和密码
     * @auther Horner 2017/12/11 23:31
     * @param name
     * @param password
     * @return
     */
    public UserDO selectUserByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
