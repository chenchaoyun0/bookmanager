package com.sttx.bookmanager.dao;

import com.sttx.bookmanager.po.Student;

import tk.mybatis.mapper.common.Mapper;

public interface StudentMapper extends Mapper<Student>{

  public long insertPo(Student student);
}