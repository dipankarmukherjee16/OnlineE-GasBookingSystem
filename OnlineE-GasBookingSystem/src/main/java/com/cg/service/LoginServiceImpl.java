package com.cg.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.dao.ILoginDao;
import com.cg.entity.Login;
import com.cg.exception.LoginException;
import com.cg.util.LoginConstants;

/*
 * Created By Titas Sarkar
 */
@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginDao logindao;

	Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	Map<String, Login> authMap = new HashMap<>();

	/*
	 * Method for logging into an account
	 */
	@Override
	public Login doLogin(Integer userId, String password) throws LoginException {
		Login login = null;
		logger.debug("doing login process");
		Optional<Login> optLogin = logindao.findById(userId);
		if (!optLogin.isPresent())
			throw new LoginException(LoginConstants.CHECK_YOUR_CREDENTIALS);
		login = optLogin.get();
		if (!login.getPassword().contentEquals(encryptString(password)))
			throw new LoginException(LoginConstants.PASSWORD_WRONG);
		return login;
	}

	/*
	 * Method to put the token into Map
	 */
	public String generateToken(Login login) {
		String token = encryptLogin(login);
		authMap.put(token, login);
		return token;
	}

	/*
	 * getter method for Map
	 */
	public Map<String, Login> getAuthMap() {
		return authMap;
	}

	/*
	 * Method to encrypt password
	 */
	@Override
	public String encryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		int ch;
		for (int idx = 0; idx < arr.length; idx++) {
			ch = arr[idx] + 3;
			sb.append((char) ch);
		}
		return sb.toString();
	}

	/*
	 * Method to decrypt password
	 */
	@Override
	public String decryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		int ch;
		for (int idx = 0; idx < arr.length; idx++) {
			ch = arr[idx] - 3;
			sb.append((char) ch);
		}
		return sb.toString();
	}

	/*
	 * Method to generate encrypted token id
	 */
	@Override
	public String encryptLogin(Login loginAcnt) {
		return encryptString(loginAcnt.getUserId().toString()) + "-" + encryptString(loginAcnt.getRole());
	}

	public boolean verifyLogin(String tokenId) throws LoginException {
		if (!authMap.containsKey(tokenId)) {
			throw new LoginException(LoginConstants.INVALID_LOGIN_TOKEN);
		}
		return true;
	}
	

}
