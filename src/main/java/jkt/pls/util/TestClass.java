package jkt.pls.util;

import java.util.UUID;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0; i<10; i++) {
			UUID uuid = UUID.randomUUID();
			System.out.println("Generated UUID: " + uuid);
		}
		
	}

}
