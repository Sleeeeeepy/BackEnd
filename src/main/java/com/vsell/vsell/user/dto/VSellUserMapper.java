package com.vsell.vsell.user.dto;

import com.vsell.vsell.user.domain.VSellUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VSellUserMapper {
    VSellUserDto fromVSellUserToVSellUserDto(VSellUser vSellUser);
}
