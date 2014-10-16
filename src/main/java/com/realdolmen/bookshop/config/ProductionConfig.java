package com.realdolmen.bookshop.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.realdolmen.bookshop.dao.BookDao;
import com.realdolmen.bookshop.dao.BookDaoSQLImpl;
import com.realdolmen.bookshop.domain.VatNumber;
import com.realdolmen.bookshop.services.BookService;
import com.realdolmen.bookshop.services.BookServiceImpl;
import com.realdolmen.bookshop.services.GreetingServiceImpl;
import com.realdolmen.bookshop.services.PaymentService;
import com.realdolmen.bookshop.services.PaymentServiceImpl;

@Configuration
@ComponentScan(basePackages={"com.realdolmen.bookshop"})
@EnableAspectJAutoProxy
@ImportResource({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ProductionConfig {
	
	@Value("Hello, world from config java file")
	private String message1;
	
	@Value("Welcome to spring")
	private String message2;
	
	@Value("An offer you cannot refuse")
	private String bookStoreName;
	
	@Value("100")
	private int maxBooks;
	
	@Value("BE.123.4567890.12")
	private VatNumber vatNumber;
	
    @Bean
    public static BasicDataSource dataSource(){
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3307/test");
        source.setUsername("root");
        source.setPassword("password");
        return source;
    }
    
    @Bean
    public static LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.realdolmen.bookshop.domain" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public static PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    static Properties additionalProperties() {
    	Logger log = Logger.getLogger("org.hibernate.SQL");
    	log.setLevel(Level.TRACE);
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }
	
	@Bean
	public GreetingServiceImpl greetingService1() {
		return new GreetingServiceImpl(message1);
	}
	
	@Bean
	public GreetingServiceImpl greetingService2() {
		return new GreetingServiceImpl(message2);
	}
	
	@Bean
	public BookServiceImpl bookService() {
		BookServiceImpl bookService = new BookServiceImpl(bookStoreName, maxBooks );
		bookService.setVatNumber(vatNumber);
		return bookService;
	}
	
	@Bean
	public PaymentServiceImpl paymentService() {
		PaymentServiceImpl paymentService = new PaymentServiceImpl();
		paymentService.setPaymentProvider("Visa");
		return paymentService;
	}

}
