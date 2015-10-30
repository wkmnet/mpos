/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.config
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午5:14
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.config;

import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import org.apache.http.Consts;
import org.mobile.mpos.controller.HomeController;
import org.mobile.mpos.controller.MobileUserController;
import org.mobile.mpos.interceptor.LoggerInterceptor;
import org.mobile.mpos.model.MobileUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.config
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-25
 * Time : 下午5:14
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class MposConfig extends JFinalConfig{

    //系统配置文件
    private Prop prop = PropKit.use("system.properties");
    //日志输出
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void configConstant(Constants me) {
        me.setDevMode(prop.getBoolean("system.dev.model"));
        me.setEncoding(Consts.UTF_8.name());
        me.setViewType(ViewType.JSP);

        log.info("开发模式:" + me.getDevMode());
        log.info("编码:" + me.getEncoding());

        me.setError404View("/html/home.html");
        me.setError500View("/html/home.html");
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/" , HomeController.class);
        me.add("/mobileUser", MobileUserController.class);
    }

    @Override
    public void configPlugin(Plugins me) {

        C3p0Plugin mysqlDatasource = new C3p0Plugin(loadPropertyFile("c3p0.properties"));
        me.add(mysqlDatasource);
        ActiveRecordPlugin mysqlPlugin = new ActiveRecordPlugin(mysqlDatasource);
        me.add(mysqlPlugin);
        mysqlPlugin.setDevMode(prop.getBoolean("system.dev.model"));
        mysqlPlugin.setDialect(new MysqlDialect());
        mysqlPlugin.setTransactionLevel(8);
        mysqlPlugin.setShowSql(true);
        mysqlPlugin.addMapping("mpos_user", "id", MobileUser.class);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new LoggerInterceptor());
    }

    @Override
    public void configHandler(Handlers me) {

    }
}
