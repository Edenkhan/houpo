
package com.youruan.dentistry.core.user.service;

import java.util.List;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.query.RegisteredUserQuery;
import com.youruan.dentistry.core.user.vo.ExtendedRegisteredUser;
import com.youruan.dentistry.core.user.vo.UserAllInfoVo;

public interface RegisteredUserService {


    public RegisteredUser get(Long id);

    public UserAllInfoVo queryOne(RegisteredUserQuery qo);

    public List<UserAllInfoVo> list(RegisteredUserQuery qo);

    public Pagination<UserAllInfoVo> query(RegisteredUserQuery qo);

    public int count(RegisteredUserQuery qo);

    RegisteredUser create(String phoneNumber, Boolean locked);

    void update(RegisteredUser user, String phoneNumber, Boolean locked);
}
