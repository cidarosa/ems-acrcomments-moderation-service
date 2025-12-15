package com.github.cidarosa.acrcomments.moderation.service.api.controller;

import com.github.cidarosa.acrcomments.moderation.service.api.model.ModerationInputDTO;
import com.github.cidarosa.acrcomments.moderation.service.api.model.ModerationOutputDTO;
import com.github.cidarosa.acrcomments.moderation.service.api.service.ModerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/moderate")
@RequiredArgsConstructor
public class ModerationController {

    private final ModerationService moderationService;

    @PostMapping
    public ModerationOutputDTO get(@RequestBody ModerationInputDTO inputDTO){

        return moderationService.moderateComments(inputDTO);

    }
}
