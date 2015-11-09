/**
 * Apache LICENSE-2.0
 * Project name : mtool
 * Package name : org.wkm.mtool.interceptor
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-12
 * Time : 下午9:55
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.render.JsonRender;
import com.jfinal.render.JspRender;
import com.jfinal.render.Render;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Project name : mtool
 * Package name : org.wkm.mtool.interceptor
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-12
 * Time : 下午9:55
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class LoggerInterceptor implements Interceptor{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 输出请求和响应参数
     * @param inv
     */
    public void intercept(Invocation inv) {
        logger.info("controllerKey:" + inv.getControllerKey());
        logger.info("methodName:" + inv.getMethodName());
        Controller c = inv.getController();
        Map<String, String[]> map =  c.getParaMap();
        if(map != null){
            StringBuilder para = new StringBuilder();
            for(Map.Entry<String,String[]> entry : map.entrySet()){
                String[] values = entry.getValue();
                StringBuffer tempValue = new StringBuffer();
                if(values != null){
                    tempValue.append("[");
                    for(String v:values){
                        tempValue.append(v);
                        tempValue.append(",");
                    }
                    tempValue.deleteCharAt(tempValue.length() - 1);
                    tempValue.append("]");
                }
                para.append(entry.getKey() + "=" + tempValue.toString());
                para.append(SystemUtils.LINE_SEPARATOR);
            }
            printRequest(para);
        }
        String para = c.getPara();
        if(!StringUtils.isBlank(para)){
            printRequest(para.split("-"));
        }
        inv.invoke();
        Object value = inv.getReturnValue();
        if(value == null){
            logger.info("return:");
        } else if (value instanceof String){
            logger.info("return:" + value);
        } else {
            logger.info("return:" + value.getClass() + ";" + value);
        }
        printHeaders(c.getResponse());
        printReturnValue(c.getRender());
    }

    /**
     * 打印响应头信息
     * @param response
     */
    private  void printHeaders(HttpServletResponse response){
        if(response != null){
            StringBuilder head = new StringBuilder();
            head.append(SystemUtils.LINE_SEPARATOR);
            head.append("heads:");
            head.append(SystemUtils.LINE_SEPARATOR);
            JSONObject h = new JSONObject();
            for(String headerName:response.getHeaderNames()){
                h.put(headerName,response.getHeader(headerName));
            }
            head.append(h.toString(4));
            logger.info(head.toString());
        }
    }

    /**
     * 打印请求信息
     * @param value
     */
    private void printRequest(Object value){
        logger.info(SystemUtils.LINE_SEPARATOR + "request:" + SystemUtils.LINE_SEPARATOR + value);
    }

    /**
     * 打印响应信息
     * @param value
     */
    private void printResponse(Object value){
        logger.info(SystemUtils.LINE_SEPARATOR + "response:" + SystemUtils.LINE_SEPARATOR + value);
    }

    /**
     * 打印响应结果
     * @param render
     */
    private void printReturnValue(Render render){
        if(render == null){
            logger.info("render:");
            return;
        }

        logger.info("render:" + render.getClass());

        if(render instanceof JsonRender){
            String value = ((JsonRender) render).getJsonText();
            if(value.startsWith("[")){
                printResponse(JSONArray.fromObject(value).toString(4));
            }else if(value.startsWith("{")){
                printResponse(JSONObject.fromObject(value).toString(4));
            }else {
                printResponse(value);
            }
        }
        if(render instanceof JspRender){
            printResponse(render.getView());
        }

    }
}
