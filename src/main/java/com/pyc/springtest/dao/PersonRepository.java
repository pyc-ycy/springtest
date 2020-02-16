//IntelliJ IDEA
//springtest
//PersonRepository
//2020/2/16
// Author:御承扬
//E-mail:2923616405@qq.com


package com.pyc.springtest.dao;

import com.pyc.springtest.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
