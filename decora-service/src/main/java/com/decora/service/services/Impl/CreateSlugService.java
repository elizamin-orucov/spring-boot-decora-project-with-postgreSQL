package com.decora.service.services.Impl;

import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Locale;

public class CreateSlugService {

    static String createSlug(String input){
        if (input == null || input.isBlank()){
            return "";
        }

        String slug = input.toLowerCase(Locale.ENGLISH);

        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = slug.replaceAll("\\p{M}", "");

        slug = slug.replaceAll("[^a-z0-9\\s]", "");

        return slug.trim().replaceAll("\\s+", "-");
    }


}
