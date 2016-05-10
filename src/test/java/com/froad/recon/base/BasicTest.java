 
package com.froad.recon.base;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

 
public class BasicTest {

	public static ApplicationContext applicationContext;
	 public static String[] configLocations=
	 {"applicationContext.xml"};
   
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
       applicationContext= new ClassPathXmlApplicationContext(configLocations);	
	   System.out.println("beforeClass");
	}
	
    public static void initialSpring(){
    	applicationContext=new ClassPathXmlApplicationContext(configLocations);
    	  System.out.println("initialSpring");
    }
  
}
