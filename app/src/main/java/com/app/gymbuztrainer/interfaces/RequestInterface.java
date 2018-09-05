package com.app.gymbuztrainer.interfaces;

import com.app.gymbuztrainer.entities.AllRequestSupportEnt;

public interface RequestInterface {

    void onRequestClick(AllRequestSupportEnt entity, int position);
    void onJobDoneClick(AllRequestSupportEnt entity, int position);
}
