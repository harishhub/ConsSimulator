package com.example.service.sns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SnsSubscriber {

    private String _topic;

    @Autowired
    private AmazonSNS _snsClient;

    public SnsSubscriber()
    { }

    public void subscribe()
    {
        SubscribeRequest request = new SubscribeRequest("arn:aws:sns:us-east-1:123456789012:test-topic","sqs","http://localhost:4576/queue/test-topic-read");
        _snsClient.subscribe(request);
        //System.out.println(_snsClient.getCachedResponseMetadata(request));
    }
}
