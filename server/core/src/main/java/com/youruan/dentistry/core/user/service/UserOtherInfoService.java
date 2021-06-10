
package com.youruan.dentistry.core.user.service;

import com.youruan.dentistry.core.user.domain.UserOtherInfo;

public interface UserOtherInfoService {


    public UserOtherInfo get(Long userId);

    UserOtherInfo create(UserOtherInfo userOtherInfo);

    void update(UserOtherInfo userOtherInfo);
    
}
