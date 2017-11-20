# HNAP Client

[![Build Status](https://travis-ci.org/jbuncle/hnap-client.svg?branch=master)](https://travis-ci.org/jbuncle/hnap-client)
[![codecov.io](https://codecov.io/github/jbuncle/hnap-client/coverage.svg?branch=master)](https://codecov.io/github/jbuncle/hnap-client?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/62ce9ec96f0f4e3085661d69a5ca86e3)](https://www.codacy.com/app/jbuncle/hnap-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jbuncle/hnap-client&amp;utm_campaign=Badge_Grade)

Java Library for interacting with devices using the [Home Network Administration Protocol (HNAP)](https://en.wikipedia.org/wiki/Home_Network_Administration_Protocol).

# Installation

This library isn't currently available in standard repositories, however it can
be used via https://jitpack.io/. 

The Maven setup is:

```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
        ...
	<dependency>
	    <groupId>com.github.jbuncle</groupId>
	    <artifactId>master</artifactId>
	    <version>master</version>
	</dependency>
```