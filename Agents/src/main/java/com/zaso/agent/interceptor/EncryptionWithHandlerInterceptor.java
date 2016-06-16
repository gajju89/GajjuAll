package com.zaso.agent.interceptor;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class EncryptionWithHandlerInterceptor implements HandlerInterceptor {
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("you are not autherised thanks!");

		
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			
			   Cipher c = Cipher.getInstance("AES");
			   byte[] key = "43381A8E10469C42EFBD6EA0DFD9CA71".getBytes("UTF-8");
			   MessageDigest sha = MessageDigest.getInstance("SHA-1");
			   key = sha.digest(key);
			   key = Arrays.copyOf(key, 16);
				SecretKeySpec k =
				  new SecretKeySpec(key, "AES");
				c.init(Cipher.ENCRYPT_MODE, k);
				byte[] encryptedData = c.doFinal(response.toString().getBytes());
				response.reset();
				response.getOutputStream().write(encryptedData);
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
		
	}
	
	

}
