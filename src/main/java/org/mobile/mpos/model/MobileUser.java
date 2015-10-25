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
public class MobileUser extends Model<MobileUser>{

    //查询使用
    public static MobileUser dao = new MobileUser();

    public MobileUser findByMobileNo(String mobileNo){
        return  findFirst("select * from mpos_user where mobile=?", mobileNo);
    }

    public boolean insertMobileUser(String mobileNo,String password){
        MobileUser user = new MobileUser();
        user.set("mobile", mobileNo);
        user.set("password",password);
        return user.save();
    }
}
