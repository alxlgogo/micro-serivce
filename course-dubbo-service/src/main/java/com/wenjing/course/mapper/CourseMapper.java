package com.wenjing.course.mapper;

import com.wenjing.course.dto.CourseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("select * from pe_course")
    List<CourseDTO> listCourse();

    @Select("select user_id from pe_user_course where course_id = #{courseId}")
    Integer getCourseTeacher(@Param("courseId") int courseId);

}
