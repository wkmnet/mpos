/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-30
 * Time : 下午6:57
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.controller;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-30
 * Time : 下午6:57
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class HomeController extends MposController {

    public void index(){
        renderJsp("/html/home.html");
    }
}
