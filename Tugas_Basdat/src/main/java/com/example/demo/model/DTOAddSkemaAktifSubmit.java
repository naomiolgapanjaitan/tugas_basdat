package com.example.demo.model;

import java.sql.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOAddSkemaAktifSubmit {

	private int kode_skema_beasiswa;
	private int no_urut;
	private Date tgl_mulai_pendaftaran;
	private Date  tgl_tutup_pendaftaran;
	private String periode_penerimaan;
	private String status;
	
	
}
