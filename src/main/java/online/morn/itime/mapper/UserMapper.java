package online.morn.itime.mapper;

import online.morn.itime.DO.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表
 * @auther Horner 2017/12/11 23:30
 */
public interface UserMapper {

    /**
     * 修改用户 根据用户ID
     * @auther Horner 2017/12/16 16:35
     * @param userDO
     * @return
     */
    public Integer updateById(@Param("userDO") UserDO userDO);

    /**
     * 查询用户 根据用户名和密码
     * @auther Horner 2017/12/11 23:31
     * @param name
     * @param password
     * @return
     */
    public UserDO selectUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    /**
     * 查询用户根据ID
     * @auther Horner 2017/12/13 22:45
     * @param id
     * @return
     */
    public UserDO selectUserById(@Param("id") String id);
}
