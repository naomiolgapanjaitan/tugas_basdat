package com.example.demo.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOPendaftaran {

	private int no;
	private int kode_skema_beasiswa;
	private String nama;
	private String npm;
	private Timestamp waktu_daftar;
	private String status_terima;
	private String status_daftar;
}
