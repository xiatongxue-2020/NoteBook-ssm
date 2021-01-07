package com.zzc.dao;

import com.zzc.entity.Share;

import java.util.List;

public interface ShareDao {
    void insert(Share share);

    List<Share> findLikeTitle(String keyword, int start);

    Share findById(String shareid);
}
