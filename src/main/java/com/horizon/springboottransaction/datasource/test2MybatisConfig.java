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

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author lihh 18:31
 */
@Configuration
@MapperScan(basePackages="com.horizon.springboottransaction.test02",sqlSessionFactoryRef="test2SqlSessionFactory")
public class test2MybatisConfig {

    //配置数据源
    @Bean(name="test2Datasource")
    public DataSource testDatasource(DBConfig02 config02) throws SQLException {
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl(config02.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(config02.getPassword());
        mysqlXADataSource.setUser(config02.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("test2Datasource");

        atomikosDataSourceBean.setMinPoolSize(config02.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(config02.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(config02.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(config02.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(config02.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(config02.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(config02.getMaxIdleTime());
        return atomikosDataSourceBean;
    }

    @Bean(name="test2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2Datasource")DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //如果还有分页等其他事务
        //      bean.setMapperLocations(new PathMatchingResourcePatternResolver().
        //              getResources("classpath:mybatis/test1/*.xml"));
        return bean.getObject();
    }

    @Bean(name="test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory")
            SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
