package com.pruebaitau.demo.commons.domains.generic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDTO
{
    @SerializedName("")
    @Expose
    private List<ItemDTO> list;

    public List<ItemDTO> getList() {
        return list;
    }

    public void setList(List<ItemDTO> list) {
        this.list = list;
    }
}
