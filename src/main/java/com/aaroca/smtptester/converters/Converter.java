package com.aaroca.smtptester.converters;

public interface Converter<SOURCE, TARGET> {
  TARGET convert(SOURCE source);
}
