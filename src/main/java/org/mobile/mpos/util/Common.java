/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.util
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午6:22
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.jpos.iso.ISOUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.util
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午6:22
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class Common {

    /**
     * 是否为手机号
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile){
        if(StringUtils.isBlank(mobile)){
            return false;
        }
        return mobile.matches("^1[34578][0-9]{9}$");
    }

    /**
     * 密码合法性校验
     * @param password
     * @return
     */
    public static boolean isPassword(String password){
        if(StringUtils.isBlank(password)){
            return false;
        }
        if(password.length() < 6){
            return false;
        }
        return true;
    }

    /**
     * shar加密
     * @param source
     * @return
     */
    public static String encryptSHA(String source){
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            sha.update(source.getBytes(Consts.UTF_8));
            return ISOUtil.hexString(sha.digest());
        }catch (NoSuchAlgorithmException e){
            return null;
        }
    }
}
