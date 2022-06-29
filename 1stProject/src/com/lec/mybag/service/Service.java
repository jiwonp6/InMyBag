package com.lec.mybag.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {
	void execute(HttpServletRequest request, HttpServletResponse response);
}
