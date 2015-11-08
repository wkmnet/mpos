/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-11-7
 * Time : 上午11:47
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.controller;

import com.jfinal.aop.Duang;
import org.apache.commons.lang3.StringUtils;
import org.mobile.mpos.service.MessageService;
import org.mobile.mpos.util.Common;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.controller
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-11-7
 * Time : 上午11:47
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class MessageController extends MposController{

    public void sendMessage(){
        String cellphnoe = getPara("cellphone");
        if(StringUtils.isBlank(cellphnoe)){
            renderJson(fail("CELLPHONE_NO_EMPTY", "手机号不能为空"));
            return;
        }
        if(Common.isMobile(cellphnoe)){
            renderJson(fail("CELLPHONE_ILLEGAL", "手机号非法"));
            return;
        }
        String content = getPara("content");
        if(StringUtils.isBlank(content)){
            renderJson(fail("CONTENT_NO_EMPTY", "短信内容不能为空"));
            return;
        }
        MessageService messageService = Duang.duang(MessageService.class);

        if(messageService.sendMessage(cellphnoe,content)){
            renderJson(success());
            return;
        }
        renderJson(fail("MESSAGE_SEND_FAIL","短信发送失败"));
    }

    public void sendMessages(){
        renderJson(fail("","暂不支持"));
    }

}
