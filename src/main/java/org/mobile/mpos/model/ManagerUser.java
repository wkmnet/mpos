/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.model
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午5:38
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.model;

import com.jfinal.plugin.activerecord.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.model
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午5:38
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class ManagerUser extends Model<ManagerUser>{

    //日志系统
    protected static Logger log = LoggerFactory.getLogger(ManagerUser.class);

    //查询使用
    public static ManagerUser me = new ManagerUser();

    public static ManagerUser findByUserByName(String username){
        return me.findFirst("select * from mpos_manager_user where username=?", username);
    }

    /**
     * 增加用户
     * @param username
     * @param email
     * @param password
     * @return
     */
    public static boolean insertUser(String username,String email,String password){
        ManagerUser user = new ManagerUser();
        user.set("username", username);
        user.set("email", email);
        user.set("password",password);
        log.info("add user:" + user);
        return user.save();
    }
}
