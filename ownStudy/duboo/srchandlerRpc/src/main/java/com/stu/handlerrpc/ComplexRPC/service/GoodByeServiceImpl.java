package com.stu.handlerrpc.ComplexRPC.service;

public class GoodByeServiceImpl implements GoodByeService {
    @Override
    public String sayGoodbye(Person person) {
        return "GoodBye :"+person;
    }
}
