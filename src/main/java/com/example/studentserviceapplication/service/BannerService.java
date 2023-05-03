package com.example.studentserviceapplication.service;

import com.example.studentserviceapplication.domain.Banner;

import java.util.List;

public interface BannerService {
    List<Banner> getPanelBanners(String panelType);
}
