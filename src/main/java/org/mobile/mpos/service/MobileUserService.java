/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.service
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午5:42
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.service;

import com.jfinal.aop.Duang;
import org.mobile.mpos.model.ManagerUser;
import org.mobile.mpos.model.UserSession;
import org.mobile.mpos.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.service
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午5:42
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class MobileUserService {

    //日志
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 按照手机号寻找用户
     * @param offset
     * @param size
     * @return
     */
    public List<Map<String,String>> pageAllUsers(int offset,int size){
        log.info("page user:" + offset + "," + size);
        List<Map<String,String>> result = new ArrayList<Map<String, String>>();
        List<ManagerUser> users = ManagerUser.pageUser(offset, size);
        for(ManagerUser u:users){
            Map<String,String> map = new HashMap<String, String>();
            map.put("username",u.getStr("username"));
            map.put("email",u.getStr("email"));
            map.put("password",u.getStr("password"));
            map.put("id",u.getInt("id").toString());
            result.add(map);
        }
        log.info("find users result:" + result);
        return result;
    }

    public boolean deleteUser(String id){
        return ManagerUser.deleteUserById(id);
    }

    /**
     *
     * @return
     */
    public long countUser(){
        long count = ManagerUser.countUser();
        log.info("count user:" + count);
        return count;
    }

    /**
     * 是否存在指定用户
     * @param email
     * @return
     */
    public boolean existUser(String email){
        log.info("find user by email:" + email);
        ManagerUser mu = ManagerUser.findUserByEmail(email);
        log.info("exist user :" + mu);
        return  mu != null && email.equals(mu.getStr("email"));
    }

    /**
     * 密码是否一致
     * @param password
     * @param email
     * @return
     */
    public boolean isSamePassword(String password,String email){
        log.info("find user by email:" + email);
        ManagerUser mu = ManagerUser.findUserByEmail(email);
        log.info("exist user :" + mu);
        return Common.encryptSHA(password).equals(mu.getStr("password"));
    }

    /**
     * 寻找用户
     * @param email
     * @return
     */
    public UserSession findUserByEmail(String email){
        log.info("find user by email:" + email);
        ManagerUser mu = ManagerUser.findUserByEmail(email);
        log.info("exist user :" + mu);
        UserSession session = new UserSession();
        session.setEmail(mu.getStr("email"));
        session.setUsername(mu.getStr("username"));
        session.setExpire(System.currentTimeMillis() + 1000 * 60 * 5);
        return session;
    }

    /**
     * 新增用户
     * @param username 用户名称
     * @param email 邮箱地址
     * @param password 用户密码
     * @return
     */
    public boolean addUser(String username,String email,String password){
        log.info("new add user:" + username);
        ManagerUser user = Duang.duang(ManagerUser.class);
        return user.insertUser(username, email, Common.encryptSHA(password));
    }
}
