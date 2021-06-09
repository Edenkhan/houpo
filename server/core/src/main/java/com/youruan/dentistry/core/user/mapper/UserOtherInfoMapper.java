
package com.youruan.dentistry.core.user.mapper;

import com.youruan.dentistry.core.user.domain.UserOtherInfo;
import com.youruan.dentistry.core.user.vo.UserAllInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserOtherInfoMapper {


    public UserOtherInfo get(Long id);

    public int update(UserOtherInfo UserOtherInfo);

    public int add(UserOtherInfo UserOtherInfo);

    public int delete(Long id);

    public int count(UserOtherInfo qo);

    public List<UserAllInfoVo> query(UserOtherInfo qo);

}
