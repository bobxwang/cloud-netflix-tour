package com.bob.just.netflix.lib;

import com.netflix.client.ClientFactory;
import com.netflix.client.config.IClientConfig;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.RestClient;

import java.net.URI;

import static com.netflix.config.ConfigurationManager.getConfigInstance;

/**
 * Created by bob on 16/8/16.
 */
public class App {

    public static void main(String[] args) throws Exception {

        ConfigurationManager.loadPropertiesFromResources("sample-client.properties");
        String listServers = getConfigInstance().getString("sample-client.ribbon.listOfServers");
        System.out.println(listServers);

        RestClient client = (RestClient) ClientFactory.getNamedClient("sample-client");
        IClientConfig clientConfig = ClientFactory.getNamedConfig("sample-client");   // in fact it is DefaultClientConfigImpl
        System.out.println(clientConfig.getClientName());
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build();
        for (int i = 0; i < 20; i++) {
            HttpResponse response = client.executeWithLoadBalancer(request);
            System.out.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
        }

        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
        System.out.println(lb.getLoadBalancerStats());
        ConfigurationManager.getConfigInstance().setProperty("sample-client.ribbon.listOfServers", "www.taobao.com:80,www.163.com:80");
        System.out.println("changing servers ...");
        Thread.sleep(3000);
        for (int i = 0; i < 20; i++) {
            HttpResponse response = client.executeWithLoadBalancer(request);
            System.out.println("Status code for " + response.getRequestedURI() + "  : " + response.getStatus());
            response.close();
        }
        System.out.println(lb.getLoadBalancerStats());
    }
}
