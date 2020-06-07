package com.id.perpus.transaksi.pinjam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.id.perpus.common.Common;
import com.id.perpus.common.Constanta;

@Repository
public class LoanRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int doAdd(LoanModel pinjam) {
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO tb_r_loan( ")
			 .append(" loan_no,")
		 	 .append(" nim,")
			 .append(" book_cd,")
			 .append(" loan_date,")
			 .append(" returned_date,")
			 .append(" loan_status,")
			 .append(" created_by, ")
		     .append(" created_dt, ")
		     .append(" updated_by, ")
		     .append(" updated_dt   ")
		     .append(" ) VALUES ( ")
		     .append(" ?,")
		 	 .append(" ?,")
			 .append(" ?,")
			 .append(" STR_TO_DATE(?,'%d/%m/%Y'),")
			 .append(" STR_TO_DATE(?,'%d/%m/%Y'),")
			 .append(" ?,")
		     .append(" ?, ")// 6
		     .append(" ?, ")// 7
		     .append(" ?, ")// 8
		     .append(" ? ) ");// 9
	
		int row = jdbcTemplate.update(query.toString(),
				pinjam.getLoanNo(),
				pinjam.getNim(),
				pinjam.getBookCd(),
				pinjam.getLoanDate(),
				pinjam.getReturnedDate(),
				pinjam.getLoanStatus(),
				pinjam.getCreatedBy(),// 6
				pinjam.getCreatedDt(), // 7
				pinjam.getUpdatedBy(), // 8
				pinjam.getUpdatedDt());// 9
		return row;
	}

	public int doUpdate(LoanModel pinjam) {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE tb_r_loan set");
		sb.append(" nim = ?,");
		sb.append(" book_cd = ?,");
		sb.append(" loan_date = STR_TO_DATE(?,'%d/%m/%Y'),");
		sb.append(" returned_date = STR_TO_DATE(?,'%d/%m/%Y'),");
		sb.append(" updated_by = ?,");
		sb.append(" updated_dt = ?");
		sb.append(" WHERE loan_no = ?");
		return jdbcTemplate.update(sb.toString(), 
				pinjam.getNim(),
				pinjam.getBookCd(),
				pinjam.getLoanDate(),
				pinjam.getReturnedDate(),
				pinjam.getUpdatedBy(), 
				pinjam.getUpdatedDt(), 
				pinjam.getLoanNo());
	}

