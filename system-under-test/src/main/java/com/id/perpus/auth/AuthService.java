package com.id.perpus.auth;

import com.id.perpus.common.Response;

public interface AuthService {
	Response doLogin(String username, String password);
	Response doForgot(String insertemail);
}
