//IntelliJ IDEA
//springtest
//PersonController
//2020/2/16
// Author:御承扬
//E-mail:2923616405@qq.com

package com.pyc.springtest.web;

import com.pyc.springtest.dao.PersonRepository;
import com.pyc.springtest.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Person>findAll(){
        return personRepository.findAll();
    }
}
