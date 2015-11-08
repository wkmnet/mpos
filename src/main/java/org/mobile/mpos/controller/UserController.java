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

import com.jfinal.aop.Clear;
import com.jfinal.aop.Duang;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.mobile.mpos.interceptor.AuthInterceptor;
import org.mobile.mpos.service.MobileUserService;
import org.mobile.mpos.util.Common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void listUser(){
        String page = getPara("page");
        if(StringUtils.isBlank(page)){
            renderJson(fail("PAGE_EMPTY", "页码为空"));
            return;
        }
        String count = getPara("size");
        if(StringUtils.isBlank(count)){
            renderJson(fail("SIZE_EMPTY", "每页条数为空"));
            return;
        }

        MobileUserService mobileUserService = Duang.duang(MobileUserService.class);
        long all = mobileUserService.countUser();
        int offset = (NumberUtils.toInt(page,1) - 1) * NumberUtils.toInt(count,5);
        int size = NumberUtils.toInt(count,5);
        long pageSize = 0;
        if(all % size == 0){
            pageSize = all / size;
        } else {
            pageSize = all / size + 1;
        }
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("pageSize",pageSize);
        result.put("page",NumberUtils.toInt(page,1));
        List<Map<String,String>> data = mobileUserService.pageAllUsers(offset, size);
        result.put("size", NumberUtils.toInt(count,5));
        result.put("factSize", data.size());
        result.put("pageData",data);
        renderJson(success(result));
    }

    public void deleteUser(){
        String id = getPara("id");
        if(StringUtils.isBlank(id)){
            renderJson(fail("ID_EMPTY", "ID为空"));
            return;
        }
        MobileUserService mobileUserService = Duang.duang(MobileUserService.class);
        if(mobileUserService.deleteUser(id)){
            renderJson(success());
        } else {
            renderJson(fail("DELETE_USER_FAIL", "删除用户数据失败"));
        }
    }

    @Clear(AuthInterceptor.class)
    public void login(){
        String userName = getPara("username");

        if(StringUtils.isBlank(userName)){
            renderJson(fail("USERNAME_EMPTY", "用户名为空"));
            return;
        }

        String password = getPara("password");
        if(StringUtils.isBlank(password)){
            renderJson(fail("PASSWORD_EMPTY", "密码为空"));
            return;
        }

        MobileUserService mobileUserService = Duang.duang(MobileUserService.class);
        if(!mobileUserService.existUser(userName)){
            renderJson(fail("USER_NOT_EXIST", "用户不存在"));
            return;
        }

        if(!mobileUserService.isSamePassword(password,userName)){
            renderJson(fail("PASSWORD_ERROR", "密码错误"));
            return;
        }

        getRequest().getSession().setAttribute("user",mobileUserService.findUserByEmail(userName));

        renderJson(success());
    }
}
