package com.tml.controller;


import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tml.constants.Student;
import com.tml.service.FirstService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2019/2/11.
 */

@RestController
@RequestMapping(value = "/tml")
public class FirstController {


    /*
    guava中实现了限流器，限流算法是令牌桶算法
     */
    private static RateLimiter rateLimiter = RateLimiter.create(2);

    @Autowired
    private FirstService firstService;

    private static Logger logger = LoggerFactory.getLogger(FirstController.class);

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping()
                    .create();


    @RequestMapping(value = "/findByName", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "根据学生姓名查询学生详细信息")
    @ApiImplicitParam(name = "userName", value = "学生的用户名", required = true, dataType = "String")
    public Student hello(String userName) {

        logger.info("hello==>enter");
        long count = firstService.listAllStudent().stream().filter(student -> student.name.equals(userName)).count();
        if (count == 1) {
            return firstService.listAllStudent().stream().filter(student -> student.name.equals(userName))
                            .findFirst().get();
        } else {
            return null;
        }

    }

    @RequestMapping(value = "/findMatchedResult", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "查询大于一定score分数的前size名学生用户")
    @ApiImplicitParams({
                    @ApiImplicitParam(name = "score", value = "分数", required = true, dataType = "int"),
                    @ApiImplicitParam(name = "size", value = "用户数量限制", required = true, dataType = "int")
    })
    public List<Student> listStudents(@RequestParam(required = true) Integer score,
                    @RequestParam(required = true) Integer size) {
        logger.info("listStudents==>enter");

        List<Student> collect = firstService.listAllStudent().stream()
                        .filter(student -> student.score > Integer.valueOf(score)).limit(size)
                        .collect(
                                        Collectors.toList());
        logger.info("collect:{}", gson.toJson(collect));
        return collect;
    }


    @RequestMapping(value = "/listAll", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "请求限流案例")
    public String listAll() {
        double acquire = rateLimiter.acquire(1);
        if (acquire > 0.0) {
            return "请求过快！";
        }
        logger.info("listAll==>enter");
        return "Hello World!";
    }



}
