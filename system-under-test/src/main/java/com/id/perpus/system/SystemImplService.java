package com.id.perpus.system;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.perpus.common.ComboModel;
import com.id.perpus.common.Constanta;
import com.id.perpus.common.Messages;
import com.id.perpus.common.Response;
import com.id.perpus.user.UserImplService;

@Service
public class SystemImplService implements SystemService{
	private final Logger logger = LoggerFactory.getLogger(UserImplService.class);
	
	@Autowired
	Messages messages;
	
	@Autowired
	private SystemRepository systemRepository;
	
	@Override
	public Response getComboPage() {
		try {
			List<SystemModel> data = systemRepository.getDataSystem("SCREEN", "PAGE");
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

}
