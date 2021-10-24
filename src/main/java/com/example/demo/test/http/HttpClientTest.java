package com.example.demo.test.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author raining_heavily
 * @date 2020/9/6 18:02
 **/
public class HttpClientTest {

    public static String getHTML(String uri) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet(uri);
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        String html = null;
        try {
            //3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);

            //4.判断响应状态为200，进行处理
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                //5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                html = EntityUtils.toString(httpEntity, "utf-8");
//                System.out.println(html);
            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
        return html;
    }

    /**
     * 获取标题
     *
     * @param uri
     */
    public static void getTitle(String uri) {

        Document document = Jsoup.parse(getHTML(uri));
        //像js一样，通过标签获取title
        System.out.println(document.getElementsByTag("title").first().text());
    }

    /**
     * 获取微博热搜列表
     *
     * @param url
     */
    public static void getWeiboTop(String url) {
        String weibo = "https://s.weibo.com";
        Map<Integer, WeiboTop> map = new HashMap<>();
        Document document = Jsoup.parse(getHTML(url));
        Elements els = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");
//        el.remove(0);
        System.out.print(els.size());
        int i = 0;
        els.remove(0);
        for (Element e : els) {
            String title = e.getElementsByTag("td").get(1).getElementsByTag("a").text();//热搜
            String topUri = weibo + e.getElementsByTag("td").get(1).getElementsByTag("a").attr("href");
            String sum = e.getElementsByTag("td").get(1).getElementsByTag("span").text();//次数
            String count = e.getElementsByTag("td").get(2).getElementsByTag("i").text();//标签
            i++;
            map.put(i, new WeiboTop(i, title,topUri, sum, count));
        }
        System.out.print("-------------:" + i);
        System.out.print(map);
    }

    public void getWeichatArticle(String url){

    }

    public static void getRealUrl(String url){
        HttpClient httpClient = HttpClients.createDefault();
        HttpContext httpContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        try {
            //将HttpContext对象作为参数传给execute()方法,则HttpClient会把请求响应交互过程中的状态信息存储在HttpContext中
            httpClient.execute(httpGet, httpContext);
            //获取重定向之后的主机地址信息,即"http://127.0.0.1:8088"
//            HttpHost targetHost = (HttpHost)httpContext.getAttribute("http.target_host");//ExecutionContext.HTTP_TARGET_HOST
            //获取实际的请求对象的URI,即重定向之后的"/blog/admin/login.jsp"
//            HttpUriRequest realRequest = (HttpUriRequest)httpContext.getAttribute("http.request");//ExecutionContext.HTTP_REQUEST
//            System.out.println(httpContext);
            System.out.print(httpContext.getAttribute("http.protocol.redirect-locations"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            HttpClientUtils.closeQuietly(httpClient);
        }

    }

    public static void main(String[] args) {
//        getTitle("https://www.cnblogs.com/sam-uncle/p/10922366.html");
//        getWeiboTop("https://s.weibo.com/top/summary");
        getRealUrl("https://tophub.today/l?e=a2e5kXddit8oK39%2FJPlfO5Nzczrai8IS0jV1zAFytzPjw4WOK6qXGtDVV%2FXTzCnCN5trQYEZ55crOah9Gf5oOCG7QEIiLt9POKhaCelEUu3ZeU1VE%2BE%2B88ovWzvXKWWvz2wQ3lRJjY4P65GLRfd3J6aTq3c4BKSIwBG3JdsuBMrFa6n8DVnjsgys2JMoWBbfyA3tnxaMRLpkTjhbVYJaU60UqFxaKvSimga%2F1cKrxFWCKBC5y%2FvMrJ6hjFoEO50GimGQuhrpBehvH%2BhfrRTj");
    }
}
