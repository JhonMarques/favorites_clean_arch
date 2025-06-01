package com.br.api.favorites.data.models;

public class FavoritesPageDocumentModel {

    private String clientId;

    private Integer page;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
