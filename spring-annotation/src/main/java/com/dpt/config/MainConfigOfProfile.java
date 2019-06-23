package com.dpt.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.dpt.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Profile:
 * 		Spring为我们提供的可以根据当前环境，动态的激活切换一系列bean的功能；
 * 		
 * 开发环境、测试环境、生成环境；
 * 数据源：(/A)(/B)(/C); 
 * 
 *  @Profile:指定组件在那个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 *  
 *  1)、加了环境环境标识的bean,只有这个环境被激活的时候才能注册到容器中,默认是default环境
 *  2)、写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 *  3)、没有标注的环境标识的bean,在任何环境下都是加载的；
 * @author 邓鹏涛
 *
 */
//@Profile("test")
@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware{
	
	@Value("${db.user}")
	private String user;
	
	private StringValueResolver resolverResolver;
	
	private String driverClass;
	
	//@Profile("test")
	@Bean
	public Yellow yellow() {
		return new Yellow();
	}
	
	@Profile("test")
	@Bean("testDateSource")
	public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(pwd);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ssm_crud");
		dataSource.setDriverClass(driverClass);
		return dataSource;
	}
	
	@Profile("dev")
	@Bean("devDateSource")
	public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(pwd);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/o2o");
		dataSource.setDriverClass(driverClass);
		return dataSource;
	}
	
	@Profile("prod")
	@Bean("prodDataSource")
	public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(pwd);
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/scms");
		dataSource.setDriverClass(driverClass);
		return dataSource;
	}

	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		this.resolverResolver = resolver;
		driverClass = resolverResolver.resolveStringValue("${db.driverClass}");
	}
}
