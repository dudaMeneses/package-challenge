package com.mobiquityinc.packer;

import com.mobiquityinc.controller.PackerController;
import com.mobiquityinc.exception.APIException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Packer {

    public static String pack(String filePath) {
        try(Stream<String> stream = Files.lines(Paths.get(filePath))){
            return stream.map(line -> new PackerController().pack(line).toString()).collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new APIException();
        }
    }

}
