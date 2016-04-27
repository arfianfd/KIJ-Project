/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Scanner;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.io.IOException; 

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author ACER
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
  static String IV = "AAAAAAAAAAAAAAAA";
  static String plaintext ; /*Note null padding*/
  static String encryptionKey = "0123456789abcdef";
  public static void main(String [] args) {
       String Name = "";
       BufferedReader dataIn = new BufferedReader(new InputStreamReader(  
  
System.in) );  
      /* Scanner s = new Scanner(System.in);
       Name = s.next();*/
   try {
       plaintext = dataIn.readLine();
       //plaintext = Name;
       
      
     // char[] plaintext;
      System.out.println("==Java==");
      System.out.println("input the plaintext: ");
       
     // plaintext = (char) System.in.read();
            
      
      //System.out.println(plaintext[0]);
      char[] a= plaintext.toCharArray();
       //System.out.println( a[1]);
      System.out.println("plain:   " + plaintext);

      byte[] cipher = encrypt(plaintext, encryptionKey);

      System.out.print("cipher:  ");
      for (int i=0; i<cipher.length; i++)
        System.out.print(new Integer(cipher[i])+" ");
      System.out.println("");

      String decrypted = decrypt(cipher, encryptionKey);

      System.out.println("decrypt: " + decrypted);

   } catch (Exception e) {
      e.printStackTrace();
    } 
  }

  public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return cipher.doFinal(plainText.getBytes("UTF-8"));
  }

  public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception{
    Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return new String(cipher.doFinal(cipherText),"UTF-8");
  }
    
}
