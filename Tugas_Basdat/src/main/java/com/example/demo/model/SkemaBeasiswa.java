package com.example.demo.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkemaBeasiswa {

	private int kode;
	private String nama;
	private String jenis;
	private String deskripsi;
	private String nomer_identitas_donatur;
	
}
