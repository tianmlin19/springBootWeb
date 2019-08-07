package com.tml.util;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tml.exception.BaseException;
import com.tml.exception.CommonExceptionEnums;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * <p>HttpClient工具类
 *
 * @author tianmlin19
 * @date 2019-02-26
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();

    public static void main(String[] args) {


        String url = "https://api.wanmen.org/4.0/me/daily-signin";
        String loginUrl = "https://api.wanmen.org/4.0/main/signin";
        String loginUrlGet = "https://api.wanmen.org/4.0/main/signin?account=13716416827&password=wangyun1990";
        List<BasicNameValuePair> listParam = new ArrayList<>();
        BasicNameValuePair pair1 = new BasicNameValuePair("account","13716416827");
        BasicNameValuePair pair2 = new BasicNameValuePair("password","wangyun1990");
        BasicNameValuePair pair3 = new BasicNameValuePair("code","");
        listParam.add(pair1);
        listParam.add(pair2);
        listParam.add(pair3);
        doGet(loginUrlGet);
    }

    /**
     * <p>使用post方式访问一个地址<p/>
     *
     * @param uri 访问地址
     * @param listParam 参数列表
     */
    public static String doPost(String uri, List<BasicNameValuePair> listParam) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(uri);
        CloseableHttpResponse response = null;
        try {
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(listParam, "UTF-8");
            httppost.setEntity(uefEntity);
            logger.info("executing request :{}", httppost.getURI());
            response = httpclient.execute(httppost);
            return dealResponse(response, uri);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            shutDownResoure(httpclient, response);
        }
        return null;

    }

    /**
     * <p>使用get方式访问一个地址
     */
    public static String doGet(String uri) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpGet get = new HttpGet(uri);
        try {
            response = httpClient.execute(get);
            return dealResponse(response, uri);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            shutDownResoure(httpClient, response);
        }
        return null;
    }


    /**
     * 通过httpclient访问一个需要登录的接口
     *
     * @param beforeLoginUri 登录前某一个get请求的uri，为了获取cookie
     * @param loginUri 登录的uri
     * @param loginPara 登录需要的参数（简单登录，有图片验证码短信验证码的接口不在此范围）
     * @param targetUri 需要获取资源的目标接口
     */
    public static void getAuthorizedResource(String beforeLoginUri, String loginUri, List<BasicNameValuePair> loginPara,
                    String targetUri) throws Exception {
        logger.info("getAuthorizedResource enter!");
        if (StringUtils.isAnyBlank(beforeLoginUri, loginUri, targetUri) || CollectionUtils.isEmpty(loginPara)) {
            throw new BaseException(CommonExceptionEnums.INVALID_PARAMETER);
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse res = null;
        try {
            // 全局请求设置
            RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
            // 创建cookie store的本地实例
            CookieStore cookieStore = new BasicCookieStore();
            // 创建HttpClient上下文
            HttpClientContext context = HttpClientContext.create();
            context.setCookieStore(cookieStore);
            // 创建一个HttpClient实例
            httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig)
                            .setDefaultCookieStore(cookieStore).build();
            //1访问主页获取一些必要的cookie信息
            HttpGet first = new HttpGet(beforeLoginUri);
            res = httpClient.execute(first, context);
            logger.info("登录之前抓取的cookie信息为");
            for (Cookie c : cookieStore.getCookies()) {
                logger.info("Cookie:{}--:{}", c.getName(), c.getValue());
            }
            String beforeLoginUriRes = dealResponse(res, beforeLoginUri);
            logger.info("访问：{}接口返回的结果为：{}", beforeLoginUri, beforeLoginUriRes);
            res.close();
            //2访问登录接口获取token信息
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(loginPara, Consts.UTF_8);
            entity.setContentType("application/x-www-form-urlencoded");
            // 创建一个post请求
            HttpPost post = new HttpPost(loginUri);
            // 注入post数据
            post.setEntity(entity);
            post.setConfig(globalConfig);
            res = httpClient.execute(post, context);
            String dealResponse = dealResponse(res, loginUri);
            logger.info("访问：{}接口返回的结果为：{}", loginUri, dealResponse);
            res.close();
            logger.info("登录成功后的cookie信息！");
            for (Cookie c : cookieStore.getCookies()) {
                logger.info("Cookie====>{}--{}", c.getName(), c.getValue());
            }
            //3访问目标接口
            HttpGet newGet = new HttpGet(targetUri);
            res = httpClient.execute(newGet, context);
            String targetRes = dealResponse(res, targetUri);
            logger.info("访问：{}接口返回的结果为：{}", targetUri, targetRes);
            res.close();
            //查询抽奖信息
            HttpPost newPost = new HttpPost(
                            "https://www.zyfax.cn/ActivityWeb/lotteryWebController/queryLotteryRules.json");
            List<BasicNameValuePair> lotteryPara = new ArrayList<>();
            BasicNameValuePair pair = new BasicNameValuePair("activityType", "1");
            lotteryPara.add(pair);
            UrlEncodedFormEntity entity_0 = new UrlEncodedFormEntity(lotteryPara, Consts.UTF_8);
            entity_0.setContentType("application/x-www-form-urlencoded");
            newPost.setEntity(entity_0);
            newPost.setConfig(globalConfig);
            res = httpClient.execute(newPost, context);
            String dealResponse1 = dealResponse(res,
                            "https://www.zyfax.cn/ActivityWeb/lotteryWebController/queryLotteryRules.json");
            logger.info("访问：{}接口返回的结果为：{}",
                            "https://www.zyfax.cn/ActivityWeb/lotteryWebController/queryLotteryRules.json",
                            dealResponse1);
            res.close();
            //邀请好友
            HttpPost newPost1 = new HttpPost(
                            "https://www.zyfax.cn/MarketWeb/financial/getPartnerShareWithMobile.json");
            List<BasicNameValuePair> invitePara = new ArrayList<>();
            BasicNameValuePair pair0 = new BasicNameValuePair("inviteMobile", "15327707110");
            invitePara.add(pair0);
            UrlEncodedFormEntity entity_1 = new UrlEncodedFormEntity(invitePara, Consts.UTF_8);
            entity_1.setContentType("application/x-www-form-urlencoded");
            newPost1.setEntity(entity_1);
            newPost1.setConfig(globalConfig);
            res = httpClient.execute(newPost1, context);
            String dealResponse2 = dealResponse(res,
                            "https://www.zyfax.cn/MarketWeb/financial/getPartnerShareWithMobile.json");
            logger.info("访问：{}接口返回的结果为：{}",
                            "https://www.zyfax.cn/MarketWeb/financial/getPartnerShareWithMobile.json",
                            dealResponse2);
            res.close();
            //抽奖接口
            HttpPost newPost2 = new HttpPost(
                            "https://www.zyfax.cn/ActivityWeb/lotteryWebController/lottery.json");
            List<BasicNameValuePair> choujiang = new ArrayList<>();
            BasicNameValuePair pair1 = new BasicNameValuePair("activityType", "1");
            choujiang.add(pair1);
            UrlEncodedFormEntity entity_2 = new UrlEncodedFormEntity(choujiang, Consts.UTF_8);
            entity_2.setContentType("application/x-www-form-urlencoded");
            newPost2.setEntity(entity_2);
            newPost2.setConfig(globalConfig);
            res = httpClient.execute(newPost2, context);
            String dealResponse3 = dealResponse(res,
                            "https://www.zyfax.cn/ActivityWeb/lotteryWebController/lottery.json");
            logger.info("访问：{}接口返回的结果为：{}",
                            "https://www.zyfax.cn/ActivityWeb/lotteryWebController/lottery.json",
                            dealResponse3);

        } finally {
            // 关闭连接,释放资源
            shutDownResoure(httpClient, res);
        }
    }

    /**
     * 处理httpclient返回的结果
     */
    private static String dealResponse(CloseableHttpResponse res, String uri) throws Exception {
        int status = res.getStatusLine().getStatusCode();
        logger.info("status:{}", status);
        if (HttpStatus.SC_OK == status) {
            logger.info("访问:{}接口成功！", uri);
            HttpEntity entityLogin = res.getEntity();
            if (entityLogin != null) {
                String responseData = EntityUtils.toString(entityLogin, "UTF-8");
                return responseData;
            }
        } else {
            logger.error("访问:{}接口失败！", uri);
            throw new BaseException(CommonExceptionEnums.INTERNET_CONNECTION_FAIL);
        }
        return null;
    }

    /**
     * 关闭资源
     */
    private static void shutDownResoure(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 使用json格式数据请求一个接口
     * @param url
     * @param json
     * @return
     */
    public static String HttpPostWithJson(String url, String json) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new ByteArrayEntity(json.getBytes("UTF-8")));
            response = httpClient.execute(httpPost);
            return dealResponse(response, url);

        } catch (Exception e) {
            logger.error("使用json格式访问接口：{}异常，异常信息为：", url, e);
        } finally {
            shutDownResoure(httpClient, response);
        }
        return "";

    }





}
