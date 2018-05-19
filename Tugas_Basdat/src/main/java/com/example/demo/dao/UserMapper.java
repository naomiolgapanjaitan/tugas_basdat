package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.DonaturModel;
import com.example.demo.model.MahasiswaModel;
import com.example.demo.model.PenggunaModel;
@Mapper
public interface UserMapper {
	@Select("select * from pengguna where username = #{username} and password =#{password}")
	PenggunaModel selectPengguna (@Param("username") String username, @Param("password") String password);
	@Select("select * from donatur where username = #{username}")
	DonaturModel selectDonaturByUsername (@Param("username") String username);
	@Select("select * from mahasiswa where username = #{username}")
	MahasiswaModel selectMahasiswaByUsername (@Param("username") String username);
	@Select("select * from mahasiswa where npm = #{npm}")
	MahasiswaModel selectMahasiswaByNpm (@Param("npm") String npm);

    @Insert("INSERT INTO mahasiswa (npm, email, nama , no_telp, alamat_tinggal, alamat_domisili"
    		+", nama_bank,no_rekening, nama_pemilik, username)"
    		+"VALUES (#{npm}, #{email}, #{nama}, #{no_telp}, #{alamat_tinggal}, #{alamat_domisili},"
    		+ " #{nama_bank}, #{no_rekening},  #{nama_pemilik}, #{username}) ")
    void addStudent (MahasiswaModel mhs);
    @Insert( "insert into pengguna (username,password) values (#{username},#{password})")
    void addpengguna (MahasiswaModel mhs);
    @Insert( "insert into pengguna (username,password) values (#{username},#{password})")
    void addpenggunaDon (DonaturModel mhs);
    @Insert("INSERT INTO donatur (nomer_identitas, email, nama , npwp,no_telp, alamat,username)"
    		+"VALUES (#{no_identitas}, #{email}, #{nama}, #{npwp},#{no_telp}, #{alamat}, #{username})")
    void addDonatur (DonaturModel donat);
    @Insert( "insert into pengguna (nik,nomor_identitas_donor) values (#{nik},#{no_identitas})")
    void addIndividualDonor (DonaturModel mhs);
   
}
