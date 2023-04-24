package com.example.studentserviceapplication.web.rest;

import com.example.studentserviceapplication.domain.Banner;
import com.example.studentserviceapplication.service.BannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/banner")
public class BannerResource {

    private final BannerService bannerService;

    public BannerResource(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/slider/{panelType}")
    public ResponseEntity<List<Banner>> getBanners(@PathVariable String panelType) {
        List<Banner> banner = bannerService.getPanelBanners(panelType);
        return ResponseEntity.ok().body(banner);
    }
}
