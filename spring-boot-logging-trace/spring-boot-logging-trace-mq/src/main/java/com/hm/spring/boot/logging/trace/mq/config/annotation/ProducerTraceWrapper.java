package com.hm.spring.boot.logging.trace.mq.config.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mq生产者 传递TraceId
 *
 * @author huwenfeng
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ProducerTraceWrapper {

    String value() default "";

}
