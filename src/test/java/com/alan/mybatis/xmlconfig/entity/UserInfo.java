package com.alan.mybatis.xmlconfig.entity;

import java.time.LocalDate;

/**
 * @author Alan Yin
 * @date 2021/1/25
 */

public class UserInfo {

  private Long id;

  private String name;

  private LocalDate birthday;

  private String deleteFlag;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public String getDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(String deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
}
