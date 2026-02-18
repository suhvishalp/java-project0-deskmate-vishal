package com.deskmate.config;

import com.deskmate.controller.DeskController;
import com.deskmate.dao.DeskDao;
import com.deskmate.dao.impl.JdbcDeskDao;
import com.deskmate.services.DeskService;

public class AppConfig {

	public DeskController deskController() {
        DeskDao deskDao = new JdbcDeskDao();
        DeskService deskService = new DeskService(deskDao);
        return new DeskController(deskService);
    }

}
