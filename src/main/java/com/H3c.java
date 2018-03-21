package com;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class H3c {
    public static CloseableHttpClient client;
    public static void main(String[] args) throws Exception {
        /* 获取所有主机列表 */
        getHost("http://192.168.20.171:8080/cas/casrs/host/");
        /* 获取某个主机上的所有虚拟机列表 */
        getVM("http://192.168.20.171:8080/cas/casrs/vm/vmList?hostId=2");
        /* 获取某个虚拟机的性能监控数据 */
        getVMperf("http://192.168.20.171:8080/cas/casrs/vm/id/9/monitor");
        /* 查询指定集群统计信息 */
        getClusterInfo("http://192.168.20.171:8080/cas/casrs/cluster/summary/2");
    }
    /* 获取所有主机列表 */
    public static void getHost (String url) throws Exception {
        CloseableHttpClient client = newInstance();
        HttpGet get = new HttpGet(url);
        get.addHeader("accept", "application/xml");
        HttpResponse response = client.execute(get);
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
    /* 获取某个主机上的所有虚拟机列表 */
    public static void getVM (String url) throws Exception {
        CloseableHttpClient client = newInstance();
        HttpGet get = new HttpGet(url);
        get.addHeader("accept", "application/xml");
        HttpResponse response = client.execute(get);
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
    /* 获取某个虚拟机的性能监控数据 */
    public static void getVMperf(String url) throws Exception {
        CloseableHttpClient client = newInstance();
        HttpGet get = new HttpGet(url);
        get.addHeader("accept", "application/xml");
        HttpResponse response = client.execute(get);
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
    }
    /* 查询指定集群统计信息 */
    public static void getClusterInfo(String url) throws Exception {
        CloseableHttpClient client = newInstance();
        HttpGet get = new HttpGet(url);
        get.addHeader("accept", "application/xml");
        HttpResponse response = client.execute(get);
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
    }
    /* 以实例方式实现 H3C CAS 3.0 云计算管理平台认证 */
    public static CloseableHttpClient  newInstance() {
        if (client == null) {
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    // 认证范围
                    new AuthScope("192.168.20.171", 8080, "VMC RESTful Web Services"),
                    // 认证用户名和密码
                    new UsernamePasswordCredentials("admin", "admin"));
            client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        }
        return client;
    }
}
