package com.yl.web;

import com.yl.domain.Person;
import com.yl.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuanli
 * @create 2019-05-13 21:34
 **/
@RestController
@RequestMapping("/test")
@Api
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/testMysql")
    @ApiOperation("动态数据源测试--MySql")
    public List<Person> testMysql(){

        return testService.testMysql();
    }

    @GetMapping("/testOtherDataSource")
    @ApiOperation("动态数据源测试--另一数据源")
    public List<Person> testOtherDataSource(){
        return testService.testOtherDataSource();
    }

}
