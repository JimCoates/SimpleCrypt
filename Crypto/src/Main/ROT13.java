import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class ROT13  {
    private Integer shift;
    String smallRotated;
    String capsRotate;
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String caphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    ROT13(Character cs, Character cf) {
        shift = Math.abs(cs - cf);
        smallRotated = rotate(alphabet,cf);
        Character CF = Character.toUpperCase(cf);
        capsRotate = rotate(caphabet,Character.toUpperCase(CF));
    }

    ROT13() {
        this('a','n');
    }


    public String crypt(String text) throws UnsupportedOperationException {
       return encrypt(text);
    }

    public String encrypt(String text) {
        StringBuilder builder = new StringBuilder();
        char[] temp = text.toCharArray();

        Integer index = 0;
        for (int i = 0; i < temp.length; i++) {
            if(temp[i] == ' ' || temp[i] == '?' || temp[i] == '!'){
                builder.append(temp[i]);
            }
            if(alphabet.indexOf(temp[i]) != -1 ) {
                index = alphabet.indexOf(temp[i]);
                builder.append(this.smallRotated.charAt(index));
            }
            if(caphabet.indexOf(temp[i]) != -1 ){
                index = caphabet.indexOf(temp[i]);
                builder.append(this.capsRotate.charAt(index));
            }
        }
        return builder.toString();
    }

    public String decrypt(String text) {
        this.smallRotated = rotate(smallRotated, 'n');
        this.capsRotate = rotate(capsRotate,'N');

        return encrypt(text);
    }

    public static String rotate(String s, Character c) {
        Integer index = s.indexOf(c);
        return s.substring(index) + s.substring(0,index);
    }
}
