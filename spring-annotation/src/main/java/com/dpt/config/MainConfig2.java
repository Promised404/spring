package com.dpt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import com.dpt.bean.Color;
import com.dpt.bean.ColorFactoryBean;
import com.dpt.bean.Person;
import com.dpt.bean.Red;
import com.dpt.condition.LinuxCondition;
import com.dpt.condition.MyImportBeanDefinitionRegistrar;
import com.dpt.condition.MyImportSelector;
import com.dpt.condition.WindowsCondition;

//类中注解统一设置，满足当前条件，这个类中配置的所有bean注册才能生效
@Conditional(value = {WindowsCondition.class})
@Configuration
@Import({Color.class,Red.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
//导入组件，id默认是组件的全类名
public class MainConfig2 {

	
	/**
	 * prototype：多实例的:ioc容器启动并不会去调用方法创建对象放到容器中。而是每次获取的时候才会调用方法创建对象；
	 * singleton:单实例的（默认值）：ioc容器启动会调用方法创建对象放到ioc容器中。
	 *  		以后每次获取都是直接从容器（map.get()）中拿。
	 *  懒加载：
	 *  	单实例bean:默认在容器启动时候创建对象；
	 *  	懒加载：容器启动时不加载对象。第一次使用（获取）Bean的时候再来创建对象，并初始化
	 * @return
	 */
	//@Scope("prototype")
	@Lazy
	@Bean(value = "person")
	public Person person01() {
		System.out.println("给容器中添加Person...");
		return new Person(110,"wanwu");
	}
	
	/**
	 * @Conditional({Condition}) : 按照一定的条件进行判断，满足条件给容器注册bean,
	 * 
	 * 如果系统是windows,给容器注册（"bill"）
	 * 如果是linux系统，给容器注册("linus")
	 */
	@Conditional(value = {WindowsCondition.class})
	@Bean("bill")
	public Person person02() {
		return new Person(99,"Bill Gates");
	}

	@Bean("linus")
	@Conditional(value = {LinuxCondition.class})
	public Person person03() {
		return new Person(46,"linus");
	}
	
	/**
	 * 给容器中注册组件：
	 * 	1)、包扫描+ 组件标注的注解（@Controller/@Service/@Repository/@Component）
	 *  2)、@Bean[导入第三方包里面的组件]
	 *  3)、@Import[快递给容器中导入一个组件]
	 *  	1)、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件,id默认是全类名
	 *  	2)、@ImportSelector:返回需要导入的组件的全类名数组
	 *  	3)、@ImportBeanDefinitionRegistrar:手动注册
	 *  4)、使用Spring提供的@FactoryBean(工厂Bean);
	 *  	1)、默认获取到的是工厂bean调用getObject创建的对象
	 *  	2)、要获取工厂Bean本身,我们需要geiId前面加一个&
	 */
	
	@Bean
	public ColorFactoryBean colorFactoryBean() {
		return new ColorFactoryBean();
	}
	
}
