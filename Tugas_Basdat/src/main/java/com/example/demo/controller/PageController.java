package com.example.demo.controller;

import static org.mockito.Mockito.RETURNS_SMART_NULLS;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.BeasiswaMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.DTOAddSkema;
import com.example.demo.model.DTOAddSkemaAktif;
import com.example.demo.model.DTOAddSkemaAktifSubmit;
import com.example.demo.model.DTOMahasiswaModel;
import com.example.demo.model.DTOPendaftaran;
import com.example.demo.model.DTOSkemaBeasiswa;
import com.example.demo.model.DonaturModel;
import com.example.demo.model.MahasiswaModel;
import com.example.demo.model.PendaftaranModel;
import com.example.demo.model.PenggunaModel;
import com.example.demo.model.PengumumanModel;
import com.example.demo.model.RiwayatAkademik;
import com.example.demo.model.SkemaBeasiswa;
import com.example.demo.model.SkemaBeasiswaAktif;
import com.example.demo.model.SyaratModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PageController {
	 @Autowired
	 UserMapper userDAO;
	 @Autowired
	 BeasiswaMapper beaDAO;
	 public static PenggunaModel userLogin ;
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
	    if (!(auth instanceof AnonymousAuthenticationToken))
	    {
	    	
			if(userDAO.selectDonaturByUsername(name)!=null) {
			       
	        return "redirect:/homeDonat";
			}
	    
	    
			if(userDAO.selectMahasiswaByUsername(name)!=null) {
				System.out.println("masuk mahasiswa ini");
	        return "redirect:/homeMhs";
			}
	    }
		return "login_all";
	}
	/*
	@RequestMapping(value="/login/submit", method =RequestMethod.POST)
	public String loginMhs(PenggunaModel model) {
		if(userDAO.selectPengguna(model.getUsername(), model.getPassword())!=null) {
			return "home_mhs";
	}
		else {
			return "login_all";
		}
	}
	*/
	@RequestMapping("/homeMhs")
	public String homeMhs(Model model, SkemaBeasiswa skemabea, SkemaBeasiswaAktif skemaaktif ) {
		List<SkemaBeasiswa> listSkema = beaDAO.selectAll();
		int nomer=1;
		List<DTOSkemaBeasiswa> listRes = new ArrayList<DTOSkemaBeasiswa>() ;
		
		for(SkemaBeasiswa lst:listSkema) {
			List<SkemaBeasiswaAktif> listAktv = beaDAO.selectAllByKodeSkema(lst.getKode());
			for(SkemaBeasiswaAktif lst2:listAktv) {
				
			DTOSkemaBeasiswa a = new DTOSkemaBeasiswa(nomer,lst.getKode(),lst.getNama(),lst2.getTgl_tutup_pendaftaran(),lst2.getStatus(),lst2.getJumlah_pendaftar());
			listRes.add(a);
			nomer++;
		}
		}
		model.addAttribute("listRes",listRes);
		return "home_mhs";
	}
	@RequestMapping("/homeDonat")
	public String homeDonat(Model model, SkemaBeasiswa skemabea, SkemaBeasiswaAktif skemaaktif ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		String nid = "";
		DonaturModel coba = userDAO.selectDonaturByUsername(name);
		String nama = userDAO.selectDonaturByUsername(name).getNomer_identitas();
		
		List<SkemaBeasiswa> listSkema = beaDAO.selectAll();
		int nomer=1;
		List<DTOSkemaBeasiswa> listRes = new ArrayList<DTOSkemaBeasiswa>() ;
		
		for(SkemaBeasiswa lst:listSkema) {
			List<SkemaBeasiswaAktif> listAktv = beaDAO.selectAllByKodeSkema(lst.getKode());
			for(SkemaBeasiswaAktif lst2:listAktv) {
				
			DTOSkemaBeasiswa a = new DTOSkemaBeasiswa(nomer,lst.getKode(),lst.getNama(),lst2.getTgl_tutup_pendaftaran(),lst2.getStatus(),lst2.getJumlah_pendaftar());
			listRes.add(a);
			nomer++;
		}
		}
		model.addAttribute("listRes",listRes);
		return "home_donatur";
	}
	@RequestMapping("/lihatpengumuman")
	public String lihatpengumuman(Model model) {
		List<PengumumanModel> listPengumuman = beaDAO.selectAllPengumuman();
	//	String nama;
		for(PengumumanModel lst:listPengumuman) {
			if(userDAO.selectDonaturByUsername(lst.getUsername())!=null) {
				String nama = userDAO.selectDonaturByUsername(lst.getUsername()).getNama();
				lst.setUsername(nama);
			}
			if(userDAO.selectMahasiswaByUsername(lst.getUsername())!=null) {
					String nama = userDAO.selectMahasiswaByUsername(lst.getUsername()).getNama();
				lst.setUsername(nama);
			}
			
		}
		model.addAttribute("listPengumumans", listPengumuman);
		return "daftar_pengumuman_bea";
	}
	@RequestMapping("/lihatpengumumandonat")
	public String lihatpengumumanDonat(Model model) {
		List<PengumumanModel> listPengumuman = beaDAO.selectAllPengumuman();
	//	String nama;
		for(PengumumanModel lst:listPengumuman) {
			if(userDAO.selectDonaturByUsername(lst.getUsername())!=null) {
				String nama = userDAO.selectDonaturByUsername(lst.getUsername()).getNama();
				lst.setUsername(nama);
			}
			if(userDAO.selectMahasiswaByUsername(lst.getUsername())!=null) {
					String nama = userDAO.selectMahasiswaByUsername(lst.getUsername()).getNama();
				lst.setUsername(nama);
			}
			
		}
		
		model.addAttribute("listPengumumans", listPengumuman);
		return "daftar_pengumuman_beadonatur";
	}
	
	@RequestMapping("/tambahskema")
	public String tambahskema() {
		return "pendaftaran_paket_bea";
	}
	@RequestMapping("/tambahskemapaket")
	public String tambahskemapaket(Model model) {
		List<String> listkode = beaDAO.selectAllKode();
		model.addAttribute("listkode",listkode);
		return "tambah_bea_daripaket";
			}
	@RequestMapping(value="/tambahskema/submit", method =RequestMethod.POST)
	 public String tambahskemasubmit (DTOAddSkema model ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		String nid = "";
	
			String nama = userDAO.selectDonaturByUsername(name).getNomer_identitas();
			
		
		SkemaBeasiswa skema = new SkemaBeasiswa (model.getKode(),model.getNama(),model.getJenis(),model.getDeskripsi(),nama);
		SyaratModel syarat = new SyaratModel (model.getKode(),model.getSyarat());
		beaDAO.addSkemaBeasiswa(skema);
		beaDAO.addSyarat(syarat);
       return "berhasildaftarskema";
}
	@RequestMapping(value="/tambahskemaaktif/submit", method =RequestMethod.POST)
	 public String tambahskemaaktifsubmit (DTOAddSkemaAktif model ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		String nid = "";
	
			String nama = userDAO.selectDonaturByUsername(name).getNomer_identitas();
			String status = "Aktif";
			String periode_penerimaan = "Genap 2016/2017";
			DTOAddSkemaAktifSubmit addskema = new DTOAddSkemaAktifSubmit(model.getKode_skema_beasiswa(),model.getNo_urut(),model.getTgl_mulai_pendaftaran(),model.getTgl_tutup_pendaftaran(),periode_penerimaan, status);
			beaDAO.addSkemaBeasiswaAktif(addskema);
			return "berhasildaftarskemaaktif";
}

	@RequestMapping("/register/mahasiswa")
	public String registerMahasiswa() {
		return "register_mhs";
	}
	@RequestMapping("/register/donatur")
	public String registerDonatur() {
		return "register_donatur";
	}
	@RequestMapping(value="/register/mahasiswa/submit", method =RequestMethod.POST)
	 public String regisMhsSubmit (DTOMahasiswaModel model , PenggunaModel model2) {
		
    	MahasiswaModel mhs = new MahasiswaModel (model.getNpm(),model.getEmail(),model.getNama(),model.getNo_telp(),model.getAlamat_tinggal(),model.getAlamat_domisili(),model.getNama_bank(),model.getNo_rekening(),model.getNama_pemilik(),model.getUsername(),model.getPassword());
    	char semester='0';
    	char tahun_ajaran='0';	
    	 int jumlah_sks=0;
    	 String lampiran="";
    	RiwayatAkademik rwt = new RiwayatAkademik(model.getNpm(),semester,tahun_ajaran,jumlah_sks,model.getIps(),lampiran);
        userDAO.addpengguna(mhs);
        userDAO.addStudent(mhs);
        beaDAO.addRiwayatPendidikan(rwt);
        return "login_all";
}
	@RequestMapping(value="/register/individuDonatur/submit", method =RequestMethod.POST)
	 public String regisIndiDonaturSubmit (DonaturModel model) {
		System.out.println("TEST");
	DonaturModel donat = new DonaturModel (model.getNik(),model.getNomer_identitas(),model.getEmail(),model.getNama(),model.getNo_telp(),model.getAlamat_tinggal(),model.getNpwp(),model.getUsername(),model.getPassword(),model.getNo_sk_yayasan());
     
       userDAO.addpenggunaDon(donat);
    
       userDAO.addDonatur(donat);
   	System.out.println("TEST");
	
       userDAO.addIndividualDonor(donat);
   	System.out.println("TEST");
	
       return "login_all";
}

	 @RequestMapping("/lihatbeasiswa/{kode}/{no}")
	 public String lihatbeasiswadetail (Model model, @PathVariable(value = "kode") int kode, @PathVariable(value = "no") int no){
	SkemaBeasiswa skema = beaDAO.selectAllByKode(kode);
	
	List<SyaratModel> syarat = beaDAO.selectAllSyaratByKode(kode);
	model.addAttribute("no_urut",no);
	model.addAttribute("skema", skema);
	model.addAttribute("syarat", syarat);
		 return "detail_beasiswamhs";
	 }
	 @RequestMapping("/lihatbeasiswaDonat/{kode}/{no}")
	 public String lihatbeasiswadetailDonat (Model model, @PathVariable(value = "kode") int kode, @PathVariable(value = "no") int no){
	SkemaBeasiswa skema = beaDAO.selectAllByKode(kode);
	
	List<SyaratModel> syarat = beaDAO.selectAllSyaratByKode(kode);
	model.addAttribute("no_urut",no);
	model.addAttribute("skema", skema);
	model.addAttribute("syarat", syarat);
		 return "detail_beasiswa_donatur";
	 }
	 @RequestMapping("/lihatBeasiswaAndaDonat")
		public String lihatBeaAndaDonat(Model model, SkemaBeasiswa skemabea, SkemaBeasiswaAktif skemaaktif ) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			String nid = "";
			DonaturModel coba = userDAO.selectDonaturByUsername(name);
			String nama = userDAO.selectDonaturByUsername(name).getNomer_identitas();
			
			List<SkemaBeasiswa> listSkema = beaDAO.selectAllByDonatur(nama);
			int nomer=1;
			List<DTOSkemaBeasiswa> listRes = new ArrayList<DTOSkemaBeasiswa>() ;
			
			for(SkemaBeasiswa lst:listSkema) {
				List<SkemaBeasiswaAktif> listAktv = beaDAO.selectAllByKodeSkema(lst.getKode());
				for(SkemaBeasiswaAktif lst2:listAktv) {
					
				DTOSkemaBeasiswa a = new DTOSkemaBeasiswa(nomer,lst.getKode(),lst.getNama(),lst2.getTgl_tutup_pendaftaran(),lst2.getStatus(),lst2.getJumlah_pendaftar());
				listRes.add(a);
				nomer++;
			}
			}
			model.addAttribute("listRes",listRes);
			return "list_bea_donatur";
		}
	 @RequestMapping("/penerimaanDonat/{kode}/{no_urut}")
		public String penerimaanDonat(Model model,@PathVariable(value = "kode") int kode,@PathVariable(value = "no_urut") int no_urut) {
		 List<DTOPendaftaran> listRes = new ArrayList<DTOPendaftaran>() ;
				 List<PendaftaranModel> pdf = beaDAO.selectPendaftaranByKodeSkemaNo(kode, no_urut);
		int i =1;
		for(PendaftaranModel listpdf : pdf) {
			String nama = userDAO.selectMahasiswaByNpm(listpdf.getNpm()).getNama();
			DTOPendaftaran a = new DTOPendaftaran(listpdf.getNo_urut(),listpdf.getKode_skema_beasiswa(),nama,listpdf.getNpm(),listpdf.getWaktu_daftar(),listpdf.getStatus_terima(),listpdf.getStatus_daftar());
			listRes.add(a);
			i++;
		}
		model.addAttribute("listRes", listRes);
		return "mhs_mendaftar_bea";
		}
	 @RequestMapping(value="/daftarbea/submit", method =RequestMethod.POST)
	 public String daftarbeasubmit (SkemaBeasiswaAktif model ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		String nid = "";
			String npm = userDAO.selectMahasiswaByUsername(name).getNpm();
			String status_daftar = "Dibuka";
			String status_terima ="";
			String periode_penerimaan = "Genap 2016/2017";
			 Timestamp waktu_daftar = new Timestamp(System.currentTimeMillis());
		 	PendaftaranModel dft = new PendaftaranModel(model.getNo_urut(),model.getKode_skema_beasiswa(),npm,waktu_daftar, status_daftar,status_terima);
			beaDAO.addPendaftaran(dft);
		 	return "berhasildaftarbeasiswa";
}
	 @RequestMapping("/diterima/{kode_skema_beasiswa}/{no_urut}/{npm}")
		public String diterima(Model model,@PathVariable(value = "kode_skema_beasiswa") int kode,@PathVariable(value = "no_urut") int no_urut,@PathVariable(value = "npm") String npm) {
		 
		 beaDAO.addTerima(kode, no_urut, npm);
	 List<DTOPendaftaran> listRes = new ArrayList<DTOPendaftaran>() ;
	 List<PendaftaranModel> pdf = beaDAO.selectPendaftaranByKodeSkemaNo(kode, no_urut);
	 	int i =1;
	 	for(PendaftaranModel listpdf : pdf) {
String nama = userDAO.selectMahasiswaByNpm(listpdf.getNpm()).getNama();
DTOPendaftaran a = new DTOPendaftaran(listpdf.getNo_urut(),listpdf.getKode_skema_beasiswa(),nama,listpdf.getNpm(),listpdf.getWaktu_daftar(),listpdf.getStatus_terima(),listpdf.getStatus_daftar());
listRes.add(a);
i++;
}
model.addAttribute("listRes", listRes);
		 return "redirect:/penerimaanDonat/{kode_skema_beasiswa}/{no_urut}";
		}
	 @RequestMapping("/ditolak/{kode_skema_beasiswa}/{no_urut}/{npm}")
		public String ditolak(Model model,@PathVariable(value = "kode_skema_beasiswa") int kode,@PathVariable(value = "no_urut") int no_urut,@PathVariable(value = "npm") String npm) {
		 
		 beaDAO.addTolak(kode, no_urut, npm);
	 List<DTOPendaftaran> listRes = new ArrayList<DTOPendaftaran>() ;
	 List<PendaftaranModel> pdf = beaDAO.selectPendaftaranByKodeSkemaNo(kode, no_urut);
	 	int i =1;
	 	for(PendaftaranModel listpdf : pdf) {
String nama = userDAO.selectMahasiswaByNpm(listpdf.getNpm()).getNama();
DTOPendaftaran a = new DTOPendaftaran(listpdf.getNo_urut(),listpdf.getKode_skema_beasiswa(),nama,listpdf.getNpm(),listpdf.getWaktu_daftar(),listpdf.getStatus_terima(),listpdf.getStatus_daftar());
listRes.add(a);
i++;
}
model.addAttribute("listRes", listRes);
		 return "redirect:/penerimaanDonat/{kode_skema_beasiswa}/{no_urut}";
		}
}
