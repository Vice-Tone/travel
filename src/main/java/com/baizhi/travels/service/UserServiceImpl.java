package com.baizhi.travels.service;

import com.baizhi.travels.dao.UserDAO;
import com.baizhi.travels.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User userDB = userDAO.findByUsername(user.getUsername());
        if(userDB!=null){
            if(userDB.getPassword().equals(user.getPassword())){
                return userDB;
            }
            throw new RuntimeException("密码输入错误~~~");
        }else{
            throw new RuntimeException("用户名输入错误!!!");
        }
    }

    /**
     * 注册
     * @param user
     */
    @Override
    public void register(User user) {
        //注册时首先根据用户名查询是否存在这个用户，如果不存在，则可以注册
        //如果已经存在，则抛出异常
        if (userDAO.findByUsername(user.getUsername()) == null) {
            userDAO.save(user);
        }else{
            throw new RuntimeException("用户名已经存在~~~~");
        }
    }
}
