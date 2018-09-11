package com.amarnehsoft.harritask.network.weatherApi.response;

import java.util.List;

public class GetWeatherResponse {

    private List<Data> list;

    public List<Data> getList() {
        return list;
    }

    public void setList(List<Data> list) {
        this.list = list;
    }
}
