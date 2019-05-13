package com.yl.service;

import com.yl.domain.Person;

import java.util.List;

public interface TestService {

    List<Person> testMysql();

    List<Person> testOtherDataSource();

}
