package com.example.demo.model;

import java.sql.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOSkemaBeasiswa {

	private int no;
	private int kode;
	private String nama;
	private Date waktu_tutup;
	private String status;
	private int jumlah_daftar;
	
}
