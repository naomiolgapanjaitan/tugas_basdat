package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BeasiswaMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.DTOAddSkemaAktifSubmit;
import com.example.demo.model.DonaturModel;
import com.example.demo.model.MahasiswaModel;
import com.example.demo.model.PendaftaranModel;
import com.example.demo.model.PenggunaModel;
import com.example.demo.model.PengumumanModel;
import com.example.demo.model.RiwayatAkademik;
import com.example.demo.model.SkemaBeasiswa;
import com.example.demo.model.SkemaBeasiswaAktif;
import com.example.demo.model.SyaratModel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service

public class BeasiswaImpl implements BeasiswaInterface {
	@Autowired
	private BeasiswaMapper beaDAO;
	@Override
	public List<SkemaBeasiswa> selectAll() {
		List<SkemaBeasiswa> selectAll = beaDAO.selectAll(); 
		return selectAll;
	}
	@Override
	public List<String> selectAllKode() {
		List<String> selectAll = beaDAO.selectAllKode(); 
		return selectAll;
	}
	@Override
	public SkemaBeasiswa selectAllByKode(int kode) {
	SkemaBeasiswa skema = beaDAO.selectAllByKode(kode); 
		return skema;
	}
	@Override
	public List<SkemaBeasiswaAktif> selectAllByKodeSkema(int kode) {
		List<SkemaBeasiswaAktif> selectAllByKode = beaDAO.selectAllByKodeSkema(kode); 
		return selectAllByKode;
	}
	@Override
	public List<SkemaBeasiswaAktif> selectAllByKodeSkemaNo(int kode, int no) {
		List<SkemaBeasiswaAktif> selectAllByKodeNo = beaDAO.selectAllByKodeSkemaNo(kode,no); 
		return selectAllByKodeNo;
	}
	@Override
	public List<PendaftaranModel> selectPendaftaranByKodeSkemaNo(int kode, int no) {
		List<PendaftaranModel> selectAllByKodeNo = beaDAO.selectPendaftaranByKodeSkemaNo(kode,no); 
		return selectAllByKodeNo;
	}
	@Override
	public List<SkemaBeasiswa> selectAllByDonatur(String nomer) {
		List<SkemaBeasiswa> selectAllByDonatur = beaDAO.selectAllByDonatur(nomer); 
		return selectAllByDonatur;
	}
	@Override
	public List<PengumumanModel> selectAllPengumuman() {
		List<PengumumanModel> selectAllPengumuman = beaDAO.selectAllPengumuman(); 
		return selectAllPengumuman;
	}
	@Override
	public void addSkemaBeasiswa(SkemaBeasiswa skema) {
			 beaDAO.addSkemaBeasiswa(skema);
	}
	@Override
	public void addSkemaBeasiswaAktif(DTOAddSkemaAktifSubmit skema) {
			 beaDAO.addSkemaBeasiswaAktif(skema);
	}
	@Override
	public void addPendaftaran(PendaftaranModel pendaftaran) {
			 beaDAO.addPendaftaran(pendaftaran);
	}
	@Override
	public void addTerima(int kode, int no , String npm) {
			 beaDAO.addTerima(kode,no,npm);
	}
	@Override
	public void addTolak(int kode, int no , String npm) {
			 beaDAO.addTolak(kode,no,npm);
	}
	@Override
	public List<SyaratModel> selectAllSyaratByKode(int kode) {
		List<SyaratModel> selectAllPengumuman = beaDAO.selectAllSyaratByKode(kode);
		return selectAllPengumuman;
	}
	public void addSyarat(SyaratModel syarat) {
		 beaDAO.addSyarat(syarat);
	}
	public void addRiwayatAkademik(RiwayatAkademik riwayat) {
		 beaDAO.addRiwayatPendidikan(riwayat);;
}

}
