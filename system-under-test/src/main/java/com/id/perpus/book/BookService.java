package com.id.perpus.book;

import java.util.List;

import com.id.perpus.common.Response;

public interface BookService {
	public Response doSearch(BookModel model);
	
	public Response getComboCategory();
	
	public BookModel getBookById(String bookCd);
	
	public Response doAdd(BookModel book);

	public Response doEdit(BookModel book);
	
	public Response doDelete(BookModel book);
	
	public BookMissingModel getBookMissingById(String bookCd, String date);
	
	// belum dipakai
	public Response getCombo();
	
	public Response getComboBukuPinjam(String nim);
	
	public Response doPinjam(TransaksiModel model);
	
	public Response doKembali(TransaksiModel model);
	
	public Response getDataPinjam(String nim);
	
	public List<TransaksiModel> getDenda(String idPinjam);

	public Response doEditMissingBook(BookMissingModel book);

	public Response doAddMissingBook(BookMissingModel book);

	public Response doSearchMissingBook(BookMissingModel model);

	public Response doDeleteMissingBook(BookMissingModel book);

	public Response getBookByCode(String bookCode);

	

	


}
