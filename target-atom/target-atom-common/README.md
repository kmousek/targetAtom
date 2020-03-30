## About

This project는 targetatom의 Apache Camel component에 대한 프로젝트이다.

Features:
- Dynamic Camel Route Loader
- Spring AOP Logger
- Camel Message Tracer


## Dynamic Camel Route Loader

Camel Context에 Dynamic하게 Route를 추가하는 기능을 제공

- Route File은 spring/routes/ CDR Fomat별로 생성하여 사용할 것을 권장한다.

```
spring/routes/kr/krRouteProcessor.xml
```

Usage examples:

- Spring Application에서 다음과 같이 사용한다.

```
	@Bean
	CamelContextConfiguration contextConfiguration() {
		return new CamelContextConfiguration() {
			@Override
			public void beforeApplicationStart(CamelContext camelContext) {
				
			}
			@Override
			public void afterApplicationStart(CamelContext camelContex) {
				log.debug("--> afterApplicationStart");
				log.trace("   camelContext.name= {}", camelContext.getName());
				try {
					String routePath = "spring/routes/DynamicDispatcher.xml";
					RouteLoader.getInstance().addRoute(camelContext, routePath);
				} catch (Exception e) {
					log.error("Exception occured during initialize application after camelContext stated : \n {}", e);
				}	finally {
					log.debug("<-- afterApplicationStart");
				}
			}
		};
	}
```

## Spring AOP Logger

Spring Bean으로 등록된 `com.ktds.targetatom` 패키지의 로그를 자동으로 출력한다.

- Spring AOP는 AutoConfiguration과 ComponentScan을 기반으로 컴포턴트를 Scan한다.
- 따라서, Spring Application Main에 `@Configuration`, `@ComponentScan`, `EnableAspectJAutoProxy(proxyTargetClass=True)`가 필요하다.

Usage examples:

```
@Configuration
@ComponentScan("com.ktds.targetatom")
@SpringBootApplication
@ImportResource("classpath:spring/camelContextMain.xml")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class DynamicDispatcherRefactoredApplication  {
```

## Camel Message Tracer

Camel Message Tracer는 기존 Camel에서 제공되는 Tracer에 targetatom에서 Custom Formatter를 추가한 것이다.

Usage examples:

```
	@Bean
	CamelContextConfiguration contextConfiguration() {
		return new CamelContextConfiguration() {
			@Override
			public void beforeApplicationStart(CamelContext camelContext) {

			}

			@Override
			public void afterApplicationStart(CamelContext camelContex) {
				log.debug("--> afterApplicationStart");
				log.trace("   camelContext.name= {}", camelContext.getName());
				try {
					Tracer tracer = new Tracer();
					TraceFormatter formatter = new TraceFormatter();
					tracer.setFormatter(formatter);
					tracer.setEnabled(true);
					camelContext.addInterceptStrategy(tracer);
				} catch (Exception e) {
					log.error("Exception occured during initialize application after camelContext stated : \n {}", e);
				} finally {
					log.debug("<-- afterApplicationStart");
				}
			}
		};
	}

```
