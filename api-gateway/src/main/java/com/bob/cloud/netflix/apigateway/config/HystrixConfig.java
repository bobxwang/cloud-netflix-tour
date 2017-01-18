package com.bob.cloud.netflix.apigateway.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.graphite.GraphiteSender;
import com.codahale.metrics.graphite.GraphiteUDP;
import com.netflix.hystrix.contrib.codahalemetricspublisher.HystrixCodaHaleMetricsPublisher;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import org.springframework.context.annotation.Bean;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class HystrixConfig {

    @Bean
    public HystrixMetricsPublisher hystrixMetricsPublisher(MetricRegistry metricRegistry) {
        HystrixCodaHaleMetricsPublisher publisher = new HystrixCodaHaleMetricsPublisher(metricRegistry);
        HystrixPlugins.getInstance().registerMetricsPublisher(publisher);
        return publisher;
    }

    @Bean
    public GraphiteReporter graphiteReporter(MetricRegistry metricRegistry) {
        final GraphiteReporter reporter = GraphiteReporter
                .forRegistry(metricRegistry)
                .prefixedWith("fc-bb-loan-web-bb")
                .build(graphite());
        reporter.start(1, TimeUnit.MINUTES);
        return reporter;
    }

    @Bean
    GraphiteSender graphite() {
//        return new Graphite(new InetSocketAddress("10.0.89.107", 2003));
//        return new Graphite(new InetSocketAddress("192.168.2.22", 2003));
        return new GraphiteUDP(new InetSocketAddress("metricsreport.51.nb", 8125));
    }
}