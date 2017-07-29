package com.zaso.agent.interceptor;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResonseEncryptionWithFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		CharResponseWrapper newResponse = new CharResponseWrapper((HttpServletResponse) response);
		chain.doFilter(request,newResponse);
	
		try {
	
			   Cipher c = Cipher.getInstance("AES");
			   byte[] key = "43381A8E10469C42EFBD6EA0DFD9CA71".getBytes("UTF-8");
			   MessageDigest sha = MessageDigest.getInstance("SHA-1");
			   key = sha.digest(key);
			   key = Arrays.copyOf(key, 16);
				SecretKeySpec k =
				  new SecretKeySpec(key, "AES");
				c.init(Cipher.ENCRYPT_MODE, k);
				byte[] encryptedData = c.doFinal(newResponse.toString().getBytes());
				//response.setBufferSize(0);
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
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
 class CharResponseWrapper extends HttpServletResponseWrapper {
    private CharArrayWriter output;

    public String toString() {
        return output.toString();
    }

    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new CharArrayWriter();
    }

    public PrintWriter getWriter() {
        return new PrintWriter(output);
    }
}
