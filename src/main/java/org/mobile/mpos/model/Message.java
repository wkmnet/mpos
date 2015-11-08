/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.model
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-11-7
 * Time : 下午12:04
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.model
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-11-7
 * Time : 下午12:04
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class Message extends Model<Message>{

    //日志系统
    protected static Logger log = LoggerFactory.getLogger(Message.class);

    //查询使用
    public static Message me = new Message();

    public static boolean saveMessage(String cellphone,String content){
        Message message = new Message();
        message.set("cellphone",cellphone);
        message.set("content",content);
        log.info("save message:" + message);
        return  message.save();
    }

    public static boolean failSendMessage(String id){
        Message update = me.findById(id);
        //短信发送失败
        update.set("status", 2);
        log.info("update message:" + update);
        return update.update();
    }

    public static Message findLastMessageByCellphone(String cellphone){
        Message m = me.findFirst("select * from mpos_message where cellphone=? order by id desc",cellphone);
        log.info("find message:" + m);
        return m;
    }

    public static boolean successSendMessage(String id){
        Message update = me.findById(id);
        //短信发送成功
        update.set("status",1);
        log.info("update message:" + update);
        return update.update();
    }
}
