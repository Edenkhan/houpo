
package com.youruan.dentistry.core.user.mapper;

import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.query.RegisteredUserQuery;
import com.youruan.dentistry.core.user.vo.ExtendedRegisteredUser;
import com.youruan.dentistry.core.user.vo.UserAllInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RegisteredUserMapper {


    public RegisteredUser get(Long id);

    public int update(RegisteredUser user);

    public int add(RegisteredUser user);

    public int delete(Long id);

    public int count(RegisteredUserQuery qo);

    public List<ExtendedRegisteredUser> query(RegisteredUserQuery qo);

    /**
     * 根据openid查询用户 并返回
     */
    RegisteredUser getByOpenid(String openid);

    /**
     * 查询用户所有信息
     */
    List<UserAllInfoVo> queryAll(RegisteredUserQuery qo);
}
