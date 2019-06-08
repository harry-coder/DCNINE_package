package com.dc9_master.authentication;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class SessionIdentifierGenerator {
	
	
	 private SecureRandom random = new SecureRandom();

	  public String nextSessionId() {
	    return new BigInteger(12, random).toString(32);
	  }
	}