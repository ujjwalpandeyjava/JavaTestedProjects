package pandey.ujjwal.concepts.spring.APO_Demo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configurable
@ComponentScan(basePackages = "demo")
@EnableAspectJAutoProxy
public class BeanConfig {

}
