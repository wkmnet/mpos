/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午5:32
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.controller;

import com.jfinal.core.Controller;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午5:32
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class MposController extends Controller{

    //日志
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    protected JSONObject success(Object data){
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("message","成功");
        if(data != null)
            result.put("data",data);
        return result;
    }

    protected JSONObject success(){
        return success(null);
    }

    protected JSONObject fail(String respCode,String message){
        JSONObject result = new JSONObject();
        result.put("success",false);
        result.put("respCode",respCode);
        result.put("message",message);
        return result;
    }

    public void index(){
        renderJsp("/html/home.html");
    }
}
