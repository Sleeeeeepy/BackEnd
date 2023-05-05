package com.vsell.vsell.user.presentation;


import com.vsell.vsell.response.ResponseStatusType;
import com.vsell.vsell.security.UserSecurityService;
import com.vsell.vsell.user.application.InformationModifyService;
import com.vsell.vsell.user.application.ProfileHandlerService;
import com.vsell.vsell.user.domain.VSellUser;
import com.vsell.vsell.user.domain.exception.CustomUserException;
import com.vsell.vsell.user.domain.exception.UserExceptionType;
import com.vsell.vsell.user.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserInformationController {

    private final InformationModifyService informationModifyService;
    private final ProfileHandlerService profileHandlerService;
    private final UserSecurityService userSecurityService;
    private final VSellUserMapper vSellUserMapper;

    public UserInformationController(InformationModifyService informationModifyService, ProfileHandlerService profileHandlerService, UserSecurityService userSecurityService, VSellUserMapper vSellUserMapper) {
        this.informationModifyService = informationModifyService;
        this.profileHandlerService = profileHandlerService;
        this.userSecurityService = userSecurityService;
        this.vSellUserMapper = vSellUserMapper;
    }

    @PostMapping
    public ResponseEntity<VSellUserResponseDto> modifyUserInformation(HttpServletRequest request, @RequestBody InformationModifyDto informationModifyDto) {
        Optional<String> email = userSecurityService.getLoginUser();

        if (!email.get().equals(informationModifyDto.getEmail())) {
            throw new CustomUserException(UserExceptionType.FORBIDDEN);
        }

        VSellUser user = informationModifyService.changeUserInformation(informationModifyDto);

        VSellUserDto vSellUserDto = vSellUserMapper.fromVSellUserToVSellUserDto(user);
        VSellUserResponseDto response = new VSellUserResponseDto();
        response.setData(vSellUserDto);
        response.setStatus(ResponseStatusType.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/profile")
    public ResponseEntity<VSellUserResponseDto> modifyUserProfile(HttpServletRequest request, @RequestPart MultipartFile profile, @RequestPart String email) {
        Optional<String> tokenEmail = userSecurityService.getLoginUser();

        if (!tokenEmail.get().equals(email)) {
            throw new CustomUserException(UserExceptionType.FORBIDDEN);
        }

        VSellUser user = informationModifyService.changeProfile(profile, email);

        VSellUserDto vSellUserDto = vSellUserMapper.fromVSellUserToVSellUserDto(user);
        VSellUserResponseDto response = new VSellUserResponseDto();
        response.setData(vSellUserDto);
        response.setStatus(ResponseStatusType.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Resource> getProfile(@RequestParam String email) {
        ProfileDto profile = profileHandlerService.getProfile(email);


        return ResponseEntity.ok().contentType(profile.getProfileType().getMediaType()).body(profile.getProfile());
    }

}
