package com.example.demo.model;

import java.sql.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOAddSkema {

	private int kode;
	private String nama;
	private String status;
	private String jenis;
	private String deskripsi;
	private String nomer_identitas_donatur;
	private String syarat;
}
