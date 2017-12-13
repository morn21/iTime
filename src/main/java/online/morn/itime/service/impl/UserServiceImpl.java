package online.morn.itime.service.impl;

import online.morn.itime.DO.UserDO;
import online.morn.itime.mapper.UserMapper;
import online.morn.itime.service.UserService;
import online.morn.itime.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO login(String name, String password) throws MyException {
        return userMapper.selectUserByNameAndPassword(name, password);
    }

    @Override
    public UserDO findUserById(String id) throws MyException {
        return userMapper.selectUserById(id);
    }
}
