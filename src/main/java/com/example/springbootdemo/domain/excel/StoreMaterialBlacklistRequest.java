package com.example.springbootdemo.domain.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StoreMaterialBlacklistRequest {
    private List<String> k3storeCodes;
    private List<String> materialCodes;
    private String comment;
}
