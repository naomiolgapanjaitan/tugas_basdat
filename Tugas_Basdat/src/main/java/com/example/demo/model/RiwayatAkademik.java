package com.example.demo.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiwayatAkademik {
	private String npm;
	private char semester;
	private char tahun_ajaran;	
	private int jumlah_sks;
	private Double  ips;
	private String lampiran;
		}
