package com.example.restApiwebclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
    @Builder
    @AllArgsConstructor
@NoArgsConstructor
    public class Name{
        private String common;
        private String official;
        private NativeName nativeName;

    }

