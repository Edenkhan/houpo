package com.youruan.dentistry.core.enroll.mapper;

import com.youruan.dentistry.core.enroll.query.EnrollQuery;
import com.youruan.dentistry.core.enroll.vo.ExtendedEnroll;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnrollMapper {

    /**
     * 查询报名信息总条目
     */
    int count(EnrollQuery qo);

    /**
     * 条件分页查询 报名信息
     */
    List<ExtendedEnroll> query(EnrollQuery qo);
}