	public int getlastIdByDate(String currentDate) {
		StringBuffer query = new StringBuffer();
		query.append(" select ifnull(substr(max(l.loan_no),9),'0') id ");
		query.append(" from tb_r_loan l ");
		query.append(" where substr(l.loan_no, 1, 8) = ? ");
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(), currentDate);
		String id = "0";
		for (Map<String, Object> row : rows) {
			id = Common.toString(row.get("id"));
		}
		return Common.stringToInteger(id);
	}

	public List<LoanModel> doSearch(LoanModel dto) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" l.loan_no,  ")
			 .append(" l.nim,  ")
			 .append(" s.user_email,  ")
			 .append(" u.name AS member_name, ")
			 .append(" CONCAT(m.majors_name, ' ', p.prodi_name) AS majors, ")
			 .append(" l.book_cd,  ")
			 .append(" b.title AS book_title, ")
			 .append(" DATE_FORMAT(l.loan_date,'%d/%m/%Y') as loan_date,  ")
			 .append(" DATE_FORMAT(l.returned_date,'%d/%m/%Y') as returned_date,  ")
			 .append(" DATE_FORMAT(l.actual_returned_date,'%d/%m/%Y') as actual_returned_date,  ")
			 .append(" ifnull(l.penalty, 0) penalty,  ")
			 .append(" l.loan_status,  ")
			 .append("   b.book_cd,")
			 .append("   b.title,")
			 .append("   b.author,")
			 .append("   b.publisher,")
			 .append("   DATE_FORMAT(b.publication_year, '%Y') as publication_year,")
		     .append(" l.created_by,  ")
			 .append(" l.created_dt,  ")
			 .append(" l.updated_by,  ")
			 .append(" l.updated_dt  ")
			 .append(" FROM tb_r_loan l INNER JOIN tb_m_students s ON l.nim = s.nim ")
			 .append("      INNER JOIN tb_m_user u ON u.user_email = s.user_email ")
			 .append("      INNER JOIN tb_m_book b ON b.book_cd = l.book_cd ")
			 .append("      left join tb_m_prodi p on s.prodi_id = p.prodi_id ")
			 .append("      left join tb_m_majors m on p.majors_id = m.majors_id ")
		     .append(" WHERE 1=1 ");
		if(dto.getMemberName() != null && !dto.getMemberName().isEmpty()){
			query.append(" AND u.name LIKE '%"+dto.getMemberName()+"%'");
		}
		if(dto.getBookTitle() != null && !dto.getBookTitle().isEmpty()){
			query.append(" AND b.title LIKE '%"+dto.getBookTitle()+"%'");
		}
		if(dto.getBookAuthor() != null && !dto.getBookAuthor().isEmpty()){
			query.append(" AND b.author LIKE '%"+dto.getBookAuthor()+"%'");
		}
		if(dto.getMajors() != null && !dto.getMajors().isEmpty()){
			query.append(" AND ( p.prodi_name LIKE '%"+dto.getMajors()+"%'  ");
			query.append("  OR m.majors_name LIKE '%"+dto.getMajors()+"%'  ) ");
		}
		if(dto.getLoanDateFrom() != null && !dto.getLoanDateFrom().isEmpty()){
			query.append(" AND l.loan_date >=  STR_TO_DATE('"+dto.getLoanDateFrom()+"','%d/%m/%Y') ");
		}
		if(dto.getLoanDateTo() != null && !dto.getLoanDateTo().isEmpty()){
			query.append(" AND l.loan_date <=  STR_TO_DATE('"+dto.getLoanDateTo()+"','%d/%m/%Y') ");
		}
		if(dto.getLoanStatus() != null && !dto.getLoanStatus().isEmpty()){
			query.append(" AND l.loan_status = '"+dto.getLoanStatus()+"'");
		}
		if(dto.getNim() != null && !dto.getNim().isEmpty()){
			query.append(" AND l.nim = '"+dto.getNim()+"'");
		}
		
		query.append(" ORDER  BY l.loan_no ASC LIMIT ? OFFSET ?");
		List<LoanModel> loans = new ArrayList<LoanModel>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(),dto.getLimit(), dto.getOffset());
		for (Map<String, Object> row : rows) {
			System.out.println("row: ");
			System.out.println(row);
			LoanModel loan = new LoanModel();
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			loan.setCreatedBy(Common.toString(row.get("created_by")));
			loan.setCreatedDt(Constanta.sdf.format(createdDate));
			loan.setUpdatedBy(Common.toString(row.get("updated_by")));
			loan.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			loan.setLoanNo(Common.toString(row.get("loan_no")));
			loan.setEmail(Common.toString(row.get("user_email")));
			loan.setNim(Common.toString(row.get("nim")));
			loan.setMemberName(Common.toString(row.get("member_name")));
			loan.setMajors(Common.toString(row.get("majors")));
			
			loan.setBookCd(Common.toString(row.get("book_cd")));
			loan.setBookTitle(Common.toString(row.get("title")));
			loan.setBookAuthor(Common.toString(row.get("author")));
			loan.setBookPublisher(Common.toString(row.get("publisher")));
			loan.setBookPublicationYear(Common.toString(row.get("publication_year")));
			
			loan.setLoanDate(Common.toString(row.get("loan_date")));
			loan.setReturnedDate(Common.toString(row.get("returned_date")));
			loan.setActualReturnedDate(Common.toString(row.get("actual_returned_date")));
			loan.setPenalty(((Integer)row.get("penalty")));
			
			loans.add(loan);
		}
		
		return loans;
	}

	public Long doCount(LoanModel dto) {
		Long count = (long)0;
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(*) COUNT ")
		.append(" FROM tb_r_loan l INNER JOIN tb_m_students s ON l.nim = s.nim ")
		 .append("      INNER JOIN tb_m_user u ON u.user_email = s.user_email ")
		 .append("      INNER JOIN tb_m_book b ON b.book_cd = l.book_cd ")
		 .append("      left join tb_m_prodi p on s.prodi_id = p.prodi_id ")
		 .append("      left join tb_m_majors m on p.majors_id = m.majors_id ")
	     .append(" WHERE 1=1 ");
		
		if(dto.getMemberName() != null && !dto.getMemberName().isEmpty()){
			query.append(" AND u.name LIKE '%"+dto.getMemberName()+"%'");
		}
		if(dto.getBookTitle() != null && !dto.getBookTitle().isEmpty()){
			query.append(" AND b.title LIKE '%"+dto.getBookTitle()+"%'");
		}
		if(dto.getBookAuthor() != null && !dto.getBookAuthor().isEmpty()){
			query.append(" AND b.author LIKE '%"+dto.getBookAuthor()+"%'");
		}
		if(dto.getMajors() != null && !dto.getMajors().isEmpty()){
			query.append(" AND ( p.prodi_name LIKE '%"+dto.getMajors()+"%'  ");
			query.append("  OR m.majors_name LIKE '%"+dto.getMajors()+"%'  ) ");
		}
		if(dto.getLoanDateFrom() != null && !dto.getLoanDateFrom().isEmpty()){
			query.append(" AND l.loan_date >=  STR_TO_DATE('"+dto.getLoanDateFrom()+"','%d/%m/%Y') ");
		}
		if(dto.getLoanDateTo() != null && !dto.getLoanDateTo().isEmpty()){
			query.append(" AND l.loan_date <=  STR_TO_DATE('"+dto.getLoanDateTo()+"','%d/%m/%Y') ");
		}
		if(dto.getLoanStatus() != null && !dto.getLoanStatus().isEmpty()){
			query.append(" AND l.loan_status = '"+dto.getLoanStatus()+"'");
		}
		if(dto.getNim() != null && !dto.getNim().isEmpty()){
			query.append(" AND l.nim = '"+dto.getNim()+"'");
		}
		
	    List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString());
		for (Map<String, Object> row : rows) {
			count = ((Long)row.get("COUNT"));	
		}
		return count;
	}

	public LoanModel getLoanDataById(String loanNo) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT  ")
			 .append(" l.loan_no,  ")
			 .append(" l.nim,  ")
			 .append(" s.user_email,  ")
			 .append(" u.name AS member_name, ")
			 .append(" CONCAT(m.majors_name, ' ', p.prodi_name) AS majors, ")
			 .append(" l.book_cd,  ")
			 .append(" b.title AS book_title, ")
			 .append(" DATE_FORMAT(l.loan_date,'%d/%m/%Y') as loan_date,  ")
			 .append(" DATE_FORMAT(l.returned_date,'%d/%m/%Y') as returned_date,  ")
			 .append(" DATE_FORMAT(l.actual_returned_date,'%d/%m/%Y') as actual_returned_date,  ")
			 .append(" ifnull(l.penalty, 0) penalty,  ")
			 .append(" l.loan_status,  ")
		     .append(" l.created_by,  ")
			 .append(" l.created_dt,  ")
			 .append(" l.updated_by,  ")
			 .append(" l.updated_dt  ")
			 .append(" FROM tb_r_loan l INNER JOIN tb_m_students s ON l.nim = s.nim ")
			 .append("      INNER JOIN tb_m_user u ON u.user_email = s.user_email ")
			 .append("      INNER JOIN tb_m_book b ON b.book_cd = l.book_cd ")
			 .append("      left join tb_m_prodi p on s.prodi_id = p.prodi_id ")
			 .append("      left join tb_m_majors m on p.majors_id = m.majors_id ")
		     .append(" WHERE l.loan_no = ? ");
		
		LoanModel loan = null;

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(),loanNo);
		for (Map<String, Object> row : rows) {
			System.out.println("row: ");
			System.out.println(row);
			loan = new LoanModel();
			Date createdDate = (Date) row.get("created_dt");
			Date updatedDate = (Date) row.get("updated_dt");
			loan.setCreatedBy(Common.toString(row.get("created_by")));
			loan.setCreatedDt(Constanta.sdf.format(createdDate));
			loan.setUpdatedBy(Common.toString(row.get("updated_by")));
			loan.setUpdatedDt(Constanta.sdf.format(updatedDate));
			
			loan.setLoanNo(Common.toString(row.get("loan_no")));
			loan.setNim(Common.toString(row.get("nim")));
			loan.setEmail(Common.toString(row.get("user_email")));
			loan.setMemberName(Common.toString(row.get("member_name")));
			loan.setMajors(Common.toString(row.get("majors")));
			loan.setBookCd(Common.toString(row.get("book_cd")));
			loan.setBookTitle(Common.toString(row.get("book_title")));
			loan.setLoanDate(Common.toString(row.get("loan_date")));
			loan.setReturnedDate(Common.toString(row.get("returned_date")));
			loan.setActualReturnedDate(Common.toString(row.get("actual_returned_date")));
			loan.setPenalty(((Integer)row.get("penalty")));
			
		}
		
		return loan;
	}

	public int doDelete(LoanModel loan) {
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM tb_r_loan ");
		sb.append(" WHERE loan_no = ?");
		return jdbcTemplate.update(sb.toString(), loan.getLoanNo());
	}

	public LoanModel getKembaliDataInfo(String loanNo) {
		StringBuilder query = new StringBuilder();
		query.append(" select ");
		query.append(" s.nim, ");
		query.append("  (select u.name");
		query.append("   from tb_m_user u");
		query.append("    where u.user_email = s.user_email) AS member_name,");
		query.append("   (select m.majors_name");
		query.append("    from tb_m_prodi p, tb_m_majors m");
		query.append("    where p.majors_id = m.majors_id");
		query.append("           and p.prodi_id = s.prodi_id) AS major_name,");
		query.append("   (select p.prodi_name");
		query.append("     from tb_m_prodi p");
		query.append("     where p.prodi_id = s.prodi_id) AS prodi_name,");
		query.append("   b.book_cd,");
		query.append("   b.title,");
		query.append("   b.author,");
		query.append("   b.publisher,");
		query.append("   DATE_FORMAT(b.publication_year, '%Y') as publication_year,");
		query.append("   l.loan_no,");
		query.append("   DATE_FORMAT(l.loan_date, '%d/%c/%Y') AS loan_date,");
		query.append("   DATE_FORMAT(l.returned_date, '%d/%c/%Y') AS returned_date,");
		query.append("    DATE_FORMAT(CURDATE(), '%d/%c/%Y') AS actual_returned_date,");
		query.append("   if(DATEDIFF(CURDATE(), l.returned_date) <= 0, 0, DATEDIFF(CURDATE(), l.returned_date) *  (select CONVERT(s.sys_val, UNSIGNED INTEGER) "); 
		query.append("               from tb_m_system s ");
		query.append("              where s.sys_cat = 'BOOK_LOAN' ");
		query.append("                AND s.sys_sub_cat = 'PENALTY_RETURNED' ");
		query.append("                AND s.sys_cd = 'PR1')) AS penalty "); 
		query.append("   from tb_r_loan l inner join tb_m_students s on l.nim = s.nim ");
		query.append("        inner join tb_m_book b on l.book_cd = b.book_cd ");
		query.append("   where  l.loan_no = ?  ");
		
		LoanModel loan = null;

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query.toString(),loanNo);
		for (Map<String, Object> row : rows) {
			System.out.println("row: ");
			System.out.println(row);
			loan = new LoanModel();
			
			loan.setLoanNo(Common.toString(row.get("loan_no")));
			loan.setNim(Common.toString(row.get("nim")));
			loan.setMemberName(Common.toString(row.get("member_name")));
			loan.setMajorName(Common.toString(row.get("major_name")));
			loan.setProdiName(Common.toString(row.get("prodi_name")));
			
			loan.setBookCd(Common.toString(row.get("book_cd")));
			loan.setBookTitle(Common.toString(row.get("title")));
			loan.setBookAuthor(Common.toString(row.get("author")));
			loan.setBookPublisher(Common.toString(row.get("publisher")));
			loan.setBookPublicationYear(Common.toString(row.get("publication_year")));
			
			loan.setLoanDate(Common.toString(row.get("loan_date")));
			loan.setReturnedDate(Common.toString(row.get("returned_date")));
			loan.setActualReturnedDate(Common.toString(row.get("actual_returned_date")));
			loan.setPenalty(((BigDecimal)row.get("penalty")).intValue());
			
		}
		
		return loan;
	}

	public int doUpdateKembali(LoanModel pinjam) {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE tb_r_loan set");
		sb.append(" penalty = ?,");
		sb.append(" loan_status = ?,");
		sb.append(" actual_returned_date = STR_TO_DATE(?,'%d/%m/%Y'),");
		sb.append(" updated_by = ?,");
		sb.append(" updated_dt = ?");
		sb.append(" WHERE loan_no = ?");
		return jdbcTemplate.update(sb.toString(), 
				pinjam.getPenalty(),
				pinjam.getLoanStatus(),
				pinjam.getActualReturnedDate(),
				pinjam.getUpdatedBy(), 
				pinjam.getUpdatedDt(), 
				pinjam.getLoanNo());
	}

}
