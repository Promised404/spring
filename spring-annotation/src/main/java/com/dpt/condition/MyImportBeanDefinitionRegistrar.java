package com.dpt.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.dpt.bean.RainBow;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{

	/**
	 * AnnotationMetadata:当前类的注解信息
	 * BeanDefinitionRegistry: BeanDefinition注册类；
	 * 		把所有需要添加到容器中的bean;BeanDefinitionRegistry.registerBeanDefinition手工注册进来
	 */
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		boolean beanDefinition = registry.containsBeanDefinition("com.dpt.bean.Red");
		boolean beanDefinition2 = registry.containsBeanDefinition("com.dpt.bean.Blue");
		if (beanDefinition && beanDefinition2) {
			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
			registry.registerBeanDefinition("RainBow", rootBeanDefinition);
		}
	}

}
