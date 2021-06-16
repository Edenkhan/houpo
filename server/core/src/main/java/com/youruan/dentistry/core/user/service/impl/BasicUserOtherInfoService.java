
package com.youruan.dentistry.core.user.service.impl;

import com.youruan.dentistry.core.base.exception.OptimismLockingException;
import com.youruan.dentistry.core.user.domain.UserOtherInfo;
import com.youruan.dentistry.core.user.mapper.UserOtherInfoMapper;
import com.youruan.dentistry.core.user.service.UserOtherInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

@Service
public class BasicUserOtherInfoService
    implements UserOtherInfoService
{

    private final UserOtherInfoMapper userOtherInfoMapper;

    public BasicUserOtherInfoService(UserOtherInfoMapper userOtherInfoMapper) {
        this.userOtherInfoMapper = userOtherInfoMapper;
    }

    @Override
    public UserOtherInfo get(Long userId) {
        return userOtherInfoMapper.get(userId);
    }

    @Override
    @Transactional
    public UserOtherInfo create(UserOtherInfo userOtherInfo) {
        userOtherInfo.setCreatedDate(new Date());
        userOtherInfoMapper.add(userOtherInfo);
        return userOtherInfo;
    }

    @Override
    public void update(UserOtherInfo userOtherInfo) {
        Assert.notNull(userOtherInfo, "必须提供用户");
        int affected = userOtherInfoMapper.update(userOtherInfo);
        if (affected == 0) {
            throw new OptimismLockingException("version!!");
        }
        userOtherInfo.setVersion((userOtherInfo.getVersion()+ 1));
    }

    @Override
    public void add(UserOtherInfo other) {
        userOtherInfoMapper.add(other);
    }

    @Override
    public boolean checkOtherInfo(Long userId) {
        UserOtherInfo other = userOtherInfoMapper.get(userId);
        return other!=null && other.getJob()!=null
                && other.getArea()!=null
                && other.getSchool()!=null
                && other.getMajor()!=null
                && other.getEducation()!=null;
    }


}
