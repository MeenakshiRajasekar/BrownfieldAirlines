package com.brownfield.app.service;

import com.brownfield.app.entity.Checkin;
import com.brownfield.app.model.response.CheckinResponse;

public interface CheckinService {

    Checkin saveChecking(Checkin checkin);
    CheckinResponse findByCheckinId(long checkinId);

}
