/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.interceptor
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-11-8
 * Time : 下午8:15
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.interceptor;

import com.jfinal.aop.Duang;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import org.mobile.mpos.model.UserSession;
import org.mobile.mpos.service.MobileUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.interceptor
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-11-8
 * Time : 下午8:15
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class AuthInterceptor implements Interceptor {

    //日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void intercept(Invocation inv) {

        Controller controller = inv.getController();
        if(controller == null) {
            logger.info("do not know controller:" + controller);
            return;
        }

        HttpServletRequest request = controller.getRequest();
        logger.info("ger request:" + request);
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        logger.info("ger session:" + user);
        if(user == null || !(user instanceof UserSession)){
            controller.renderJsp("/html/login.html");
            return;
        }

        UserSession us = (UserSession) user;
        MobileUserService mus = Duang.duang(MobileUserService.class);
        logger.info("ger user:" + mus);
        if(!mus.existUser(us.getEmail())){
            controller.renderJsp("/html/login.html");
            return;
        }

        if(us.getExpire() <= System.currentTimeMillis()){
            controller.renderJsp("/html/login.html");
            return;
        }
        long newExpire = System.currentTimeMillis() +  1000 * 60 * 5;
        us.setExpire(newExpire);

        inv.invoke();
    }
}
