
package com.youruan.dentistry.core.user.service.impl;

import com.youruan.dentistry.core.base.exception.OptimismLockingException;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.mapper.RegisteredUserMapper;
import com.youruan.dentistry.core.user.query.RegisteredUserQuery;
import com.youruan.dentistry.core.user.service.RegisteredUserService;
import com.youruan.dentistry.core.user.vo.ExtendedRegisteredUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("registeredUserService")
public class BasicRegisteredUserService
    implements RegisteredUserService
{

    private final RegisteredUserMapper registeredUserMapper;

    public BasicRegisteredUserService(RegisteredUserMapper registeredUserMapper) {
        this.registeredUserMapper = registeredUserMapper;
    }

    @Override
    public RegisteredUser get(Long id) {
        return registeredUserMapper.get(id);
    }

    protected void update(RegisteredUser registeredUser) {
        int affected = registeredUserMapper.update(registeredUser);
        if (affected == 0) {
            throw new OptimismLockingException("version!!");
        }
        registeredUser.setVersion((registeredUser.getVersion()+ 1));
    }

    protected RegisteredUser add(RegisteredUser registeredUser) {
        registeredUser.setCreatedDate(new Date());
        registeredUserMapper.add(registeredUser);
        return registeredUser;
    }

    @Override
    public List<ExtendedRegisteredUser> list(RegisteredUserQuery qo) {
        return registeredUserMapper.query(qo);
    }

    @Override
    public ExtendedRegisteredUser queryOne(RegisteredUserQuery qo) {
        qo.setPageSize(1);
        List<ExtendedRegisteredUser> data = registeredUserMapper.query(qo);
        return (((data == null)||data.isEmpty())?null:data.get(0));
    }

    @Override
    public Pagination<ExtendedRegisteredUser> query(RegisteredUserQuery qo) {
        int rows = registeredUserMapper.count(qo);
        List<ExtendedRegisteredUser> data = ((rows == 0)?new ArrayList<ExtendedRegisteredUser>():registeredUserMapper.query(qo));
        return new Pagination<ExtendedRegisteredUser>(rows, data);
    }

    @Override
    public int count(RegisteredUserQuery qo) {
        return registeredUserMapper.count(qo);
    }

    @Override
    @Transactional
    public RegisteredUser create(String phoneNumber, Boolean locked) {
        RegisteredUser user = new RegisteredUser();
        user.setPhoneNumber(phoneNumber);
        user.setLocked(locked);
        return add(user);
    }

    @Override
    public void update(RegisteredUser user, String phoneNumber, Boolean locked) {
        Assert.notNull(user, "必须提供用户");
        user.setPhoneNumber(phoneNumber);
        user.setLocked(locked);
        update(user);
    }

}
