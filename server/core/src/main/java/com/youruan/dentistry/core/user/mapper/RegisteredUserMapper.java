
package com.youruan.dentistry.core.user.mapper;

import java.util.List;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.query.RegisteredUserQuery;
import com.youruan.dentistry.core.user.vo.ExtendedRegisteredUser;
import com.youruan.dentistry.core.user.vo.UserAllInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RegisteredUserMapper {


    public RegisteredUser get(Long id);

    public int update(RegisteredUser registeredUser);

    public int add(RegisteredUser registeredUser);

    public int delete(Long id);

    public int count(RegisteredUserQuery qo);

    public List<UserAllInfoVo> query(RegisteredUserQuery qo);

}
