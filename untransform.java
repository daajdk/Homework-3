/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dan Wegman, Jen Senior, Kevin McCarthy
 */

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class untransform {
  public static void main(String[] args){
    // regular expression for 9x9  \\s[1-9]{3}\\s
    int[][] Matrix = new int[10][10];
    for(int i=0; i<10; i++){
        for(int j=0; j<10; j++){
            Matrix[i][j] = 0;
        }
    }
    String r = "(\\s[1-9]{3}\\s)";
    Pattern pattern = Pattern.compile(r);
    ArrayList<Integer> untrans = new ArrayList<>();
    String line;
        try (BufferedReader br = new BufferedReader (new FileReader (args[0]))) {
            line = br.readLine();
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()){
                System.out.println(matcher.group(1));
                String s = matcher.group(1);
                int row = s.charAt(1)-'0';
                int col = s.charAt(2)-'0';
                int val = s.charAt(3)-'0';
                Matrix[row][col] = val;
            }
        } catch (java.io.IOException e) {}
        System.out.println("******SOLUTION*******");
        for(int i=1; i<10; i++){
            for(int j=1; j<10; j++){
                System.out.print(Matrix[i][j]);
            }
            System.out.print("\n");
        }
  }   
}
