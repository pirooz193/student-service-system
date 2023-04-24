package com.example.studentserviceapplication.domain;

import com.example.studentserviceapplication.domain.enumuration.PanelType;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "banners")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_url", nullable = false, length = 300)
    private String imageUrl;
    @Column(name = "panel_type", nullable = false, length = 20)
    private PanelType panelType;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banner banner = (Banner) o;
        return Objects.equals(id, banner.id) && Objects.equals(imageUrl, banner.imageUrl) && panelType == banner.panelType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageUrl, panelType);
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", panelType=" + panelType +
                '}';
    }

    public PanelType getPanelType() {
        return panelType;
    }

    public void setPanelType(PanelType panelType) {
        this.panelType = panelType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
