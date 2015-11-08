/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.service
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-11-7
 * Time : 下午12:01
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.service;

import com.jfinal.aop.Duang;
import org.mobile.mpos.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.service
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-11-7
 * Time : 下午12:01
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class MessageService {
    //日志
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 单条发送短信接口
     * @param cellphone   手机号码
     * @param content     短信内容
     */
    public boolean sendMessage(String cellphone,String content){
        //初始化发送信息
        if(!Message.saveMessage(cellphone,content)){
            return false;
        }

        Message m = Message.findLastMessageByCellphone(cellphone);

        DefaultSendMessageService sendMessageService = Duang.duang(DefaultSendMessageService.class);

        if(sendMessageService.sendMessage(cellphone,content)){
            Message.successSendMessage(m.getStr("id"));
        } else {
            Message.failSendMessage(m.getStr("id"));
            return false;
        }
        return true;
    }

}
