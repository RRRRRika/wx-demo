package com.rika.wxspringbootbackend.mapper;

import com.rika.wxspringbootbackend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Insert("insert into wx_user (uid) values (#{user.uid})")
    public void addNormalUser(@Param("user") User user);

    @Select("select uid, name, role from wx_user where uid = #{uid}")
    public User getUser(@Param("uid") String uid);
}
