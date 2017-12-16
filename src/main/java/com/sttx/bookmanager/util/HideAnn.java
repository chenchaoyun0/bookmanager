package com.sttx.bookmanager.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不打印敏感信息
 * @Description 在实体类中使用
 * @author lintong[lintong1@yusys.com.cn]
 * @date 2017年2月8日 下午3:47:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
public @interface HideAnn {

}
