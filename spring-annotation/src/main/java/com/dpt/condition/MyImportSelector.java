package com.dpt.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector{

	/**
	 * 返回值，就是到导入到容器中的组件全类名
	 * AnnotationMetadata:当前标注的@Import注解的类的所有注解信息
	 */
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		
		//不要返回null值，可以返回空数组
		return new String[]{"com.dpt.bean.Blue","com.dpt.bean.Yellow"};
	}

}
