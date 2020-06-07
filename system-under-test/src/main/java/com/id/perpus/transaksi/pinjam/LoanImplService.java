package com.id.perpus.transaksi.pinjam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.perpus.book.BookRepository;
import com.id.perpus.common.ComboModel;
import com.id.perpus.common.Common;
import com.id.perpus.common.Constanta;
import com.id.perpus.common.Messages;
import com.id.perpus.common.Response;
import com.id.perpus.system.SystemModel;
import com.id.perpus.system.SystemRepository;
import com.id.perpus.user.UserRepository;

@Service
public class LoanImplService implements LoanService{

	@Autowired
	SystemRepository systemRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	Messages messages;
	
	private final Logger logger = LoggerFactory.getLogger(LoanImplService.class);
	
	

	@Override
	public Response getComboStatus() {
		try {
			List<SystemModel> data = systemRepository.getDataSystem("BOOK_LOAN", "BOOK_LOAN_TRANSACTION");
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
	public Response getComboNIM() {
		try {
			List<ComboModel> list = userRepository.getComboNIM();
			
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
	public Response getComboBook() {
		try {
			List<ComboModel> list = bookRepository.getComboBook();
			
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
	public Response doAdd(LoanModel pinjam) {
		try {
			String currentDate = Common.formatDateYYYYMMDD(new Date());
			int lastId = loanRepository.getlastIdByDate(currentDate);
			lastId++;
			pinjam.setLoanStatus("BL1");
			pinjam.setLoanNo(currentDate+Common.integerToString03Format(lastId)); // new Loan No
			int result = loanRepository.doAdd(pinjam);
			
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
	public Response doEdit(LoanModel pinjam) {
		try {
			int result = loanRepository.doUpdate(pinjam);
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
	public Response doSearch(LoanModel dto) {
		try {
			int offset = 0;
			if (dto.getPage() != null) {
				offset = (dto.getPage() - 1) * Constanta.LIMIT;
			}

			dto.setOffset(offset);

			List<LoanModel> list = loanRepository.doSearch(dto);
			Long total_row = loanRepository.doCount(dto);

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

	@Override
	public LoanModel getLoanDataById(String loanNo) {
		try {
			return loanRepository.getLoanDataById(loanNo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Response doDelete(LoanModel loan) {
		try {
			int result = loanRepository.doDelete(loan);
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
	public LoanModel getKembaliDataInfo(String loanNo) {
		try {
			return loanRepository.getKembaliDataInfo(loanNo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Response doKembali(LoanModel pinjam) {
		try {
			int result = loanRepository.doUpdateKembali(pinjam);
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

}
