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

    @Select("select id,user_name as userName,password,real_name as realName,mobile,email from pe_user where user_name=#{username}")
    UserInfo getUserByName(@Param("username") String username);

    @Insert("insert into pe_user (user_name,password,real_name,mobile,email) values (u.username,u.password,u.realname,u.mobile,u.email)")
    void registerUser(@Param("u") UserInfo userInfo);

}
