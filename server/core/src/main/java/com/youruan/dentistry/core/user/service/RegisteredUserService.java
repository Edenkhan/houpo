
package com.youruan.dentistry.core.user.service;

import java.util.List;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.query.RegisteredUserQuery;
import com.youruan.dentistry.core.user.vo.ExtendedRegisteredUser;

public interface RegisteredUserService {


    public RegisteredUser get(Long id);

    public ExtendedRegisteredUser queryOne(RegisteredUserQuery qo);

    public List<ExtendedRegisteredUser> list(RegisteredUserQuery qo);

    public Pagination<ExtendedRegisteredUser> query(RegisteredUserQuery qo);

    public int count(RegisteredUserQuery qo);

    RegisteredUser create(String phoneNumber, Boolean locked);

    void update(RegisteredUser user, String phoneNumber, Boolean locked);
}
