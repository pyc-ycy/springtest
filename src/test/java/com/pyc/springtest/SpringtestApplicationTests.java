package com.pyc.springtest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyc.springtest.dao.PersonRepository;
import com.pyc.springtest.domain.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringtestApplication.class)
@WebAppConfiguration
@Transactional
class SpringtestApplicationTests {
    @Autowired
    PersonRepository personRepository;

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    String expectedJson;

    protected String Obj2Json(Object obj) throws JsonProcessingException{//5
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
    @Before
    public void setUp() throws JsonProcessingException{
        Person p1 = new Person("pyc");
        Person p2 = new Person("ycy");
        personRepository.save(p1);
        personRepository.save(p2);

        expectedJson = Obj2Json(personRepository.findAll());
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testPersonController() throws Exception{
        String uri = "/person";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();

        Assert.assertEquals("错误，正确的返回值为 200", 200, status);
        Assert.assertEquals("错误，返回值和预期返回值不一致", expectedJson, content);
    }
}
