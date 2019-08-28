package com.syswin.temail.usermail.core.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;

public class ResettableStreamHttpServletRequest extends
    HttpServletRequestWrapper {

    private byte[] rawData;
    private HttpServletRequest request;
    private ResettableStreamHttpServletRequest.ResettableServletInputStream servletStream;

    public ResettableStreamHttpServletRequest(HttpServletRequest request) {
      super(request);
      this.request = request;
      this.servletStream = new ResettableStreamHttpServletRequest.ResettableServletInputStream();
    }


    public void resetInputStream() {
      servletStream.stream = new ByteArrayInputStream(rawData);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
      if (rawData == null) {
        rawData = IOUtils.toByteArray(this.request.getReader(), Charset.defaultCharset());
        servletStream.stream = new ByteArrayInputStream(rawData);
      }
      return servletStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
      if (rawData == null) {
        rawData = IOUtils.toByteArray(this.request.getReader(), Charset.defaultCharset());
        servletStream.stream = new ByteArrayInputStream(rawData);
      }
      return new BufferedReader(new InputStreamReader(servletStream));
    }


    public class ResettableServletInputStream extends ServletInputStream {

      private InputStream stream;

      @Override
      public int read() throws IOException {
        return stream.read();
      }

      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }
    }
  }