
package com.youruan.dentistry.core.user.service;

import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.query.RegisteredUserQuery;
import com.youruan.dentistry.core.user.vo.ExtendedRegisteredUser;
import com.youruan.dentistry.core.user.vo.UserAllInfoVo;

import java.util.List;

public interface RegisteredUserService {


    public RegisteredUser get(Long id);

    public ExtendedRegisteredUser queryOne(RegisteredUserQuery qo);

    public List<ExtendedRegisteredUser> list(RegisteredUserQuery qo);

    public Pagination<ExtendedRegisteredUser> query(RegisteredUserQuery qo);

    public int count(RegisteredUserQuery qo);

    RegisteredUser create(String phoneNumber, Boolean locked);

    void update(RegisteredUser user, String phoneNumber, Boolean locked);


    /**
     * 修改用户所有信息
     */
    void modify(RegisteredUser user, String major, String school, Integer education, String job, String area);

    /**
     * 查询用户所有信息
     */
    Pagination<UserAllInfoVo> queryAll(RegisteredUserQuery qo);

    /**
     * 检查用户资料是否完善
     */
    boolean checkCompleteInfo(RegisteredUser user);
}
