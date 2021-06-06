package com.youruan.dentistry.core.enroll.service.impl;

import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.enroll.mapper.EnrollMapper;
import com.youruan.dentistry.core.enroll.query.EnrollQuery;
import com.youruan.dentistry.core.enroll.service.EnrollService;
import com.youruan.dentistry.core.enroll.vo.ExtendedEnroll;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicEnrollService implements EnrollService {

    private final EnrollMapper enrollMapper;

    public BasicEnrollService(EnrollMapper enrollMapper) {
        this.enrollMapper = enrollMapper;
    }

    @Override
    public Pagination<ExtendedEnroll> query(EnrollQuery qo) {
        int rows = enrollMapper.count(qo);
        List<ExtendedEnroll> datas = ( (rows == 0) ? new ArrayList<>() : enrollMapper.query(qo) );
        return new Pagination<>(rows,datas);
    }
}
