package com.pngabo.affrontementapi.exceptions;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException() {
        super("Cet element n'existe pas!");
    }
}
