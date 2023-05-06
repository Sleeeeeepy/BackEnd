package com.vsell.vsell.auction.dto;

import com.vsell.vsell.response.ResponseStatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuctionRegisterResponseDto {
    private String status;

    private Data data = new Data();


    public void setStatus(ResponseStatusType responseStatusType) {
        this.status = responseStatusType.getStatus();
    }

    public void setAuctionId(long id) {
        data.setAuctionId(id);
    }

    @Getter
    @Setter
    public static class Data {
        private Long auctionId;
    }
}
