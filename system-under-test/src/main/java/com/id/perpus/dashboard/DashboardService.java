package com.id.perpus.dashboard;

import java.util.List;

import com.id.perpus.common.Response;

public interface DashboardService {
	public Response doSearch(DashboardModel dto);
	public Response doCountDt(DashboardModel dto);
	public Response doAdd(DashboardModel dto);
	public Response doEdit(DashboardModel dto);
	public Response doSearchById(String id);
	public Response doDelete(List<DashboardModel> dto);
}
