package com.yl.mapper;

import com.yl.domain.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestMapper {

    List<Person> testMysql();

    List<Person> testOtherDataSource();
}
