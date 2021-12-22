package com.Xolotl.backgroundActions;

public class GenerateNewPassword {
    private int passLength;

    public String generatePassword(int passLength){
        this.passLength = passLength;
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < passLength; i++){
            pass.append((char)((Math.random()*93)+33));
        }
        return String.valueOf(pass);
    }
}
