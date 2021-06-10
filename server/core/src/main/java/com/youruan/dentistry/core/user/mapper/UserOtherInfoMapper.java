
package com.youruan.dentistry.core.user.mapper;

import com.youruan.dentistry.core.user.domain.UserOtherInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserOtherInfoMapper {


    public UserOtherInfo get(Long userId);

    public int update(UserOtherInfo UserOtherInfo);

    public int add(UserOtherInfo UserOtherInfo);

    public int delete(Long id);

}
