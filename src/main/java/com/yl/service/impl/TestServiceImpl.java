package com.yl.service.impl;

import com.yl.config.DataSource;
import com.yl.datasource.DatabaseType;
import com.yl.domain.Person;
import com.yl.mapper.TestMapper;
import com.yl.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuanli
 * @create 2019-05-13 21:35
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Person> testMysql() {
        return testMapper.testMysql();
    }

    @Override
    @DataSource(DatabaseType.MYSQLDATASOURCE)
    public List<Person> testOtherDataSource() {
        return testMapper.testOtherDataSource();
    }
}
