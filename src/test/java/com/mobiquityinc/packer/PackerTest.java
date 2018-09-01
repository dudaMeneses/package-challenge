package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PackerTest {

    @Test(expected = APIException.class)
    public void pack_whenInvalidFilePath_shouldThrowAPIException(){
        Packer.pack("./unexistentpack.txt");
    }

    @Test
    public void pack_whenEmptyFile_shouldReturnEmptyString(){
        String result = Packer.pack("src/test/resources/empty-file.txt");
        assertEquals("", result);
    }

    @Test
    public void pack_whenSingleLinePackage_shouldReturnSingleLineResult(){
        String result = Packer.pack("src/test/resources/single-line.txt");
        assertEquals("4", result);
    }

    @Test(expected = APIException.class)
    public void pack_whenParsingInvalidLine_shouldThrowAPIException(){
        String result = Packer.pack("src/test/resources/invalid-file.txt");
        assertEquals("4", result);
    }

    @Test
    public void pack_whenMultiLinePackage_shouldReturnMultiLineResult(){
        String result = Packer.pack("src/test/resources/multi-line-file.txt");
        assertEquals("4\n-\n2,7\n8,9", result);
    }

}