package cn.jj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jj.mapper.DutyMapper;
import cn.jj.pojo.Duty;
import cn.jj.service.DutyService;
@Service
public class DutyServiceImpl implements DutyService{
	@Autowired
	private DutyMapper dutyMapper;
	@Override
	public List<Duty> dutiesList() {
		return dutyMapper.dutiesList();
	}

}
