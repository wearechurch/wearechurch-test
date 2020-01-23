package com.wearechurch.tool.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

@Retention(RetentionPolicy.RUNTIME)
@Valid
public @interface ValidRequest {
	RequestBody value() default @RequestBody;
}
