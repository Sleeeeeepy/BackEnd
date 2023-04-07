package com.vsell.vsell.user.presentation;


import com.vsell.vsell.response.ResponseStatusType;
import com.vsell.vsell.security.JwtProvider;
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

@RestController
@RequestMapping("/users")
public class UserInformationController {

    private final InformationModifyService informationModifyService;
    private final ProfileHandlerService profileHandlerService;
    private final UserSecurityService userSecurityService;
    private final JwtProvider jwtProvider;

    public UserInformationController(InformationModifyService informationModifyService, ProfileHandlerService profileHandlerService, UserSecurityService userSecurityService, JwtProvider jwtProvider) {
        this.informationModifyService = informationModifyService;
        this.profileHandlerService = profileHandlerService;
        this.userSecurityService = userSecurityService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping
    public ResponseEntity<VSellUserResponseDto> modifyUserInformation(HttpServletRequest request, @RequestBody InformationModifyDto informationModifyDto){
        String email = userSecurityService.getLoginUser();

        if(!email.equals(informationModifyDto.getEmail())){
            throw new CustomUserException(UserExceptionType.FORBIDDEN);
        }

        VSellUser user = informationModifyService.changeUserInformation(informationModifyDto);

        VSellUserDto vSellUserDto = mapVSellUserToDto(user);
        VSellUserResponseDto response = new VSellUserResponseDto();
        response.setData(vSellUserDto);
        response.setStatus(ResponseStatusType.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/profile")
    public ResponseEntity<VSellUserResponseDto> modifyUserProfile(HttpServletRequest request, @RequestPart MultipartFile profile, @RequestPart String email){
        String tokenEmail = userSecurityService.getLoginUser();

        if(!tokenEmail.equals(email)){
            throw new CustomUserException(UserExceptionType.FORBIDDEN);
        }

        VSellUser user = informationModifyService.changeProfile(profile, email);

        VSellUserDto vSellUserDto = mapVSellUserToDto(user);
        VSellUserResponseDto response = new VSellUserResponseDto();
        response.setData(vSellUserDto);
        response.setStatus(ResponseStatusType.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Resource> getProfile(@RequestParam String email){
        ProfileDto profile = profileHandlerService.getProfile(email);


        return ResponseEntity.ok().contentType(profile.getProfileType().getMediaType()).body(profile.getProfile());
    }


    private VSellUserDto mapVSellUserToDto(VSellUser user){
        VSellUserDto vSellUserDto = new VSellUserDto();
        vSellUserDto.setBirthDate(user.getBirthDate());
        vSellUserDto.setEmail(user.getEmail());
        vSellUserDto.setNickName(user.getNickName());

        return vSellUserDto;
    }
}
