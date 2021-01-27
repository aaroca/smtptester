package com.aaroca.smtptester.services;

import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.data.ResponseData;

public interface EmailService {
  ResponseData send(EmailData email);
}
