/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kij_chat_client;

/*import java.net.Socket;*/
import java.util.ArrayList;
import java.util.Scanner;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.io.IOException; 

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author santen-suru
 */
public class Read implements Runnable {
        
        private Scanner in;//MAKE SOCKET INSTANCE VARIABLE
        String input;
        boolean keepGoing = true;
        ArrayList<String> log;
        static String IV = "AAAAAAAAAAAAAAAA";
  static String plaintext ; /*Note null padding*/
  static String encryptionKey = "0123456789abcdef";
  String[] kata;
  String pesan="";
  
	
	public Read(Scanner in, ArrayList<String> log)
	{
		this.in = in;
                this.log = log;
	}
    
        
	public void run()//INHERIT THE RUN METHOD FROM THE Runnable INTERFACE
	{
		try
		{
			while (keepGoing)//WHILE THE PROGRAM IS RUNNING
			{						
				if(this.in.hasNext()) {
                                                                   //IF THE SERVER SENT US SOMETHING
                                        input = this.in.nextLine();
                                        
                                        int x=input.length();
                                        kata= input.split(" ");
                                        //System.out.println(kata.length);
                                        
                                       for(int i=1; i<kata.length; i++)
                                           pesan=pesan.concat(kata[i]);
                                          // System.out.println(kata[i]);
                                          
                                        System.out.println(pesan);
                                          
                                            
                                            
                                        
                                        
                                        
                                     
                                        
                                        
                                        //System.out.println(a[1]);
                                       
                                        //String message2 = String.valueOf(message);
                                        //System.out.println(message2);
                                            
					System.out.println(input);//PRINT IT OUT
                                        if (input.split(" ")[0].toLowerCase().equals("success")) {
                                            if (input.split(" ")[1].toLowerCase().equals("logout")) {
                                                keepGoing = false;
                                            } else if (input.split(" ")[1].toLowerCase().equals("login")) {
                                                log.clear();
                                                log.add("true");
                                            }
                                        }
                                        
                                }
                                
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();//MOST LIKELY WONT BE AN ERROR, GOOD PRACTICE TO CATCH THOUGH
		} 
	}
         public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception{
    Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return new String(cipher.doFinal(cipherText),"UTF-8");
  }
}
