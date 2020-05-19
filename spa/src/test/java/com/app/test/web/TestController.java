package com.app.test.web;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
 	
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.app.config.AppConfig;
import com.app.config.WebConfig;
import com.app.vo.ResultVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebConfig.class, AppConfig.class})
public class TestController {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getCombinationList() throws Exception {
       String uri = "/lettercombinations/56789212/10";
       MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
          .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
       
       int status = mvcResult.getResponse().getStatus();
       assertEquals(200, status);
       String content = mvcResult.getResponse().getContentAsString();
       ResultVO list = mapFromJson(content, ResultVO.class);
       System.out.println("List Size>"+list.getList().size());
       list.getList().forEach((s)->{
    	   System.out.println(s);
       });
       assertTrue(list.getList().size()>0);
    }
    
    @Test
    public void test() {
    	Assert.assertTrue(true);
    }
    
    protected <T> T mapFromJson(String json, Class<T> clazz)
    	      throws JsonParseException, JsonMappingException, IOException {
    	      
    	      ObjectMapper objectMapper = new ObjectMapper();
    	      return objectMapper.readValue(json, clazz);
    	   }
}