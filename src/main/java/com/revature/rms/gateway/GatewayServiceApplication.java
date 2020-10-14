package com.revature.rms.gateway;

import com.netflix.appinfo.AmazonInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//	import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableEurekaClient
@SpringBootApplication
public class GatewayServiceApplication {

	private final Environment env;

	public GatewayServiceApplication(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils utils) {

		final int managementPort = Integer.parseInt(env.getProperty("server.port"));
		final EurekaInstanceConfigBean instance = new EurekaInstanceConfigBean(utils) {

			//needed only when Eureka server instance binds to EIP
			@Scheduled(initialDelay = 10000L, fixedRate = 30000L)
			public void refreshInfo() {
				AmazonInfo newInfo = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
				if (!this.getDataCenterInfo().equals(newInfo)) {
					((AmazonInfo) this.getDataCenterInfo()).setMetadata(newInfo.getMetadata());
				}
			}

			private AmazonInfo getAmazonInfo() {
				return (AmazonInfo) getDataCenterInfo();
			}

			@Override
			public String getHostname() {
				AmazonInfo info = getAmazonInfo();
				final String publicHostname = info.get(AmazonInfo.MetaDataKey.publicHostname);
				return this.isPreferIpAddress() ?
						info.get(AmazonInfo.MetaDataKey.localIpv4) :
						publicHostname == null ?
								info.get(AmazonInfo.MetaDataKey.localHostname) : publicHostname;
			}

			@Override
			public String getHostName(final boolean refresh) {
				return getHostname();
			}

			@Override
			public int getNonSecurePort() {
				return managementPort;
			}

			@Override
			public String getHomePageUrl() {
				return super.getHomePageUrl();
			}

			@Override
			public String getStatusPageUrl() {
				String scheme = getSecurePortEnabled() ? "https" : "http";
				return scheme + "://" + getHostname() + ":"
						+ managementPort + getStatusPageUrlPath();
			}

			@Override
			public String getHealthCheckUrl() {
				String scheme = getSecurePortEnabled() ? "https" : "http";
				return scheme + "://" + getHostname() + ":"
						+ managementPort + getHealthCheckUrlPath();
			}
		};

		AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("cloudconfig");
		instance.setDataCenterInfo(info);

		return instance;
	}


}
