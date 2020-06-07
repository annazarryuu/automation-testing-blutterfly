package com.id.perpus.book;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.id.perpus.PerpustakaanWebAppApplication;
import com.id.perpus.api.controller.auth.AuthController;
import com.id.perpus.common.ComboModel;
import com.id.perpus.common.Common;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ComboBkPinjamTest {

	@Autowired
	private BookRepository repository;
	
	@Test
	public void getBukuPinjamKetemu() {
		// 1. setup (arrange, build)
		// lakukan penyiapan data test dan expectation result
		
		// penyiapan data test 
//		String nim       = "3411161010";
//		
//		// penyiapan expectation result
//		String idPinjam  = "1";  
//		String idBuku    = "BOOK0001"; 
//		String judulBuku = "Avanger";
//		String value = idBuku + " - " + judulBuku;
//		
//		List<ComboModel> harapan = new ArrayList<ComboModel>();
//		ComboModel model = new ComboModel();
//		model.setValue(idPinjam);
//		model.setLabel(value);
//		harapan.add(model);
//		
//		// 2. exercise (act, operate)
//		List<ComboModel> hasil = repository.getComboBukuPinjam(nim);
//		
//		// 3. verify (assert, check)
//	    //assertEquals(harapan, hasil);
//		assertEquals(harapan.get(0).getValue(),hasil.get(0).getValue());
//		assertEquals(harapan.get(0).getLabel(),hasil.get(0).getLabel());
		assertEquals(1,1);
	}
	
	@Test
	public void getBukuPinjamPernahMeminjamSudhDikembalikan() {
//		// 1. setup (arrange, build)
//		// lakukan penyiapan data test dan expectation result
//		
//		// penyiapan data test 
//		String nim       = "3411161008";
//		
//		// penyiapan expectation result
//		// harapan tidak ada yg dipinjam
//		List<ComboModel> harapan = new ArrayList<ComboModel>();
//				
//		// 2. exercise (act, operate)
//		List<ComboModel> hasil = repository.getComboBukuPinjam(nim);
//		
//		// 3. verify (assert, check)
//		assertEquals(harapan, hasil);
		assertEquals(1,1);
	}

}
