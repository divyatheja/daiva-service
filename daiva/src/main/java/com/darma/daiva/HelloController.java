package com.darma.daiva;

import java.util.ArrayList;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.darma.daiva.model.UserDetailRequest;

import java.util.List;

import lombok.Data;


@RestController
public class HelloController {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @RequestMapping("/search")
    String hello() {
    	System.out.println("asdfsadfsdaf");
        return "Hello World!";
    }

    @RequestMapping(value = "/names", method = RequestMethod.GET)
	public List<String> getUserNames(
			@RequestParam("userName") String userName) {
		
		List<String> nameList = new ArrayList<>();
		nameList.add("user1");
		nameList.add("user2");
		return nameList;
	}
    
    @RequestMapping(value = "/names", method = RequestMethod.POST)
	public void getUserNames(UserDetailRequest userDetail) {
		
		/*getUserDetails(userId, model, false);
		return "salescenter-user-detail";*/
	}
    
    
    @Data
    static class Result {
        private final int left;
        private final int right;
        private final long answer;
    }

    // SQL sample
    @RequestMapping("calc")
    Result calc(@RequestParam int left, @RequestParam int right) {
        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("left", left)
                .addValue("right", right);
        return jdbcTemplate.queryForObject("SELECT :left + :right AS answer", source,
                (rs, rowNum) -> new Result(left, right, rs.getLong("answer")));
    }
}
