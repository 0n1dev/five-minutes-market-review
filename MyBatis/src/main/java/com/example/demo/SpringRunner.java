package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@RequiredArgsConstructor
@Component
public class SpringRunner implements ApplicationRunner {

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Connection connection = dataSource.getConnection();

        log.info("URL : " + connection.getMetaData().getURL());
        log.info("UserName : " + connection.getMetaData().getUserName());

        jdbcTemplate.execute("INSERT INTO Products (prod_name, prod_price) VALUES ('테스트', 7000)");

    }
}
