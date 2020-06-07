package com.id.perpus.book;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.perpus.common.ComboModel;
import com.id.perpus.common.Common;
import com.id.perpus.common.Constanta;
import com.id.perpus.common.Messages;
import com.id.perpus.common.Response;
import com.id.perpus.system.SystemModel;
import com.id.perpus.system.SystemRepository;

@Service
public class BookImplService implements BookService{

	@Autowired
	BookRepository	repository;
	
	@Autowired
	SystemRepository systemRepository;
	
	@Autowired
	Messages messages;
	
	private final Logger logger = LoggerFactory.getLogger(BookImplService.class);
	
	@Override
	public Response doSearch(BookModel dto) {
		try {
			int offset = 0;
			if (dto.getPage() != null) {
				offset = (dto.getPage() - 1) * Constanta.LIMIT;
			}

			dto.setOffset(offset);

			List<BookModel> list = repository.doSearch(dto);
			Long total_row = repository.doCount(dto);

			int totalPage = (int) Math.ceil(total_row / Constanta.LIMIT.doubleValue());
			Integer currentPage = ((offset / Constanta.LIMIT) + 1);

			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list,currentPage, totalPage, offset);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null, 0, 0,1);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null, 0, 0, 1);
		}
	}
	
	public Response getComboBukuPinjam(String nim){
		try {
			List<ComboModel> list = repository.getComboBukuPinjam(nim);
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null);
		}
	}
	
	@Override
	public Response getCombo() {
		try {
			List<ComboModel> list = repository.getCombo();
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null);
		}
	}
	
	public Response doPinjam(TransaksiModel dto) {
		try {
			int result = repository.doPinjam(dto);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", "username atau email"),
						"MSTD0039AERR", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			}

		}
	}
	
	public Response doKembali(TransaksiModel dto) {
		try {
			int result = repository.doKembali(dto);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", "username atau email"),
						"MSTD0039AERR", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			}

		}
	}
	
	public Response getDataPinjam(String nim) {
		try {
			List<TransaksiModel> list = repository.getDataPinjam(nim);
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", list);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", "username atau email"),
						"MSTD0039AERR", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			}

		}
	}

	@Override
	public List<TransaksiModel> getDenda(String idPinjam) {
		try {
			 List<TransaksiModel> result = repository.getDenda(idPinjam);
			return result;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null; 
		}
	}

	@Override
	public Response getComboCategory() {
		try {
			List<SystemModel> data = systemRepository.getDataSystem("CATALOG_BOOK", "CATEGORY");
			List<ComboModel> list = new ArrayList<ComboModel>();
			if (data != null){
				for(int i=0; i<data.size(); i++){
					ComboModel temp = new ComboModel();
					temp.setLabel(data.get(i).getSysVal());
					temp.setValue(data.get(i).getSysCd());
					list.add(temp);
				}
			}
			
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null);
		}
	}

	@Override
	public BookModel getBookById(String bookCd) {
		try {
			return repository.getBookByCd(bookCd);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Response doAdd(BookModel book) {
		try {
			int lastId = repository.getlastIdByCategory(book.getCategory());
			lastId++;
			book.setBookCode(book.getCategory()+ "."+Common.integerToString03Format(lastId)); // new Book Id
			int result = repository.doAdd(book);
			
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", book.getBookCode()),
						"MSTD0039AERR", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			}

		}
	}

	@Override
	public Response doEdit(BookModel book) {
		try {
			int result = repository.doUpdateBook(book);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
		}
	}

	@Override
	public Response doDelete(BookModel book) {
		try {
			int result = repository.doDeleteBook(book);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", "username atau email"),
						"MSTD0039AERR", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			}

		}
	}

	@Override
	public BookMissingModel getBookMissingById(String bookCd, String date) {
		try {
			return repository.getBookMissingById(bookCd, date);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Response doEditMissingBook(BookMissingModel book) {
		try {
			int result = repository.doUpdateMissingBook(book);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
		}
	}

	@Override
	public Response doAddMissingBook(BookMissingModel book) {
		try {
			int result = repository.doAddMissingBook(book);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", book.getBookCd()),
						"MSTD0039AERR", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			}

		}
	}

	@Override
	public Response doSearchMissingBook(BookMissingModel model) {
		try {
			
			List<BookMissingModel> list = repository.doSearchMissingBook(model);

			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list,1, 1, 1);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null, 0, 0,1);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null, 0, 0, 1);
		}
	}

	@Override
	public Response doDeleteMissingBook(BookMissingModel book) {
		try {
			int result = repository.doDeleteMissingBook(book);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),
						"MSTD0000AERR", null);
			

		}
	}

	@Override
	public Response getBookByCode(String bookCode) {
		try {
			BookModel data = repository.getBookByCd(bookCode);
			List<BookModel> list = new ArrayList<BookModel>();
			if (data != null){
				list.add(data);
			}
			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list,0, 0, 1);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null, 0, 0,1);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null, 0, 0, 1);
		}
	}
}
