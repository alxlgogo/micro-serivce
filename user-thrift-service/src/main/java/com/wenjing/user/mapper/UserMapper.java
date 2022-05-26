package com.wenjing.user.mapper;

import com.wenjing.thrift.user.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select id,user_name as userName,password,real_name as realName,mobile,email from pe_user where id=#{id}")
    UserInfo getUserById(@Param("id") int id);

    @Select("select u.id,u.user_name as userName,u.password,u.real_name as realName,u.mobile,u.email,t.intro,t.stars " +
            "from db_user.pe_user as u,db_user.pe_teacher as t " +
            "where u.id=#{id} and u.id=t.user_id;")
    UserInfo getTeacherById(@Param("id") int id);

    @Select("select id,user_name as userName,password,real_name as realName,mobile,email from pe_user where user_name=#{username}")
    UserInfo getUserByName(@Param("username") String username);

    @Insert("insert into pe_user (user_name,password,real_name,mobile,email) values (#{u.username},#{u.password},#{u.realname},#{u.mobile},#{u.email})")
    void registerUser(@Param("u") UserInfo userInfo);

}
