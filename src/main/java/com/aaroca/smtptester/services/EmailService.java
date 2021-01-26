package com.aaroca.smtptester.services;

import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.data.ResultingData;

public interface EmailService {
  ResultingData send(EmailData email);
}
