package com.horizon.springboottransaction.datasource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author lihh 18:31
 */
@Configuration
@MapperScan(basePackages="com.horizon.springboottransaction.test01",sqlSessionFactoryRef="test1SqlSessionFactory")
public class test1MybatisConfig {
    //配置数据源
    @Primary
    @Bean(name="test1Datasource")
    public DataSource testDatasource(DBConfig01 config01) throws SQLException {
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl(config01.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(config01.getPassword());
        mysqlXADataSource.setUser(config01.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("test1Datasource");

        atomikosDataSourceBean.setMinPoolSize(config01.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(config01.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(config01.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(config01.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(config01.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(config01.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(config01.getMaxIdleTime());
        return atomikosDataSourceBean;
    }
    @Primary
    @Bean(name="test1SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1Datasource")DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //如果还有分页等其他事务
        //      bean.setMapperLocations(new PathMatchingResourcePatternResolver().
        //              getResources("classpath:mybatis/test1/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name="test1SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test1SqlSessionFactory")
            SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
