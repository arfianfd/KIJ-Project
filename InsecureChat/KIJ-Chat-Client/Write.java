/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kij_chat_client;

import java.io.PrintWriter;
import java.net.Socket;
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
public class Write implements Runnable {
    
	private final Scanner chat;
        private final PrintWriter out;
        boolean keepGoing = true;
        ArrayList<String> log;
        static String IV = "AAAAAAAAAAAAAAAA";
        //static String plaintext ; /*Note null padding*/
        static String encryptionKey = "0123456789abcdef";
        
	
	public Write(Scanner chat, PrintWriter out, ArrayList<String> log)
	{
		this.chat = chat;
                this.out = out;
                this.log = log;
	}
	
	@Override
	public void run()//INHERIT THE RUN METHOD FROM THE Runnable INTERFACE
	{
            
		try
		{
			while (keepGoing)//WHILE THE PROGRAM IS RUNNING
			{
                                String kalimat="";
                                String b2="";
                                String strtosend="";
				String input = chat.nextLine();	//SET NEW VARIABLE input TO THE VALUE OF WHAT THE CLIENT TYPED IN
                                char[] a= input.toCharArray();
                                if (input.split(" ")[0].toLowerCase().equals("login") == true)
                                { 
                                    out.println(input);//SEND IT TO THE SERVER
                                    out.flush();//FLUSH THE STREAM
                                }
                                else if (input.split(" ")[0].toLowerCase().equals("logout") == true)
                                { 
                                    out.println(input);//SEND IT TO THE SERVER
                                    out.flush();//FLUSH THE STREAM
                                }
                                else if (input.split(" ")[0].toLowerCase().equals("cg") == true)
                                { 
                                    out.println(input);//SEND IT TO THE SERVER
                                    out.flush();//FLUSH THE STREAM
                                }
                                else if (input.split(" ")[0].toLowerCase().equals("bm") == true)
                                {
                                    String[] parts= input.split(" ");
                                    int byk=parts.length;
                                    for (int i=1;i<byk;i++)
                                    {
                                        kalimat = kalimat.concat(parts[i]+" ");
                                    }
                                    byte[] cipher = encrypt(kalimat, encryptionKey);
                                    for(int i=0;i<cipher.length;i++){
                                        strtosend+=(char)cipher[i];
                                      }//kalimat di b2 nantinya diganti dengan str to send
                                    
                                    b2=b2.concat(parts[0]+" "+strtosend+" "+Integer.toString(cipher.length));
                                    out.println(b2);//SEND IT TO THE SERVER
                                    out.flush();//FLUSH THE STREAM
                                }
                                else if(input.split(" ")[0].toLowerCase().equals("pm") == true)
                                {
                                    String[] parts= input.split(" ");
                                    int byk=parts.length;
                                    for (int i=2;i<byk;i++)
                                    {
                                        kalimat = kalimat.concat(parts[i]+" ");
                                    }
                                    byte[] cipher = encrypt(kalimat, encryptionKey);
                                     for(int i=0;i<cipher.length;i++){
                                        strtosend+=(char)cipher[i];
                                      }//kalimat di b2 nantinya diganti dengan str to send
                                    
                                    b2=b2.concat(parts[0]+" "+parts[1]+" "+strtosend+" "+Integer.toString(cipher.length));
                                    //System.out.println(b2);
                                    out.println(b2);//SEND IT TO THE SERVER
                                    out.flush();//FLUSH THE STREAM
                                } 
                                
                                if (input.contains("logout")) {
                                    if (log.contains("true"))
                                        keepGoing = false;
                                    
                                }
			}
		}
		catch (Exception e)
		{
		} 
	}
    public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return cipher.doFinal(plainText.getBytes("UTF-8"));
  }

}
