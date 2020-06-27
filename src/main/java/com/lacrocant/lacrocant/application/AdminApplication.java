package com.lacrocant.lacrocant.application;

import com.lacrocant.lacrocant.domain.admin.Admin;
import com.lacrocant.lacrocant.util.LaCrocanteException;

public interface AdminApplication extends BaseApplication<Admin, String> {
    Admin findByUserName(String userName);
    Admin blockOrUnblock(String id, String loggedAdmin) throws LaCrocanteException;
}