package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

public class Main {
    public static void main(String[] args) throws APIException {
        System.out.println(Packer.pack(args[0]));
    }
}
