package com.pm.billing_service.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {
    @Override
    public void createBillingAccount(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {

        BillingResponse response = BillingResponse.newBuilder().setAccountId("12345").setStatus("ACTIVE").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
