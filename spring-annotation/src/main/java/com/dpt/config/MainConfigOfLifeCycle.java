package com.dpt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.dpt.bean.Car;

/**
 * bean的生命周期：
 * 		bean创建---初始化---销毁的过程
 * 容器管理bean的生命周期；
 * 我们可以自定义初始化和销毁方法；容器在进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 * 
 * 构造（对象创建）
 * 		单实例：在容器启动的时候创建对象
 * 		多实例：在每次获取的时候创建对象
 * 
 * 遍历得到容器中所有的BeanPostProcessor;挨个执行beforeInitiallization,
 * 一旦返回null,跳出for循环，不会执行后面的 @BeanPostProcessor.postProcessBeforeInitialization
 * 
 * populateBean(beanName, bd, bw);先给bean进行属性赋值
 * 再初始化bean
 * initializeBean(beanName, existingBean, bd);
 * {
 * 		wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * 		invokeInitMethods(beanName, wrappedBean, mbd);
 * 		wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 * 
 * 
 * BeanPostProcessor.postProcessBeforeInitialization
 * 初始化：
 * BeanPostProcessor.postProcessAfterInitialization
 * 		对象创建完成，并赋值好，调用初始化方法....
 * 销毁：
 * 		单实例：容器关闭的时候销毁
 * 		多实例：容器不会管理这个bean:容器不会调用销毁方法
 * 1)、指定初始化和销毁方法；
 * 		通过@Bean指定init-method destroy-method
 * 2)、通过让Bean实现InitializingBean(定义初始化逻辑),
 * 					DisposableBean(定义销毁逻辑)
 * 3)、可以使用JSR250；
 * 		@PostConstruct:在bean创建完成并且属性赋值完毕；来执行初始化方法
 * 		@Perdestroy:在容器销毁bean之前通知我们进行清理工作
 * 4)、BeanPostProcessor【interface】;bean的后置处理器；
 * 		在bean初始化前后进行一些处理工作；
 * 		postProcessBeforeInitialization:在初始化之前工作
 * 		postProcessAfterInitialization:在初始化之后进行工作
 * 
 * Spring底层对 BeanPostProcessor 的使用
 * 		bean赋值，注入其他组件，@Autowired,生命周期注解功能，@Async,xxx BeanPostProcessor;
 * 
 * @author 邓鹏涛
 *
 */
@ComponentScan("com.dpt.bean")
@Configuration
public class MainConfigOfLifeCycle {
	
	//@Scope("prototype")
	@Bean(initMethod="init",destroyMethod="destroy")
	public Car car() {
		return new Car(); 
	}
	
}
