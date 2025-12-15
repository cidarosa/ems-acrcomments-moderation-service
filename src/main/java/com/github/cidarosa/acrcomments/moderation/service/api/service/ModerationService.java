package com.github.cidarosa.acrcomments.moderation.service.api.service;

import com.github.cidarosa.acrcomments.moderation.service.api.model.ModerationInputDTO;
import com.github.cidarosa.acrcomments.moderation.service.api.model.ModerationOutputDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class ModerationService {

    private static final Set<String> PROHIBITED_WORDS = new HashSet<>(
            Arrays.asList("ódio", "xingamento")
    );

    public ModerationOutputDTO moderateComments(ModerationInputDTO inputDTO) {

        String text = inputDTO.getText();

        String normalizedText = text.toLowerCase()
                .replaceAll("[^a-z0-9áéíóúçàèìòùãõâêîôû ]", " ");

        // Split to individual words
        String[] words = normalizedText.split("\\s+");
        Set<String> wordsFound = new HashSet<>();
        // Check
        for (String word : words) {
            if (PROHIBITED_WORDS.contains(word)) {
                wordsFound.add(word);
            }
        }

        if(!wordsFound.isEmpty()){
            return  ModerationOutputDTO.builder()
                    .approved(false)
                    .reason("Contém a(s) palavra(s) proibida(s): " + wordsFound)
                    .build();
        }

        return ModerationOutputDTO.builder()
                .approved(true)
                .reason("Comentário aprovado não contém palavras proibidas")
                .build();


    }
}
