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
import org.mobile.mpos.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * @param mobileNo
     * @return
     */
//    public MobileUser findMobileUserByMobileNo(String mobileNo){
//        log.info("find user by MobileNo:" + mobileNo);
//        MobileUser user = mobileUser.findByMobileNo(mobileNo);
//        log.info("find user by MobileNo result:" + user);
//        return user;
//    }

    /**
     * 是否存在指定移动用户
     * @param mobileNo
     * @return
     */
//    public boolean existMobileUser(String mobileNo){
//        MobileUser user = findMobileUserByMobileNo(mobileNo);
//        return  user != null;
//    }

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
