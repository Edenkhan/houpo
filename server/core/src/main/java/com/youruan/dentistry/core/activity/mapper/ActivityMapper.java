package com.youruan.dentistry.core.activity.mapper;

import com.youruan.dentistry.core.activity.domain.Activity;
import com.youruan.dentistry.core.activity.query.ActivityQuery;
import com.youruan.dentistry.core.activity.vo.ExtendedActivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {

    /**
     * 查询活动总条目
     */
    int count(ActivityQuery qo);

    /**
     * 条件分页查询 活动
     */
    List<ExtendedActivity> query(ActivityQuery qo);

    /**
     * 根据id获取活动信息
     */
    Activity get(Long id);

    /**
     * 添加活动
     */
    void add(Activity banner);

    /**
     * 修改活动
     */
    int update(Activity banner);

}
