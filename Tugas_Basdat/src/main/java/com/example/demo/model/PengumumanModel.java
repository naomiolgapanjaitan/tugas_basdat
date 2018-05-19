package com.example.demo.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PengumumanModel {
 private Date tanggal;
 private int no_urut_skema_beasiswa_aktif;
 private int kode_skema_beasiswa;
 private String username;
 private String judul;
 private String isi;
}
