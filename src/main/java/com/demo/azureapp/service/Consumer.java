package com.demo.azureapp.service;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;
import com.azure.messaging.eventhubs.models.EventPosition;

public class Consumer {

    private static final String connectionString = "Endpoint=sb://dinaraappnamespace.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=QMnSgIkVErsVars7+8Vyz6FwomlsjBT4p+AEhDh7sFw=";
    private static final String eventHubName = "producer";

    public static void consume() throws InterruptedException {
        EventHubConsumerAsyncClient consumer = new EventHubClientBuilder()
                .connectionString(connectionString, eventHubName)
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .buildAsyncConsumerClient();
        consumer.receiveFromPartition("0", EventPosition.latest()).subscribe(event -> System.out.println("Partition id = " + event.getPartitionContext().getPartitionId() + " and "
                + "sequence number of event = " + event.getData().getSequenceNumber()));
    }

}
