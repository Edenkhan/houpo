
package com.youruan.dentistry.core.user.domain;

import com.youruan.dentistry.core.base.domain.BasicDomain;
import lombok.Getter;
import lombok.Setter;


/**
 * 注册的用户
 * 
 */
@Getter
@Setter
public class RegisteredUser extends BasicDomain {

    private final static long serialVersionUID = 1L;
    /**
     * 性别
     * 
     */
    protected Integer gender;
    /**
     * 性别：「女」
     * 
     */
    public final static int GENDER_FEMALE = 0;
    /**
     * 性别：「男」
     * 
     */
    public final static int GENDER_MALE = (GENDER_FEMALE + 1);
    /**
     * 用户真实姓名
     */
    protected String realName;
    /**
     * 年龄
     */
    protected Integer age;
    /**
     * 手机号
     * 
     */
    protected String phoneNumber;
    /**
     * 是否已锁定
     * 
     */
    protected Boolean locked;
    /**
     * 微信用户唯一id
     */
    protected String openid;
    /**
     * 微信用户昵称
     */
    protected String nickname;
    /**
     * 头像
     */
    protected String avatar;
    /**
     * 设置性别
     * 
     * @param gender
     *     性别
     */
    public RegisteredUser setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    /**
     * 返回性别
     * 
     * @return
     *     性别
     */
    public Integer getGender() {
        return this.gender;
    }

    /**
     * 性别是否为：「男」
     * 
     * @return
     *     是，为 true；不是，为 false。
     */
    public java.lang.Boolean isMaleGender() {
        if (this.gender == null) {
            return null;
        }
        return (this.gender == GENDER_MALE);
    }

    /**
     * 设置性别为：「男」
     * 
     */
    public RegisteredUser setMaleGender() {
        this.gender = GENDER_MALE;
        return this;
    }

    /**
     * 性别是否为：「女」
     * 
     * @return
     *     是，为 true；不是，为 false。
     */
    public java.lang.Boolean isFemaleGender() {
        if (this.gender == null) {
            return null;
        }
        return (this.gender == GENDER_FEMALE);
    }

    /**
     * 设置性别为：「女」
     * 
     */
    public RegisteredUser setFemaleGender() {
        this.gender = GENDER_FEMALE;
        return this;
    }

}
