package com.arexe.bgames.utils;

public interface EmailSender {

    void sendMessage(String to, String subject, String text);
}
