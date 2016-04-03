package com.flur.persistence.db.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库实体类annotation
 * @author wx
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) //声明为runtime，可在运行时获取到注解
@Documented
public @interface Table {
	public String name()default "";
}
