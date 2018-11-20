# HNAP Client

[![Release](https://jitpack.io/v/jbuncle/hnap-client.svg)](https://jitpack.io/#jbuncle/hnap-client)
[![Build Status](https://travis-ci.org/jbuncle/hnap-client.svg?branch=master)](https://travis-ci.org/jbuncle/hnap-client)
[![codecov.io](https://codecov.io/github/jbuncle/hnap-client/coverage.svg?branch=master)](https://codecov.io/github/jbuncle/hnap-client?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/62ce9ec96f0f4e3085661d69a5ca86e3)](https://www.codacy.com/app/jbuncle/hnap-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jbuncle/hnap-client&amp;utm_campaign=Badge_Grade)

Java Library for interacting with devices using the [Home Network Administration Protocol (HNAP)](https://en.wikipedia.org/wiki/Home_Network_Administration_Protocol).

## Usage

```
    public static void main(String[] args) {
        try {
            final String host = "192.168.1.11"; // The HNAP device's host or IP address
            final String username = "admin"; // Username is normally 'admin'
            final String password = "***"; // Your device password goes here

            // Create HnapClient
            final ClientBuilder clientBuilder = new ClientBuilder();
            final HnapClientI client = clientBuilder.createHnapClient(host, username, password);

            // Discover is part of the HNAP protocol - which is a simple "GET" to HNAP1 path
            // This should tell you the devices capabilities
            // Some devices I tried returned a blank response to this, so don't be too put of if this part didn't work
            final DeviceSettingsI results = client.discover();
            System.out.println(results.toString());


            // Perform a login request (which is a couple of requests) so we can perform requests needing authentication
            final HnapSessionI session = client.login();
            // Return a mixed type map because most responses are basically JSON style structures
            final Map<String, Object> isDeviceReady = client.request(session, "GetDeviceSettings", new LinkedHashMap<>());
            System.out.println(isDeviceReady);
        }
        catch (MalformedURLException | HnapClientException ex) {
            Logger.getLogger(BasicHttpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
```

## Installation

This library can be installed using JitPack (which supports Maven & Grade)
https://jitpack.io/#jbuncle/hnap-client, as it isn't in standard repositories.