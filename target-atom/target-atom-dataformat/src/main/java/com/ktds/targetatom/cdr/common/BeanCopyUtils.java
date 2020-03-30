package com.ktds.targetatom.cdr.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanCopyUtils {
	protected final static Logger log = LoggerFactory.getLogger(BeanCopyUtils.class);
	
	public static void copyProperties(final Object dest, final Object orig) { 
		Method[] sourceMethods = orig.getClass().getMethods();
		Method[] targetMethods = dest.getClass().getDeclaredMethods();
		
		for (int sourceNum = 0; sourceNum < sourceMethods.length; sourceNum++) {
			Method sourceMethod = sourceMethods[sourceNum];
			sourceMethod.setAccessible(true);
			
			String sMethod = sourceMethod.getName();
			
			if (sMethod.toUpperCase().startsWith("GET")) {
				sMethod = sMethod.substring(3);
				for (int targetNum=0; targetNum < targetMethods.length; targetNum++) {
					Method targetMethod = targetMethods[targetNum];
					targetMethod.setAccessible(true);
					
					String tMethod = targetMethod.getName();
					//log.debug("1::: orig method ::: {} ::: target method ::: {}", sourceMethod.getName(), targetMethod.getName());
					if (tMethod.toUpperCase().startsWith("SET") && tMethod.toUpperCase().endsWith(sMethod.toUpperCase())) {
						//log.debug("2::: orig method ::: {} ::: target method ::: {}", sourceMethod.getName(), targetMethod.getName());
						try {
							targetMethod.invoke(dest, sourceMethod.invoke(orig));
						} catch (IllegalAccessException e) {
							log.error("Incorrect Method Name during Class Copy ::: orig method ::: {} ::: target method ::: {}",
									sourceMethod.getName(), targetMethod.getName());
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							log.error("Incorrect Method Name during Class Copy ::: orig method ::: {} ::: target method ::: {}",
									sourceMethod.getName(), targetMethod.getName());
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							log.error("Incorrect Method Name during Class Copy ::: orig method ::: {} ::: target method ::: {}",
									sourceMethod.getName(), targetMethod.getName());
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
