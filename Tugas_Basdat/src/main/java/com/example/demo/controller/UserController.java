package com.example.demo.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.PenggunaModel;
@Mapper
public class UserController {
	 @Autowired
	 UserMapper userDAO;
	
}
