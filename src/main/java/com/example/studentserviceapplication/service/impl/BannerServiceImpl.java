package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.Banner;
import com.example.studentserviceapplication.repository.BannerRepository;
import com.example.studentserviceapplication.service.BannerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    public BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public List<Banner> getPanelBanners(String panelType) {
        List<Banner> banners = bannerRepository.findAll().stream()
                .filter(banner -> banner.getPanelType().name().equals(panelType))
                .collect(Collectors.toList());
        return banners;
    }
}
