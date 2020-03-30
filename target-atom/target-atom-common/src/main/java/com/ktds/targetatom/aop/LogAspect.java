package com.ktds.targetatom.aop;

import org.apache.camel.Ordered;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
@Aspect
@Component
//@Order(Ordered.LOWEST)
public class LogAspect {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Around("execution(* com.ktds.targetatom..*.*(..))")
	public Object logging(ProceedingJoinPoint point) throws Throwable {
		log.info("[{}>== {}:{}]"
				, point.getThis()
				, point.getSignature().getDeclaringTypeName()
				, point.getSignature().getName()
				);
		CodeSignature codeSignature = (CodeSignature) point.getSignature();
		String[] params = null;
		if(codeSignature != null) {
			params = codeSignature.getParameterNames();
		}
		Object[] args = point.getArgs();
		if(params != null && args != null) {
			for(int i = 0; i < args.length; i++) {
				log.info("    {}={}", params[i], args[i]);
			}
		}
		
		Object result = point.proceed();
		
		log.info("[{}<== {}:{}]"
				, point.getThis()
				, point.getSignature().getDeclaringTypeName()
				, point.getSignature().getName()
				);
		if(result != null) {
			log.info("   result={}", ToStringBuilder.reflectionToString(result,ToStringStyle.SHORT_PREFIX_STYLE));
		} else {
			log.info("   result=null");
		}
		return result;
	}
}
