
package com.youruan.dentistry.core.user.query;

import com.youruan.dentistry.core.base.query.QueryCondition;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class RegisteredUserQuery extends QueryCondition {

    protected Long[] ids;
    protected Date startCreatedDate;
    protected Date endCreatedDate;
    protected String realName;
    protected String likeRealName;
    protected String phoneNumber;
    protected String likePhoneNumber;
    protected Boolean locked;
    protected Integer[] includeGenders;
    protected Integer[] excludeGenders;
    protected String openid;
    protected String nickname;

    public boolean hasIds() {
        return (this.ids.length!= 0);
    }

    public void setOrderById(int keyword) {
        setOrderBy("id", keyword);
    }

    public int getOrderById() {
        return getOrderByKeyword("id");
    }

    public void setOrderByCreatedDate(int keyword) {
        setOrderBy("createdDate", keyword);
    }

    public int getOrderByCreatedDate() {
        return getOrderByKeyword("createdDate");
    }

}
