package net.example.backend.config;

import java.util.Properties;
import javax.sql.DataSource;
import net.example.backend.pojo.car.Cabrio;
import net.example.backend.pojo.car.Car;
import net.example.backend.pojo.car.Pickup;
import net.example.backend.pojo.order.Carorder;
import net.example.backend.pojo.order.Cash;
import net.example.backend.pojo.order.Creditcard;
import net.example.backend.pojo.order.Payment;
import net.example.backend.pojo.user.User;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

/**
 * Hibernate configuration class
 * 
 *
 * @author andre
 */
@Configuration
public class JpaConfig {

    @Value("#{dataSource}")
    private DataSource dataSource;

    @Bean
    public AnnotationSessionFactoryBean sessionFactoryBean() {
        Properties props = new Properties();
        props.put("hibernate.dialect", MySQL5Dialect.class.getName());
        props.put("hibernate.show_sql", "true");


        AnnotationSessionFactoryBean bean = new AnnotationSessionFactoryBean();
        bean.setAnnotatedClasses(new Class[]{
            User.class,
            Car.class,
            Cabrio.class,
            Pickup.class,
            Carorder.class,
            Payment.class,
            Creditcard.class,
            Cash.class
                    });
        bean.setHibernateProperties(props);
        bean.setDataSource(this.dataSource);

        return bean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactoryBean().getObject());
        return hibernateTransactionManager;
    }
}
