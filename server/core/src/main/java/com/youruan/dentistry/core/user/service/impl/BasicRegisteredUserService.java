
package com.youruan.dentistry.core.user.service.impl;

import com.youruan.dentistry.core.base.exception.OptimismLockingException;
import com.youruan.dentistry.core.base.query.Pagination;
import com.youruan.dentistry.core.message.domain.SmsVerification;
import com.youruan.dentistry.core.user.domain.RegisteredUser;
import com.youruan.dentistry.core.user.domain.UserOtherInfo;
import com.youruan.dentistry.core.user.mapper.RegisteredUserMapper;
import com.youruan.dentistry.core.user.query.RegisteredUserQuery;
import com.youruan.dentistry.core.user.service.RegisteredUserService;
import com.youruan.dentistry.core.user.service.UserOtherInfoService;
import com.youruan.dentistry.core.user.vo.ExtendedRegisteredUser;
import com.youruan.dentistry.core.user.vo.UserAllInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BasicRegisteredUserService
    implements RegisteredUserService
{

    private final RegisteredUserMapper registeredUserMapper;
    private final UserOtherInfoService userOtherInfoService;

    public BasicRegisteredUserService(RegisteredUserMapper registeredUserMapper, UserOtherInfoService userOtherInfoService) {
        this.registeredUserMapper = registeredUserMapper;
        this.userOtherInfoService = userOtherInfoService;
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
        List<ExtendedRegisteredUser> data = ((rows == 0)?new ArrayList<>():registeredUserMapper.query(qo));
        return new Pagination<>(rows, data);
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

    @Override
    @Transactional
    public void modify(RegisteredUser user, String major, String school, Integer education, String job, String area) {
        registeredUserMapper.update(user);
        UserOtherInfo other = userOtherInfoService.get(user.getId());
        if(other == null) {
            other = new UserOtherInfo();
            setProperties(major, school, education, job, area, other);
            other.setCreatedDate(new Date());
            other.setUserId(user.getId());
            userOtherInfoService.add(other);
        }else{
            setProperties(major, school, education, job, area, other);
            userOtherInfoService.update(other);
        }

    }

    private void setProperties(String major, String school, Integer education, String job, String area, UserOtherInfo other) {
        other.setMajor(major);
        other.setSchool(school);
        other.setEducation(education);
        other.setJob(job);
        other.setArea(area);
    }


    @Override
    public Pagination<UserAllInfoVo> queryAll(RegisteredUserQuery qo) {
        int rows = registeredUserMapper.count(qo);
        List<UserAllInfoVo> data = ((rows == 0)?new ArrayList<>():registeredUserMapper.queryAll(qo));
        return new Pagination<>(rows, data);
    }

    @Override
    public boolean checkCompleteInfo(RegisteredUser user) {
        return user.getRealName()!=null && userOtherInfoService.checkOtherInfo(user.getId());
    }

    @Override
    public void bindPhone(SmsVerification smsVerification, String phone, String verificationCode, Long userId) {
        this.checkParam(smsVerification,phone,verificationCode);
        Assert.isTrue(smsVerification.getCode().equals(verificationCode),"验证码错误");
        // 绑定手机号
        RegisteredUser user = registeredUserMapper.get(userId);
        Assert.notNull(user,"必须提供用户");
        user.setPhoneNumber(phone);
        registeredUserMapper.update(user);
    }

    private void checkParam(SmsVerification smsVerification, String phone, String verificationCode) {
        Assert.notNull(smsVerification,"必须提供验证信息");
        Assert.notNull(phone,"必须提供手机号");
        Assert.notNull(verificationCode,"必须提供验证码");
    }

}
