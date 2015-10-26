/**
 * Apache LICENSE-2.0
 * Project name : mpos
 * Package name : org.mobile.mpos.util
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-26
 * Time : 下午9:48
 * 版权所有,侵权必究！
 */
package org.mobile.mpos.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * Create with IntelliJ IDEA
 * Project name : mpos
 * Package name : org.mobile.mpos.util
 * Author : Wukunmeng
 * User : wkm
 * Date : 15-10-26
 * Time : 下午9:48
 * 版权所有,侵权必究！
 * To change this template use File | Settings | File and Code Templates.
 */
public class HttpClientHelper {

    //日志
    private static Logger log = LoggerFactory.getLogger(HttpClientHelper.class);

    /**
     * get request
     * @param uri
     * @return
     */
    public static String get(String uri){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(uri);
            log.info("Executing request " + httpget.getRequestLine());
            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = httpClient.execute(httpget, responseHandler);
            log.info(" response " + responseBody);
            return responseBody;
        } catch (ClientProtocolException e) {
            log.error("ClientProtocolException " + e.getMessage());
        } catch (IOException e) {
            log.error("IOException " + e.getMessage());
        } finally {
            close(httpClient);
        }
        return null;
    }

    private static void close(CloseableHttpClient httpClient){
        try {
            if(httpClient != null){
                httpClient.close();
            }
        } catch (IOException e){
            log.error("close httpClient IOException " + e.getMessage());
        }
    }

}
