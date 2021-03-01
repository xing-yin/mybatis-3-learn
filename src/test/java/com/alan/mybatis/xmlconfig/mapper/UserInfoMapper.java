package com.alan.mybatis.xmlconfig.mapper;

import com.alan.mybatis.xmlconfig.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alan Yin
 * @date 2021/1/25
 */
//@Mapper
public interface UserInfoMapper {

  UserInfo getSingleUserInfo(@Param("id") Long id);

  List<UserInfo> getAllUserInfo();

  List<UserInfo> getUserInfoByDate(@Param("localDate") LocalDate date);

  List<UserInfo> getUserInfoByLessDate(@Param("localDate") LocalDate date);

  Long addUser(UserInfo userInfo);

}
