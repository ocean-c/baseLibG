package com.luck.luckcloud.framelibrary.entity;

import com.luck.luckcloud.baselibrary.utils.StringUtils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 用户信息接口请求实体
 * Created by fa on 2018/7/25.
 */

public class UserInfoEntity implements Serializable {

    private String token;// 用户Token
    private String realName;// 真实姓名
    private String username;// 账号名
    private String inviteCode; //邀请码
    private int gradeNo; //级别 1、游客-黑旗 2、体验用户-粉旗 3、用户-红旗 4、初级-黄旗 5、中级-绿旗 6、高级-蓝旗 7、社区-紫旗
    private int collectLessonNum;// 收藏课程数量
    private int collectSchoolNum;// 收藏学校数量
    private int collectVideoNum;// 收藏视频数量
    @SerializedName("praiseNum")
    private int zanNum;// 收到的赞的数量
    private String birthday;// 生日
    @SerializedName("mobile")
    private String phone;// 手机号
    @SerializedName("sexText")
    private String gender;// 性别
    @SerializedName("sexCode")
    private String genderCode;// 性别编码
    private String uid;// 用户Id
    @SerializedName("unReadSysNoticeNum")
    private int sysMsgCount;// 系统消息数量
    @SerializedName("imAccount")
    private String IMAccount;// 环信账号
    @SerializedName("cityText")
    private String city;// 所在城市
    @SerializedName("districtText")
    private String district;// 所在区
    private String districtCode;// 区县编码
    private String address;// 具体地址

//    private String grade;// 年级
//    private String gradeCode;// 年级编码
//    private String nickName;// 昵称
//    @SerializedName("hxpassword")
//    private String IMPassword;// 环信密码


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public int getGradeNo() {
        return gradeNo;
    }

    public void setGradeNo(int gradeNo) {
        this.gradeNo = gradeNo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getShowCity() {
        return StringUtils.cleanString(city) + StringUtils.cleanString(district);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getCollectLessonNum() {
        return collectLessonNum;
    }

    public void setCollectLessonNum(int collectLessonNum) {
        this.collectLessonNum = collectLessonNum;
    }

    public int getCollectSchoolNum() {
        return collectSchoolNum;
    }

    public void setCollectSchoolNum(int collectSchoolNum) {
        this.collectSchoolNum = collectSchoolNum;
    }

    public int getCollectVideoNum() {
        return collectVideoNum;
    }

    public void setCollectVideoNum(int collectVideoNum) {
        this.collectVideoNum = collectVideoNum;
    }

    public int getZanNum() {
        return zanNum;
    }

    public void setZanNum(int zanNum) {
        this.zanNum = zanNum;
    }

    public String getUserId() {
        return uid;
    }

    public void setUserId(String userId) {
        this.uid = userId;
    }

    public String getIMAccount() {
        return IMAccount;
    }

    public void setIMAccount(String IMAccount) {
        this.IMAccount = IMAccount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public int getSysMsgCount() {
        return sysMsgCount;
    }

    public void setSysMsgCount(int sysMsgCount) {
        this.sysMsgCount = sysMsgCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //    public String getGradeCode() {
//        return gradeCode;
//    }
//
//    public void setGradeCode(String gradeCode) {
//        this.gradeCode = gradeCode;
//    }
    //
//    public String getGrade() {
//        return grade;
//    }
//
//    public void setGrade(String grade) {
//        this.grade = grade;
//    }
    //    public String getIMPassword() {
//        return IMPassword;
//    }
//
//    public void setIMPassword(String IMPassword) {
//        this.IMPassword = IMPassword;
//    }
//
//    public String getNickName() {
//        return nickName;
//    }
//
//    public void setNickName(String nickName) {
//        this.nickName = nickName;
//    }
}
