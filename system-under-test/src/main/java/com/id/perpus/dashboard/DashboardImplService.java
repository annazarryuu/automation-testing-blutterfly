package com.id.perpus.dashboard;

//import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.perpus.common.Constanta;
import com.id.perpus.common.Messages;
import com.id.perpus.common.Response;

@Service
public class DashboardImplService implements DashboardService{

	@Autowired
	Messages messages;

	@Autowired
	DataSource dataSource;
	
//	@Autowired
//	private CommonRepository commonRepository;
	
	@Autowired
	private DashboardRepository repository;
	
	private final Logger logger = LoggerFactory.getLogger(DashboardImplService.class);
	
	@Override
	public Response doAdd(DashboardModel dto) {
		try {
			int result = repository.doAdd(dto);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", "grup"), "MSTD0039AERR",null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),"MSTD0000AERR", null);
			}
		}
	}
	
	@Override
	public Response doDelete(List<DashboardModel> dto) {
		try {
			int result = repository.doDelete(dto);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", "grup"), "MSTD0039AERR",null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),"MSTD0000AERR", null);
			}
		}
	}
	@Override
	public Response doEdit(DashboardModel dto) {
		try {
			int result = repository.doEdit(dto);
			if (result > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0101AINF"), "MSTD0101AINF", null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0063AERR"), "MSTD0063AERR", null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage() != null && e.getMessage().contains(Constanta.DUPLICATE)) {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0039AERR", "grup"), "MSTD0039AERR",null);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()),"MSTD0000AERR", null);
			}
		}
	}
	@Override
	public Response doCountDt(DashboardModel dto){
		try {
			int offset = 0;
			if (dto.getPage() != null) {
				offset = (dto.getPage() - 1) * Constanta.LIMIT;
				dto.setLimit(Constanta.LIMIT);
			}

			dto.setOffset(offset);

			List<DashboardModel> list = repository.doCountDt(dto);
			Long total_row = repository.doCount(dto);

			int totalPage = (int)Math.ceil(total_row/Constanta.LIMIT.doubleValue());
			Integer currentPage = ((offset/Constanta.LIMIT) + 1);

			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list,currentPage,totalPage, offset);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null,0,0,1);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null,0,0,1);
		}
	}
	@Override
	public Response doSearch(DashboardModel dto) {
		try {
			int offset = 0;
			if (dto.getPage() != null) {
				offset = (dto.getPage() - 1) * Constanta.LIMIT;
				dto.setLimit(Constanta.LIMIT);
			}

			dto.setOffset(offset);

			List<DashboardModel> list = repository.doSearch(dto);
			Long total_row = repository.doCount(dto);

			int totalPage = (int)Math.ceil(total_row/Constanta.LIMIT.doubleValue());
			Integer currentPage = ((offset/Constanta.LIMIT) + 1);

			if (list != null && list.size() > 0) {
				return new Response(Constanta.SUCCESS, messages.find("message.MSTD0120AINF"), "MSTD0120AINF", list,currentPage,totalPage, offset);
			} else {
				return new Response(Constanta.FAILED, messages.find("message.MSTD0059AERR"), "MSTD0059AERR", null,0,0,1);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(Constanta.FAILED, messages.find("message.MSTD0000AERR", e.getMessage()), "MSTD0000AERR",null,0,0,1);
		}
	}
	@Override
	public Response doSearchById(String id) {
		try {
			List<DashboardModel> list = repository.doSearchById(id);
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
}
