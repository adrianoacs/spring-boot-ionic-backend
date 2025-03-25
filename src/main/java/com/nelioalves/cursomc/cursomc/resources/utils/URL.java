package com.nelioalves.cursomc.cursomc.resources.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {



    public static List<Integer> decodeIntList(String s){
        if(s == null || s.isEmpty()){
            return List.of();
        }
        return Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static String decodeParam(String nome) {
        if(nome == null){
            return null;
        }
        return URLDecoder.decode(nome, StandardCharsets.UTF_8);
    }
}
