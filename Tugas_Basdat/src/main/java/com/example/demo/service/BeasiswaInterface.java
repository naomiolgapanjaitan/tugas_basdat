package com.example.demo.service;

import java.util.List;

import com.example.demo.model.DTOAddSkemaAktifSubmit;
import com.example.demo.model.MahasiswaModel;
import com.example.demo.model.PendaftaranModel;
import com.example.demo.model.PenggunaModel;
import com.example.demo.model.PengumumanModel;
import com.example.demo.model.SkemaBeasiswa;
import com.example.demo.model.SkemaBeasiswaAktif;
import com.example.demo.model.SyaratModel;

public interface BeasiswaInterface {
	void addSkemaBeasiswa(SkemaBeasiswa skemaBea);
	void addSyarat(SyaratModel syarat);
	void addSkemaBeasiswaAktif(DTOAddSkemaAktifSubmit skemaBeaAktif);
	void addPendaftaran(PendaftaranModel pendaftaran);
	
	List<String> selectAllKode();
	List<SkemaBeasiswa> selectAllByDonatur(String nomer_identitas_donatur);
	List<SkemaBeasiswa> selectAll();
	SkemaBeasiswa selectAllByKode(int kode);
	List<SkemaBeasiswaAktif> selectAllByKodeSkema(int kode);
	List<SkemaBeasiswaAktif> selectAllByKodeSkemaNo(int kode,int no);
	List<PendaftaranModel> selectPendaftaranByKodeSkemaNo(int kode,int no);
	
	List<PengumumanModel> selectAllPengumuman();
	List<SyaratModel> selectAllSyaratByKode(int kode);
	
}
