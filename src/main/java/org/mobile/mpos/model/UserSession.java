/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.model
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-11-8
 * Time : 下午8:25
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.model;


/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.model
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-11-8
 * Time : 下午8:25
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class UserSession {
    //用户名
    private String username;

    private String email;

    private long expire;

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
