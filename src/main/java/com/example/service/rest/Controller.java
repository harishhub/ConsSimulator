package com.example.service.rest;

import com.example.service.Entities.AssetEntity;
import com.example.service.domain.EventMessage;
import com.example.service.sns.SnsPublisher;
import com.example.service.sns.SnsSubscriber;
import com.example.service.sqs.SqsPublisher;
import com.example.service.sqs.SqsSubscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
public class Controller {

    @Autowired
    SqsPublisher sqsPublisher;

    @Autowired
    SqsSubscriber sqsSubscriber;


    @Autowired
    SnsPublisher snsPublisher;

    @Autowired
    SnsSubscriber snsSubscriber;

    @RequestMapping(path = "/sqs/publish", method = RequestMethod.GET)
    public void triggerSqsPublish(@RequestParam String foo) {

        sqsPublisher.send(EventMessage.builder()
                .foo(foo)
                .build());

    }

    @RequestMapping(path = "/sns/publish", method = RequestMethod.GET)
    public void triggerSnsPublish(@RequestParam String foo) {

        System.out.println("@@@@@@ MESSAGE TO BE SENT IS " + foo);

        snsPublisher.send(EventMessage.builder().foo(foo).build());

        //sqsSubscriber.subscribeToSQSDirectly()
        //snsSubscriber.subscribe();
    }

    @RequestMapping(path = "/snshello", method = RequestMethod.GET)
    public String snsHello() {
        return "SNS SAYS HELLO";
    }

    @RequestMapping(path = "/deviceDataCons", method = RequestMethod.POST)
    public void deviceCons(@RequestBody AssetEntity consDevice,@RequestParam String eventType) {
        System.out.println(" @@@@@@ POSTED DATA @@@@@@@");
        System.out.println(consDevice);

        consDevice.setCreatedAt(Utilities.getCurrentUTCZoneTime());
        consDevice.setEaps("eaps");
        consDevice.setOsVersion("Android OREO");
        consDevice.setPlatform("Android Google");
        consDevice.setAssetId(UUID.randomUUID());

        snsPublisher.sendAsset(consDevice, ActionType.fromString(eventType));
    }




}
