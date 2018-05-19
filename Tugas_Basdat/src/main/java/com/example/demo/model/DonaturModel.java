package com.example.demo.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonaturModel {
	private String nomer_identitas;
	private String email;
	private String nama;
	private String no_telp;	
	private String alamat_tinggal;
	private String username;
	private String password;
	private String nik;
	private String npwp;
	private String no_sk_yayasan;
	}
