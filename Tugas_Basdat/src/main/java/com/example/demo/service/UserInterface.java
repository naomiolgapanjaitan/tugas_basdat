package com.example.demo.service;

import com.example.demo.model.DonaturModel;
import com.example.demo.model.MahasiswaModel;
import com.example.demo.model.PenggunaModel;

public interface UserInterface {
	PenggunaModel valid(String username, String password);
	void addMahasiswa(MahasiswaModel mhs);
	void addPengguna(MahasiswaModel mhs);
	void addDonatur(DonaturModel donat);
	DonaturModel selectDonaturByUsername(String username);
	MahasiswaModel selectMahasiswaByUsername(String username);
	MahasiswaModel selectMahasiswaByNpm(String npm);

}
