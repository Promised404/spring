package com.dpt.ext;

import java.util.EventListener;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

import com.dpt.bean.Blue;

/**
 * 扩展原理：
 * BeanPostProcessor:bean的后置处理器,bean创建对象初始化前后进行拦截工作
 * 1、BeanFactoryPostProcessor: beanFactory的后置处理器；
 * 		在BeanFactory标准初始化之后调用；所有的bean定义已经保存加载到beanFactory,但是bean的实例还未创建
 * 
 * 1)、ioc容器创建对象
 * 2)、invokeBeanFactoryPostProcessors(beanFactory);执行BeanFactoryPostProcessor;
 * 		如何找到所有的BeanFactoryPostProcessor并执行他们的方法；
 * 			1)、直接在BeanFacotry中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法
 * 			2)、在初始化创建其他组件前面执行
 * 
 * 2、BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 * 		postProcessBeanDefinitionRegistry();
 * 		在所有bean定义信息将要被加载，bean实例还未创建的；
 * 
 * 		优先于BeanFactoryPostProcessor执行；
 * 		利用BeanDefinitionRegistryPostProcessor给容器中额外再添加一些组件；
 * 
 * 原理：
 * 		1)、ioc创建对象
 * 		2)、refresh()->invokeBeanFactoryPostProcessors(beanFactory);
 * 		3)、先从容器中获取所有的BeanDefinitionRegistryPostProcessor组件。
 * 			1、依次触发所有的postProcessBeanDefinitionRegistry()方法
 * 			2、再来触发postProcessBeanFactory()方法BeanFactoryPostProcessor；
 * 		4)、再来从容器中找到BeanFactoryPostProcessor组件，然后依次触发postProcessBeanFactory()方法
 * 
 * 3、ApplicationListener：监听容器中发布的事件。事件驱动模型的开发
 * 		public interface ApplicationListener<E extends ApplicationEvent> extends EventListener 
 * 			监听ApplicationEvent及其下面的事件；
 * 
 * 		步骤:
 * 			1)、写一个监听器(ApplicationListener的实现)来监听某个事件（ApplicationEvent下面及其子类）
 * 				@EventListener;
 * 				原理：使用 EventListenerMethodProcessor 处理器来解析方法上的@EventListener;
 * 			2)、把监听器加入到容器；
 * 			3)、只要容器中有相关事件发布，我们就能监听到这个事件；
 * 					ContextRefreshedEvent:容器刷新完成(所有bean都已经创建)
 * 					ContextClosedEvent：关闭容器会发布这个事件；
 * 			4)、发布一个事件
 * 
 * 原理：
 * 		ContextRefreshedEvent、IOCTest_Ext$1[source=我发布的事件]、ContextClosedEvent；
 * 	1)、ContextRefreshedEvent事件：
 * 		1)、容器创建对象：refresh();
 * 		2)、finishRefresh();容器刷新完成
 * 		3)、publishEvent(new ContextRefreshedEvent(this));
 * 	2)、自己发布事件;ContextClosedEvent
 * 	3)、容器关闭会发布ContextClosedEvent;
 * 			【事件发布流程】：
 * 				1)、获取事件的多播器(派发器)：getApplicationEventMulticaster()
 * 				2)、multicastEvent()派发事件
 * 				3)、获取到所有的ApplicationListener;
 * 					for (final ApplicationListener<?> listener : getApplicationListeners(event, type))
 * 					1)、如果有Executor，可以支持使用Executor进行异步派发
 * 					2)、否则同步的方式直接执行listener方法；
 * 					拿到listener回调onApplicationEvent方法；
 * 
 * 【事件多播器（派发器）】
 * 		1)、容器创建对象：refresh();
 * 		2)、initApplicationEventMulticaster();初始化initApplicationEventMulticaster、
 * 			1)、先去容器中找有没有id="applicationEventMulticaster"的组件
 * 			2)、如果没有this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
 * 				并且加入到容器中，我们就可以在其他组件要派发事件，自动注入applicationEventMulticaster；
 * 
 * 【容器中有哪些监听器】
 * 		1)、容器创建对象：refresh();
 * 		2)、registerListeners();
 * 			从容器中拿到所有的监听器，把它们注册到applicationEventMulticaster中；
 * 			String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 * 			//将listener注册到applicationEventMulticaster中	
 * 			getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 * 
 * 	SmartInitializingSingleton 原理：->afterSingletonsInstantiated();
 * 		1)、ioc容器创建对象并refresh()；
 * 		2)、finishBeanFactoryInitialization(beanFactory);初始化剩下的单实例bean
 * 			1)、先创建所有的单实例bean;getBean();
 * 			2)、获取所有创建好的单实例bean;判断是否是SmartInitializingSingleton类型的；
 * 				如果是就调用afterSingletonsInstantiated();
 * @author 邓鹏涛
 *
 */
@Configuration
@ComponentScan("com.dpt.ext")
public class ExtConfig {

	@Bean
	public Blue blue() {
		return new Blue();
	}
	
}
