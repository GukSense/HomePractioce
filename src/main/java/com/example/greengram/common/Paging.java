package com.example.greengram.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.beans.ConstructorProperties;

@Data
public class Paging {
    @Schema(example = "페이지테스트",description = "페이지값",requiredMode = Schema.RequiredMode.REQUIRED)
    private int page;
    private int size;
    //    private
    @ConstructorProperties({"page","size"})
    public Paging(Integer page, Integer size) {
        this.page = (page == null || page == 0) ? 1 : page;
        this.size = size == null ? 10 : size;
        this.startIdx = (this.page - 1) * this.size;
        this.len = this.size;
    }
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int len;

}