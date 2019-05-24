package camel.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppStarter {

	
	public static void main(String[] args) throws InterruptedException {
		
		ClassPathXmlApplicationContext applicationContext = 
				  new ClassPathXmlApplicationContext("spring-camelcontext.xml");
		applicationContext.start();
		
		Thread.sleep(50000);
		
		applicationContext.stop();
		applicationContext.close();
	}
}
