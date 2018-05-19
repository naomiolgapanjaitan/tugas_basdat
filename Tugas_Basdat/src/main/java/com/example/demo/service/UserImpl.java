package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.DonaturModel;
import com.example.demo.model.MahasiswaModel;
import com.example.demo.model.PenggunaModel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service

public class UserImpl implements UserInterface {
	@Autowired
	private UserMapper userDAO;
	@Override
	    public void addMahasiswa(MahasiswaModel mhs)
	    {
	       log.info ("Register Mahasiswa");
	       userDAO.addStudent(mhs);
	    }
	@Override
    public void addPengguna(MahasiswaModel mhs)
    {
       log.info ("Register pengguna");
       userDAO.addStudent(mhs);
    }
	@Override
    public void addDonatur(DonaturModel donat)
    {
       log.info ("Register Donatur");
       userDAO.addDonatur(donat);
    }
	public PenggunaModel valid(String username ,  String password)
    {
      return userDAO.selectPengguna(username,password);
    }
	public DonaturModel selectDonaturByUsername(String username)
    {
      return userDAO.selectDonaturByUsername(username);
    }
	public MahasiswaModel selectMahasiswaByUsername(String username)
    {
      return userDAO.selectMahasiswaByUsername(username);
    }
	public MahasiswaModel selectMahasiswaByNpm(String npm)
    {
      return userDAO.selectMahasiswaByNpm(npm);
    }

}
