package com.zzc.service;

import com.zzc.entity.JsonInfo;

public interface NoteBookService {
    JsonInfo findById(String userId);
}
