
package com.youruan.dentistry.core.user.service;

import com.youruan.dentistry.core.user.domain.UserOtherInfo;

public interface UserOtherInfoService {


    public UserOtherInfo get(Long userId);

    UserOtherInfo create(UserOtherInfo userOtherInfo);

    void update(UserOtherInfo userOtherInfo);

    void add(UserOtherInfo other);

    /**
     * 检查用户信息是否完整
     */
    boolean checkOtherInfo(Long userId);
}
