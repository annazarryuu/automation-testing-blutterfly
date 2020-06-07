package com.id.perpus.transaksi.pinjam;

import com.id.perpus.common.Response;

public interface LoanService {

	Response getComboStatus();

	Response getComboNIM();

	Response getComboBook();

	Response doAdd(LoanModel pinjam);

	Response doEdit(LoanModel pinjam);

	Response doSearch(LoanModel model);

	LoanModel getLoanDataById(String loanNo);

	Response doDelete(LoanModel loan);

	LoanModel getKembaliDataInfo(String loanNo);

	Response doKembali(LoanModel pinjam);

}
