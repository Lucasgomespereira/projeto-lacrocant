package com.lacrocant.lacrocant.application;

import com.lacrocant.lacrocant.domain.admin.Admin;

public interface AdminApplication extends BaseApplication<Admin, String> {
    Admin findByUserName(String userName);
}