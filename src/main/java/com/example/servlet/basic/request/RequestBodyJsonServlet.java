package com.example.servlet.basic.request;

import com.example.servlet.basic.HelloJsonData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName ->
                        System.out.println("request.getParameter(paramName) = " +
                                request.getParameter(paramName)));

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);

        HelloJsonData helloJsonData = objectMapper.readValue(messageBody, HelloJsonData.class);

        System.out.println("helloJsonData = " + helloJsonData.getUsername());
        System.out.println("helloJsonData = " + helloJsonData.getAge());

        response.getWriter().write("ok");
    }
}
