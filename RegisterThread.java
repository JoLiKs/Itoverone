package com.example.expenseapp.helpers;


import org.htmlunit.org.apache.http.HttpResponse;
import org.htmlunit.org.apache.http.client.HttpClient;
import org.htmlunit.org.apache.http.client.methods.HttpUriRequest;
import org.htmlunit.org.apache.http.client.methods.RequestBuilder;
import org.htmlunit.org.apache.http.impl.client.HttpClients;

import java.io.IOException;

import retrofit2.Call;


public class RegisterThread extends Thread {
    public int getStatus() {
        return status;
    }

    private Call<Object> call;
    private RegistrationBody body;
    private int status = 0;
    public RegisterThread(Call<Object> call) {
        this.call = call;
    }
    public RegisterThread(RegistrationBody body) {
        this.body = body;
    }
    @Override
    public void run() {
        super.run();
        try {
            //call.execute();
          HttpUriRequest httpUriRequest1 =
                   RequestBuilder.post("https://kiinauiserver.herokuapp.com/user/register")
                            .addParameter("login", body.login)
                            .addParameter("password", body.password)
                            .addParameter("name", body.name)
                            .addParameter("lastName", body.lastname)
                            .build();
            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse httpResponse = httpClient.execute(httpUriRequest1);
            sleep(1500);
            status = httpResponse.getStatusLine().getStatusCode();
            System.out.println(status);
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
