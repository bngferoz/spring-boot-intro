package com.springFeroz.app.ws.userservice;

import com.springFeroz.app.ws.ui.model.request.UserDetailsRequestModel;
import com.springFeroz.app.ws.ui.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);
}
