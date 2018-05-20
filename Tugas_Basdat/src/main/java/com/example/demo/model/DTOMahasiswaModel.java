package com.example.demo.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOMahasiswaModel {
	private String npm;
	private String email;
	private String nama;
	private String no_telp;	
	private String alamat_tinggal;
	private String alamat_domisili;
	private String nama_bank;
	private String no_rekening;
	private String nama_pemilik;
	private String username;
	private String password;
	private Double ips;
	}
