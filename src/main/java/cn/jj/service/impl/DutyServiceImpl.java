package cn.jj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jj.mapper.DutyMapper;
import cn.jj.pojo.Duty;
import cn.jj.service.DutyService;
@Service
@Transactional
public class DutyServiceImpl implements DutyService{
	@Autowired
	private DutyMapper dutyMapper;
	//获取职责列表
	@Override
	public List<Duty> dutiesList() {
		return dutyMapper.dutiesList();
	}

}
