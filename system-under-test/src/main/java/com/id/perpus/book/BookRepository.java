package com.id.perpus.book;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.id.perpus.common.ComboModel;
import com.id.perpus.common.Common;
import com.id.perpus.common.Constanta;

@Repository
public class BookRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<BookModel> doSearch(BookModel dto) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" b.book_cd,  ")
			 .append(" b.title,  ")
			 .append(" b.author,  ")
			 .append(" b.subject_1,  ")
			 .append(" b.subject_2,  ")
			 .append(" b.book_cover,  ")
			 .append(" b.book_summary,  ")
			 .append(" b.publisher,  ")
		     .append(" b.publication_year,  ")
		     .append(" b.place_publication,  ")
		     .append(" b.number_page,  ")
		     .append(" b.stock, ")
		     .append(" b.stock - ((select ifnull(sum(mb.number_book),0) from tb_r_missing_book mb where mb.book_cd=b.book_cd) + ")  
             .append(" (select count(l.loan_no) from tb_r_loan l where l.book_cd=b.book_cd)) as avaliable,")
		     .append(" b.location, ")
		     .append(" b.category, ")
		     .append(" (select s.sys_val from tb_m_system s where s.sys_cat='CATALOG_BOOK' AND s.sys_sub_cat='CATEGORY' AND s.sys_cd=b.category) as category_name,")
		     .append(" b.created_by,  ")
			 .append(" b.created_dt,  ")
			 .append(" b.updated_by,  ")
			 .append(" b.updated_dt  ")
			 .append(" FROM tb_m_book b  ")
		     .append(" WHERE 1=1 ");
		if(dto.getTitle() != null && !dto.getTitle().isEmpty()){
			query.append(" AND b.title LIKE '%"+dto.getTitle()+"%'");
		}
		if(dto.getAuthor() != null && !dto.getAuthor().isEmpty()){
			query.append(" AND b.author LIKE '%"+dto.getAuthor()+"%'");
		}
		if(dto.getPublisher() != null && !dto.getPublisher().isEmpty()){
			query.append(" AND b.publisher LIKE '%"+dto.getPublisher()+"%'");
		}
		if(dto.getPublishYear() != null && !dto.getPublishYear().isEmpty()){
			query.append(" AND b.publication_year = '"+dto.getPublishYear()+"'");
		}
		if(dto.getCategory() != null && !dto.getCategory().isEmpty()){
			query.append(" AND b.category = '"+dto.getCategory()+"'");
		}
		if(dto.getSubject1() != null && !dto.getSubject1().isEmpty()){
			query.append(" AND ( b.subject_1 LIKE '%"+dto.getSubject1()+"%' OR b.subject_2 LIKE '%"+dto.getSubject1()+"%' ) ");
		}
		
		query.append(" ORDER  BY b.title ASC LIMIT ? OFFSET ?");
		List<BookModel> books = new ArrayList<BookModel>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(),dto.getLimit(), dto.getOffset());
		for (Map<String, Object> row : rows) {
			System.out.println("row: ");
			System.out.println(row);
			BookModel book = new BookModel();
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			book.setCreatedBy(Common.toString(row.get("created_by")));
			book.setCreatedDt(Constanta.sdf.format(createdDate));
			book.setUpdatedBy(Common.toString(row.get("updated_by")));
			book.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			book.setBookCode(Common.toString(row.get("book_cd")));
			book.setTitle(Common.toString(row.get("title")));
			book.setAuthor(Common.toString(row.get("author")));
			book.setSubject1(Common.toString(row.get("subject_1")));
			book.setSubject2(Common.toString(row.get("subject_2")));
			book.setBookCover(Common.toString(row.get("book_cover")));
			book.setBookSummary(Common.toString(row.get("book_summary")));
			book.setPublisher(Common.toString(row.get("publisher")));
			book.setPublishYearDate((Date) row.get("publication_year"));
			book.setPublishYear(Common.formatDateYearOnly(book.getPublishYearDate()));
			book.setLocation(Common.toString(row.get("location")));
			book.setNumberPage((Integer)row.get("number_page"));
			book.setStock((Integer) row.get("stock"));
			book.setCategory(Common.toString(row.get("category")));
			book.setCategoryName(Common.toString(row.get("category_name")));
			book.setAvaliable(((BigDecimal) row.get("avaliable")).intValue());
			
			books.add(book);
		}
		
		return books;
	}
	
	@SuppressWarnings("rawtypes")
	public Long doCount(BookModel dto) throws Exception{
		Long count = (long)0;
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(*) COUNT FROM tb_m_book b WHERE 1=1");
		if(dto.getTitle() != null && !dto.getTitle().isEmpty()){
			query.append(" AND b.title LIKE '%"+dto.getTitle()+"%'");
		}
		if(dto.getAuthor() != null && !dto.getAuthor().isEmpty()){
			query.append(" AND b.author LIKE '%"+dto.getAuthor()+"%'");
		}
		if(dto.getPublisher() != null && !dto.getPublisher().isEmpty()){
			query.append(" AND b.publisher LIKE '%"+dto.getPublisher()+"%'");
		}
		if(dto.getPublishYear() != null && !dto.getPublishYear().isEmpty()){
			query.append(" AND b.publication_year = '"+dto.getPublishYear()+"'");
		}
		if(dto.getCategory() != null && !dto.getCategory().isEmpty()){
			query.append(" AND b.category = '"+dto.getCategory()+"'");
		}
		if(dto.getSubject1() != null && !dto.getSubject1().isEmpty()){
			query.append(" AND ( b.subject_1 LIKE '%"+dto.getSubject1()+"%' OR b.subject_2 LIKE '%"+dto.getSubject1()+"%' ) ");
		}
		
	    List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString());
		for (Map row : rows) {
			count = ((Long)row.get("COUNT"));	
		}
		return count;
	}
	
	public BookModel getBookByCd(String bookCd) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" b.book_cd,  ")
			 .append(" b.title,  ")
			 .append(" b.author,  ")
			 .append(" b.subject_1,  ")
			 .append(" b.subject_2,  ")
			 .append(" b.book_cover,  ")
			 .append(" b.book_summary,  ")
			 .append(" b.publisher,  ")
		     .append(" b.publication_year,  ")
		     .append(" b.place_publication,  ")
		     .append(" b.number_page,  ")
		     .append(" b.stock, ")
		     .append(" b.stock - ((select ifnull(sum(mb.number_book),0) from tb_r_missing_book mb where mb.book_cd=b.book_cd) + ")  
             .append(" (select count(l.loan_no) from tb_r_loan l where l.book_cd=b.book_cd)) as avaliable,")
		     .append(" b.location, ")
		     .append(" b.category, ")
		     .append(" (select s.sys_val from tb_m_system s where s.sys_cat='CATALOG_BOOK' AND s.sys_sub_cat='CATEGORY' AND s.sys_cd=b.category) as category_name,")
		     .append(" ifnull((select sum(mb.number_book) from tb_r_missing_book mb where mb.book_cd = b.book_cd ),0) as lost, ")
		     .append(" (select count(l.loan_no) from tb_r_loan l where l.book_cd = b.book_cd and l.loan_status ='BL1' ) as borrowed, ")
		     .append(" b.created_by,  ")
			 .append(" b.created_dt,  ")
			 .append(" b.updated_by,  ")
			 .append(" b.updated_dt  ")
			 .append(" FROM tb_m_book b  ")
		     .append(" WHERE b.book_cd = ? ");
		
		BookModel book = null;

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(),bookCd);
		for (Map<String, Object> row : rows) {
			book = new BookModel();
			System.out.println("row: ");
			System.out.println(row);
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			book.setCreatedBy(Common.toString(row.get("created_by")));
			book.setCreatedDt(Constanta.sdf.format(createdDate));
			book.setUpdatedBy(Common.toString(row.get("updated_by")));
			book.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			book.setBookCode(Common.toString(row.get("book_cd")));
			book.setTitle(Common.toString(row.get("title")));
			book.setAuthor(Common.toString(row.get("author")));
			book.setSubject1(Common.toString(row.get("subject_1")));
			book.setSubject2(Common.toString(row.get("subject_2")));
			book.setBookCover(Common.toString(row.get("book_cover")));
			book.setBookSummary(Common.toString(row.get("book_summary")));
			book.setPublisher(Common.toString(row.get("publisher")));
			book.setPlacePublication(Common.toString(row.get("place_publication")));
			book.setPublishYearDate((Date) row.get("publication_year"));
			book.setPublishYear(Common.formatDateYearOnly(book.getPublishYearDate()));
			book.setLocation(Common.toString(row.get("location")));
			book.setNumberPage((Integer)row.get("number_page"));
			book.setStock((Integer) row.get("stock"));
			book.setCategory(Common.toString(row.get("category")));
			book.setCategoryName(Common.toString(row.get("category_name")));
			book.setAvaliable(((BigDecimal) row.get("avaliable")).intValue());
			book.setLost(((BigDecimal) row.get("lost")).intValue());
			book.setBorrowed(((Long) row.get("borrowed")).intValue());
		}
		
		return book;
	}
	
	public List<ComboModel> getCombo(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id_buku as value, CONCAT(id_buku, ' - ', judul_buku) AS label FROM tb_buku ");
		List<ComboModel> dtos = new ArrayList<ComboModel>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString());
		for (Map<String, Object> row : rows) {
			ComboModel model = new ComboModel();
			model.setValue(Common.toString(row.get("value")));
			model.setLabel(Common.toString(row.get("label")));
			dtos.add(model);
		}
		
		return dtos;
	}
	
	public List<TransaksiModel> getDataPinjam(String nim){
		StringBuilder sb = new StringBuilder();
		sb.append("  SELECT p.id_pinjam  ")
		.append("       , p.nim  ")
		.append("       , p.id_buku  ")
		.append("       , (SELECT b.judul_buku  ")
		.append("          FROM  ")
		.append("            tb_buku b  ")
		.append("          WHERE  ")
		.append("            b.id_buku = p.id_buku) judul_buku  ")
		.append("       , p.tgl_pinjam  ")
		.append("       , adddate(p.tgl_pinjam, p.lama_pinjam) jatuh_tempo  ")
		.append("       , if ((datediff(p.tgl_kembali, p.tgl_pinjam) - p.lama_pinjam) > 0,  ")
		.append("              (datediff(p.tgl_kembali, p.tgl_pinjam) - p.lama_pinjam) * 2500,  ")
		.append("              0) as denda  ")
		.append("       , if (p.tgl_kembali is null ,  ")
		.append("              'Belum dikembalikan',  ")
		.append("              'Sudah dikembalikan') as status  ")
		.append("  FROM   tb_pinjam p  ")
		.append("  WHERE p.nim = ? ")
		.append("    AND p.tgl_kembali is null ");
		List<TransaksiModel> dtos = new ArrayList<TransaksiModel>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString(),nim);
		for (Map<String, Object> row : rows) {
			TransaksiModel model = new TransaksiModel();
			model.setIdPinjam(Common.toString(row.get("id_pinjam")));
			model.setNim(Common.toString(row.get("nim")));
			model.setIdBuku(Common.toString(row.get("id_buku")));
			model.setJudulBuku(Common.toString(row.get("judul_buku")));
			model.setJatuhTempo(Constanta.sdf.format((Date)row.get("jatuh_tempo")));
			model.setTglPinjam(Constanta.sdf.format((Date)row.get("tgl_pinjam")));
			model.setDenda(Common.toString(row.get("denda")));
			model.setStatus(Common.toString(row.get("status")));
			
			dtos.add(model);
		}
		
		return dtos;
	}
	
	public List<TransaksiModel> getDenda(String idPinjam){
		StringBuilder sb = new StringBuilder();
		sb.append("  SELECT p.id_pinjam  ")
		.append("       , p.nim  ")
		.append("       , p.id_buku  ")
		.append("       , (SELECT b.judul_buku  ")
		.append("          FROM  ")
		.append("            tb_buku b  ")
		.append("          WHERE  ")
		.append("            b.id_buku = p.id_buku) judul_buku  ")
		.append("       , p.tgl_pinjam  ")
		.append("       , adddate(p.tgl_pinjam, p.lama_pinjam) jatuh_tempo  ")
		.append("       , if ((datediff(p.tgl_kembali, p.tgl_pinjam) - p.lama_pinjam) > 0,  ")
		.append("              (datediff(p.tgl_kembali, p.tgl_pinjam) - p.lama_pinjam) * 2500,  ")
		.append("              0) as denda  ")
		.append("       , if (p.tgl_kembali is null ,  ")
		.append("              'Belum dikembalikan',  ")
		.append("              'Sudah dikembalikan') as status  ")
		.append("  FROM   tb_pinjam p  ")
		.append("  WHERE p.id_pinjam = ? ");
		List<TransaksiModel> dtos = new ArrayList<TransaksiModel>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString(), idPinjam);
		for (Map<String, Object> row : rows) {
			TransaksiModel model = new TransaksiModel();
			model.setIdPinjam(Common.toString(row.get("id_pinjam")));
			model.setNim(Common.toString(row.get("nim")));
			model.setIdBuku(Common.toString(row.get("id_buku")));
			model.setJudulBuku(Common.toString(row.get("judul_buku")));
			model.setJatuhTempo(Constanta.sdf.format((Date)row.get("jatuh_tempo")));
			model.setTglPinjam(Constanta.sdf.format((Date)row.get("tgl_pinjam")));
			model.setDenda(Common.toString(row.get("denda")));
			model.setStatus(Common.toString(row.get("status")));
			
			dtos.add(model);
		}
		
		return dtos;
	}
	
	public int doPinjam(TransaksiModel dto) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO tb_pinjam ( ")
		     .append(" NIM, ")
		     .append(" id_buku, ")
		     .append(" tgl_pinjam, ")
		     .append(" lama_pinjam ")
		     .append(" ) VALUES ( ")
		     .append(" ?, ")// 1
		     .append(" ?, ")// 2
		     .append(" ?, ")// 3 
		     .append(" ? ) ");// 4
	
		int row = jdbcTemplate.update(query.toString(),
				dto.getNim(), // 1
				dto.getIdBuku(),// 2
				dto.getTglPinjam(),
				dto.getLamaPinjam());// 4
		return row;
	}
	
	public int doKembali(TransaksiModel dto) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE tb_pinjam SET ")
		     .append(" tgl_kembali = ? ")
		     .append(" WHERE id_pinjam = ? ");// 4
	
		int row = jdbcTemplate.update(query.toString(),
				dto.getTglKembali(),
				dto.getIdPinjam());// 3
		return row;
	}

	public List<ComboModel> getComboBukuPinjam(String nim) {
		StringBuilder sb = new StringBuilder();
		sb.append("  SELECT p.id_pinjam  as value")
		.append("       ,CONCAT( p.id_buku , ' - ',  ")
		.append("        (SELECT b.judul_buku  ")
		.append("          FROM  ")
		.append("            tb_buku b  ")
		.append("          WHERE  ")
		.append("            b.id_buku = p.id_buku)) as label ")
		.append("  FROM   tb_pinjam p  ")
		.append("  WHERE p.nim = ? ")
		.append("    AND p.tgl_kembali is null ");
		
		List<ComboModel> dtos = new ArrayList<ComboModel>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString(), nim);
		for (Map<String, Object> row : rows) {
			ComboModel model = new ComboModel();
			model.setValue(Common.toString(row.get("value")));
			model.setLabel(Common.toString(row.get("label")));
			dtos.add(model);
		}
		
		return dtos;
	}
	
	public int getlastIdByCategory(String category){
		StringBuffer query = new StringBuffer();
		query.append("select ifnull(substr(max(book_cd),5),'0') id ");
		query.append("from tb_m_book ");
		query.append("where category=? ");
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(), category);
		String id = "0";
		for (Map<String, Object> row : rows) {
			id = Common.toString(row.get("id"));
		}
		return Common.stringToInteger(id);
	}

	public int doUpdateBook(BookModel book) {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE tb_m_book set");
		sb.append(" title = ?,");
		sb.append(" author = ?,");
		sb.append(" subject_1 = ?,");
		sb.append(" subject_2 = ?,");
		sb.append(" book_cover = ?,");
		sb.append(" book_summary = ?,");
		sb.append(" publisher = ?,");
		sb.append(" publication_year = ?,");
		sb.append(" place_publication = ?,");
		sb.append(" number_page = ?,");
		sb.append(" stock = ?,");
		sb.append(" location = ?,");
		sb.append(" category = ?,");
		sb.append(" updated_by = ?,");
		sb.append(" updated_dt = ?");
		sb.append(" WHERE book_cd = ?");
		return jdbcTemplate.update(sb.toString(), 
				book.getTitle(),
				book.getAuthor(),
				book.getSubject1(),
				book.getSubject2(),
				book.getBookCover(),
				book.getBookSummary(),
				book.getPublisher(),
				book.getPublishYear(),
				book.getPlacePublication(),
				book.getNumberPage(),
				book.getStock(),
				book.getLocation(),
				book.getCategory(),
				book.getUpdatedBy(), 
				book.getUpdatedDt(), 
				book.getBookCode());
	
	}

	public int doAdd(BookModel book) {
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO tb_m_book( ")
			 .append(" book_cd,")
		 	 .append(" title,")
			 .append(" author,")
			 .append(" subject_1,")
			 .append(" subject_2,")
			 .append(" book_cover,")
			 .append(" book_summary,")
			 .append(" publisher,")
			 .append(" publication_year,")
			 .append(" place_publication,")
			 .append(" number_page,")
			 .append(" stock,")
			 .append(" location,")
			 .append(" category,")
		     .append(" created_by, ")
		     .append(" created_dt, ")
		     .append(" updated_by, ")
		     .append(" updated_dt   ")
		     .append(" ) VALUES ( ")
		     .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
			 .append(" ?,")
		     .append(" ?, ")// 6
		     .append(" ?, ")// 7
		     .append(" ?, ")// 8
		     .append(" ? ) ");// 9
	
		int row = jdbcTemplate.update(query.toString(),
				book.getBookCode(),
				book.getTitle(),
				book.getAuthor(),
				book.getSubject1(),
				book.getSubject2(),
				book.getBookCover(),
				book.getBookSummary(),
				book.getPublisher(),
				book.getPublishYear(),
				book.getPlacePublication(),
				book.getNumberPage(),
				book.getStock(),
				book.getLocation(),
				book.getCategory(),
				book.getCreatedBy(),// 6
				book.getCreatedDt(), // 7
				book.getUpdatedBy(), // 8
				book.getUpdatedDt());// 9
		return row;
	}

	public int doDeleteBook(BookModel book) {
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM tb_m_book ");
		sb.append(" WHERE book_cd = ?");
		return jdbcTemplate.update(sb.toString(), book.getBookCode());
	}

	public BookMissingModel getBookMissingById(String bookCd, String date) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" DATE_FORMAT(bm.date,'%d/%m/%Y') AS date,  ")
			 .append(" bm.book_cd,  ")
			 .append(" bm.number_book,  ")
			 .append(" bm.reason,  ")
			 .append(" bm.created_by,  ")
			 .append(" bm.created_dt,  ")
			 .append(" bm.updated_by,  ")
			 .append(" bm.updated_dt  ")
			 .append(" FROM tb_r_missing_book bm  ")
		     .append(" WHERE bm.book_cd = ? ")
		     .append(" AND bm.date = STR_TO_DATE(?,'%d/%m/%Y') ");
		
		BookMissingModel book = new BookMissingModel();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(),bookCd, date);
		for (Map<String, Object> row : rows) {
			System.out.println("row: ");
			System.out.println(row);
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			book.setCreatedBy(Common.toString(row.get("created_by")));
			book.setCreatedDt(Constanta.sdf.format(createdDate));
			book.setUpdatedBy(Common.toString(row.get("updated_by")));
			book.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			book.setBookCd(Common.toString(row.get("book_cd")));
			book.setDate(Common.toString(row.get("date")));
			book.setNumberOfBook((Integer)row.get("number_book"));
			book.setReason(Common.toString(row.get("reason")));
			
		}
		
		return book;
	}

	public int doUpdateMissingBook(BookMissingModel book) {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE tb_r_missing_book set");
		sb.append(" number_book = ?,");
		sb.append(" reason = ?,");
		sb.append(" updated_by = ?,");
		sb.append(" updated_dt = ?");
		sb.append(" WHERE book_cd = ? ");
		sb.append(" AND date = STR_TO_DATE(?,'%d/%m/%Y') ");
		return jdbcTemplate.update(sb.toString(), 
				book.getNumberOfBook(),
				book.getReason(),
				book.getUpdatedBy(), 
				book.getUpdatedDt(), 
				book.getBookCd(),
				book.getDate());
	}

	public int doAddMissingBook(BookMissingModel book) {
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO tb_r_missing_book( ")
			 .append(" book_cd,")
		 	 .append(" date,")
			 .append(" number_book,")
			 .append(" reason,")
			 .append(" created_by, ")
		     .append(" created_dt, ")
		     .append(" updated_by, ")
		     .append(" updated_dt   ")
		     .append(" ) VALUES ( ")
		     .append(" ?,")
			 .append(" STR_TO_DATE(?,'%d/%m/%Y'),")
			 .append(" ?,")
			 .append(" ?,")
		     .append(" ?, ")// 6
		     .append(" ?, ")// 7
		     .append(" ?, ")// 8
		     .append(" ? ) ");// 9
	
		int row = jdbcTemplate.update(query.toString(),
				book.getBookCd(),
				book.getDate(),
				book.getNumberOfBook(),
				book.getReason(),
				book.getCreatedBy(),// 6
				book.getCreatedDt(), // 7
				book.getUpdatedBy(), // 8
				book.getUpdatedDt());// 9
		return row;
	}

	public List<BookMissingModel> doSearchMissingBook(BookMissingModel model) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" DATE_FORMAT(bm.date,'%d/%m/%Y') AS date,  ")
			 .append(" bm.book_cd,  ")
			 .append(" bm.number_book,  ")
			 .append(" bm.reason,  ")
			 .append(" bm.created_by,  ")
			 .append(" bm.created_dt,  ")
			 .append(" bm.updated_by,  ")
			 .append(" bm.updated_dt  ")
			 .append(" FROM tb_r_missing_book bm  ")
		     .append(" WHERE bm.book_cd = ? ");
		
		List<BookMissingModel> books = new ArrayList<BookMissingModel>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(),model.getBookCd());
		for (Map<String, Object> row : rows) {
			BookMissingModel book = new BookMissingModel();
			System.out.println("row: ");
			System.out.println(row);
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			book.setCreatedBy(Common.toString(row.get("created_by")));
			book.setCreatedDt(Constanta.sdf.format(createdDate));
			book.setUpdatedBy(Common.toString(row.get("updated_by")));
			book.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			book.setBookCd(Common.toString(row.get("book_cd")));
			book.setDate(Common.toString(row.get("date")));
			book.setNumberOfBook((Integer)row.get("number_book"));
			book.setReason(Common.toString(row.get("reason")));
			
			books.add(book);
		}
		
		return books;
	}

	public int doDeleteMissingBook(BookMissingModel book) {
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM tb_r_missing_book ");
		sb.append(" WHERE book_cd = ? ");
		sb.append(" AND date = STR_TO_DATE(?,'%d/%m/%Y') ");
		return jdbcTemplate.update(sb.toString(), book.getBookCd(), book.getDate());
	}

	public List<ComboModel> getComboBook() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT book_cd as value, book_cd AS label FROM tb_m_book ");
		List<ComboModel> dtos = new ArrayList<ComboModel>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sb.toString());
		for (Map<String, Object> row : rows) {
			ComboModel model = new ComboModel();
			model.setValue(Common.toString(row.get("value")));
			model.setLabel(Common.toString(row.get("label")));
			dtos.add(model);
		}
		
		return dtos;
	}

}
