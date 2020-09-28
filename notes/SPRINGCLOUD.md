## Spring Cloud
* `spring-cloud` version **Greenwich** and below uses `spring-cloud-stream-reactive`
to provide reactive API. However, in version **Hoxton** and above, it is now
using `spring-cloud-function` which provides native reactive API support. 
`spring-cloud-stream-reactive` is deprecated after version 2.2.1.

* In older versions, the implementation of reactive cloud stream is done by using
`@EnableBinding`, `@Processor`, and `@StreamListener` annotations. 

```
@SpringBootApplication
@EnableBinding(Processor.class)
public class SampleApplication  {
    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public String uppercase(String value) {
        return value.toUpperCase();
    }
}
```

* In newer versions, the same code can be replaced with function
```
@SpringBootApplication
public class SampleApplication  {
    @Bean
    public Function<String, String> uppercase() {
        return value -> value.toUpperCase();
    }
}
```