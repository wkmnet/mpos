/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午5:34
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.controller;

import com.jfinal.aop.Duang;
import org.apache.commons.lang3.StringUtils;
import org.mobile.mpos.service.MobileUserService;
import org.mobile.mpos.util.Common;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午5:34
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class UserController extends MposController{

    /**
     * 注册
     */
    public void register(){
        String username = getPara("username");
        if(StringUtils.isBlank(username)){
            renderJson(fail("USERNAME_NO_EMPTY", "用户名称为空"));
            return;
        }

        String email = getPara("email");
        if(StringUtils.isBlank(email)){
            renderJson(fail("EMAIL_NO_EMPTY", "邮箱为空"));
            return;
        }

        String password = getPara("password");
        if(StringUtils.isBlank(password)){
            renderJson(fail("PASSWORD_EMPTY", "密码为空"));
            return;
        }

        if(!Common.isPassword(password)){
            renderJson(fail("PASSWORD_ILLEGAL", "密码非法"));
            return;
        }

        MobileUserService mobileUserService = Duang.duang(MobileUserService.class);

        if(!mobileUserService.addUser(username,email,password)){
            renderJson(fail("MOBILE_USER_SAVE_ERROR", "移动用户保存出错"));
            return;
        }
        renderJson(success());
    }
}
