package com.example.service.sns;

import com.example.service.Entities.AssetEntity;
import com.example.service.domain.EventMessage;
import com.example.service.rest.ActionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class SnsPublisher {

    @Autowired
    private NotificationMessagingTemplate notificationMessagingTemplate;

    private String snsTopic = "demo1";

    public void send(EventMessage msg) {
        this.notificationMessagingTemplate.sendNotification(snsTopic, msg, "subject");
    }

    public void sendAsset(AssetEntity assetEntity, ActionType action) {

        if (assetEntity != null) {
            String deviceEntityStr = getDeviceClassificationDetails(assetEntity);
            if (deviceEntityStr != null) {
                this.notificationMessagingTemplate.sendNotification(snsTopic, deviceEntityStr, "subject");
                Map<String, Object> metadata = new HashMap<>();
                metadata.put("version", "1.0");
                metadata.put("messageId", UUID.randomUUID().toString());

                metadata.put("createdAt", DateTime.now()
                        .withZone(DateTimeZone.UTC).toString());
                metadata.put("event", action.name().toLowerCase());
                //publish to topic
                notificationMessagingTemplate.convertAndSend(snsTopic, deviceEntityStr, metadata);
            }

        }

    }

    private String getDeviceClassificationDetails(AssetEntity assetEntity) {


        ObjectMapper mapper = new ObjectMapper();
        String deviceDetailString = null;
        try {
            deviceDetailString = mapper.writeValueAsString(assetEntity);
            log.info(("==>  deviceDetailString :" + deviceDetailString));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return deviceDetailString;
    }

}
