package com.wenjing.course.controller;

import com.wenjing.course.dto.CourseDTO;
import com.wenjing.course.service.ICourseService;
import com.wenjing.thrift.user.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/course")
@ResponseBody
public class CourseController {
    @DubboReference
    private ICourseService iCourseService;

    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    public List<CourseDTO> courseList(HttpServletRequest request) {
        UserDTO userDTO = (UserDTO) request.getAttribute("user");
        System.out.println(userDTO.toString());
        return iCourseService.courserList();
    }
}
