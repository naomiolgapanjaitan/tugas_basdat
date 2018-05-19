package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.DTOAddSkemaAktifSubmit;
import com.example.demo.model.DonaturModel;
import com.example.demo.model.MahasiswaModel;
import com.example.demo.model.PendaftaranModel;
import com.example.demo.model.PenggunaModel;
import com.example.demo.model.PengumumanModel;
import com.example.demo.model.SkemaBeasiswa;
import com.example.demo.model.SkemaBeasiswaAktif;
import com.example.demo.model.SyaratModel;
@Mapper
public interface BeasiswaMapper {
	@Select("select * from skema_beasiswa")
	List<SkemaBeasiswa> selectAll();
	@Select("select * from skema_beasiswa_aktif where kode_skema_beasiswa=#{kode} ")
	List<SkemaBeasiswaAktif> selectAllByKodeSkema(int kode);
	@Select("select * from skema_beasiswa where nomer_identitas_donatur=#{nomer_identitas_donatur} ")
	List<SkemaBeasiswa> selectAllByDonatur(String nomer_identitas_donatur);
	@Select("select * from skema_beasiswa_aktif where kode_skema_beasiswa=#{kode} and no_urut=#{no}")
	List<SkemaBeasiswaAktif> selectAllByKodeSkemaNo(int kode, int no);
	
	@Select("select * from pendaftaran where kode_skema_beasiswa=#{kode_skema_beasiswa} and no_urut=#{no_urut} order by "
			+ "waktu_daftar asc")
	List<PendaftaranModel> selectPendaftaranByKodeSkemaNo(@Param("kode_skema_beasiswa") int kode_skema_beasiswa, @Param("no_urut") int no_urut);
	
	@Select("select * from skema_beasiswa where kode=#{kode} ")
	SkemaBeasiswa selectAllByKode(int kode);
	@Select("select distinct kode from skema_beasiswa ")
	List<String> selectAllKode();
	
	@Select("select * from syarat_beasiswa where kode_beasiswa=#{kode} ")
	List<SyaratModel> selectAllSyaratByKode(int kode);
	
	@Select("select * from pengumuman")
	List <PengumumanModel> selectAllPengumuman();
	@Insert( "insert into skema_beasiswa (kode,nama,jenis,deskripsi,nomer_identitas_donatur)"
			+ " values (#{kode},#{nama},#{jenis},#{deskripsi},#{nomer_identitas_donatur})")
    void addSkemaBeasiswa (SkemaBeasiswa skema);
    @Insert("insert into syarat_beasiswa (kode_beasiswa,syarat) values (#{kode_beasiswa},#{syarat})")
    void addSyarat (SyaratModel syarat);
    @Insert("insert into skema_beasiswa_aktif (kode_skema_beasiswa, no_urut, tgl_mulai_pendaftaran,"
    		+ "tgl_tutup_pendaftaran,periode_penerimaan,status) "
    		+ "values (#{kode_skema_beasiswa},#{no_urut},#{tgl_mulai_pendaftaran},"
    		+ "#{tgl_tutup_pendaftaran},#{periode_penerimaan},#{status})")
    void addSkemaBeasiswaAktif (DTOAddSkemaAktifSubmit skemaaktif);
    
    @Insert("insert into pendaftaran (no_urut,kode_skema_beasiswa,npm,waktu_daftar,status_daftar,status_terima)"
    		+ "values (#{status_terima},#{kode_skema_beasiswa},#{npm},"
    		+ "#{waktu_daftar},#{status_daftar},#{status_terima})")
    void addPendaftaran(PendaftaranModel pendaftaran);
}
