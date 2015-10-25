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
public class MobileUserController extends MposController{

    //移动用户服务
    private MobileUserService mobileUserService = Duang.duang(MobileUserService.class);

    public void register(){
        String mobileNo = getPara("mobile");
        if(StringUtils.isBlank(mobileNo)){
            renderJson(fail("MOBILE_NO_EMPTY", "手机号码为空"));
            return;
        }

        if(!Common.isMobile(mobileNo)){
            renderJson(fail("MOBILE_NO_ILLEGAL", "手机号码非法"));
            return;
        }

        if(mobileUserService.existMobileUser(mobileNo)){
            renderJson(fail("MOBILE_NO_EXIST", "手机号码已存在"));
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

        if(!mobileUserService.addMobileUser(mobileNo,password)){
            renderJson(fail("MOBILE_USER_SAVE_ERROR", "移动用户保存出错"));
            return;
        }
        renderJson(success());
    }
}
