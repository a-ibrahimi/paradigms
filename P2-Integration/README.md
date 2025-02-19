# Integration: Service Oriented Model / RPC Paradigm
## Table of Cotents
- [Problem](#problem)
- [Objective](#objective)
- [Examples](#examples)
- [Readings](#readings)
- [Brainstorming](#brainstorming)
- [Solution: RPC Paradigm - Remote Procedure Call](#solution-rpc-paradigm---remote-procedure-call)
- [History](#history)
- [Key Concepts](#key-concepts)
  - [Service API](#service-api)
  - [Service provider](#service-provider)
  - [Service consumer](#service-consumer)
- [Runtime Flow](#runtime-flow)
- [Development Process](#development-process)
  - [API-first Approach](#api-first-approach)
  - [Code-first Approach](#code-first-approach)
- [Technologies](#technologies)
  - [Programming Language-Specific Technologies](#programming-language-specific-technologies)
  - [Programming Language-Agnostic Technologies](#programming-language-agnostic-technologies)
    - [CORBA (legacy) - Common Object Request Broker Architecture](#corba-legacy---common-object-request-broker-architecture)
    - [XML/SOAP (XS) Web Services](#xmlsoap-xs-web-services)
    - [RESTful (RS) Web Services](#restful-rs-web-services)
    - [Other Web Services (Non XML/SOAP, RESTless)](#other-web-services-non-xmlsoap-restless)

## Problem
The client/server model allows different processes to communicate remotely. However, designing and developing a client/server application is tedious. Indeed, it requires:
- Designing a specific protocol or, in the best case, adopting and adapting an existing (maybe standard) protocol
- Managing connections, as well as corresponding streams, on both sides
- Implementing the protocol, including (application) error management, on both sides

Also, the more services/functionality should be provided, the more tedious the whole process becomes!

## Objective
Can we imagine a programming paradigm, which provides us software developers, with *the luxury of invoking remote services/functionality, as if they were local*!?

Such a paradigm should hide all the programming hassle and details mentioned above. By doing so, it would:
- increase *devloper productivity*
- promote *software integration* for:
  - richer functionality
  - higher performance

## Examples
- Getting the weather of a given city from a specialized provider, such as Yahoo! Weather (integration for richer functionality): you don't have the necessary data to do it yourself!
- Translating text using a specialized service, such as Google Translate (integration for richer functionality): you don't have the algorithms and data to do it yourself!
- Inversing large matrices using a specialized service (integration for higher performance): you may not have the necessary processing power!

*As a matter of fact, cloud computing wouldn't be possible without such a paradigm!*

## Readings
You must absolutely familiarize yurself with the following:
- Serialization
- XML: eXtensible Markup Language
- JSON: JavaScript Object Notation

## Brainstorming
If you were to design and develop a traditional client/server application that allows the client to perform the four basic math operations (+ - x :) on the server,
- How would you go about it?
- What parts of your solution are really specific to this application?
- What parts of your solution could be generalized and used for other purposes and applications?

## Solution: RPC Paradigm - Remote Procedure Call
<p align="center"><img src="figures/architecture.png"><br/>Figure 1. RPC Architecture</p>

## History
See [History and origins on Wikipedia](https://en.wikipedia.org/wiki/Remote_procedure_call#History_and_origins)

## Key Concepts
### Service API
This is the contract between both parties: service provider and service consumer. It defines the prototypes of the methods/functions that are offered by the service provider and that can be invoked by the service consumer

### Service provider
This is the party that provides the business implementation of the service API. It publishes such an implementation under a well known location and responds to consumer invocations. Key concepts related to the service provider are:
- **Server stub or skeleton:** uses/wrapps the service business implementaion, while providing parameter unmarshalling and result marshalling.
- **Parameter unmarshalling:** unpacking and deserializing the parameters from the stream of bytes sent by the client stub into their in-memory representation.
- **Result marshalling:** serializing the result from its in-memory representation into a *structured* stream of bytes that is suitable for transport and from which the result can be rebuilt (unmarshalled) by the client stub on the other side.

### Service consumer
This is the party that consumes the service offered by the service provider. It needs to know nothing about the service implementation (one of the main purposes of RPC). Key conceprs related to the service consumer are:
- **Client stub or proxy:** implements the service API, but this is just a proxy/fake implementation, which gives the impression to the service consumer that it's invoking the remote business implementation as if it were local. It provides parameter marshalling and result unmarshalling.
- **Parameter marshalling:** serializing and packing parameters from their in-memory representation into a *structured* stream of bytes that is suitable for transport and from which parameters can be rebuilt (unmarshalled) by the server stub on the other side.
- **Result unmarshalling:** unpacking and deserializing the result from the stream of bytes sent ny the server stub into its in-memory representation.

## Runtime Flow
<p align="center"><img src="figures/runtime-flow.png"><br/>Figure 2. RPC Runtime Flow</p>

## Development Process
There are two main approaches: API-first and Code-first. Regardless of the approach, the server skeleton and the client stub are generated automatically from the service API/contract using the appropriate tool provided by the chosen technology.
### API-first Approach
As its name implies, this approach consits of designing the service API/contract first. Then, server-side code, as well as client-side code are created. From a design perspective, it's always a good practice to specify the API/contract before delving into the implementation.
<p align="center"><img src="figures/dev-process-api-first.png"><br/>Figure 3. API-first Approach</p>
However, it may not be an easy task to specify the contract in some interface definition languages, such as WSDL or RAML. In this case, some developers would prefer the code-first approach.

### Code-first Approach
In this approach, developers start by coding the service business implementation, or at least defining its business interface in a target programming language, such as Java, Python, JavaScript, etc. Then, they use an appropriate tool for the chosen technology to generate the service API. This won't be possible if such a tool doesn't exist for the chosen technology and target programming language.
<p align="center"><img src="figures/dev-process-code-first.png"><br/>Figure 4. Code-first Approach</p>

## Technologies
### Programming Language-Specific Technologies
In the traditional client/server model, the programming language of the client and the one of the server are not relevant to the interaction. What really matters is the protocol and how the exchanged messages are compliant with the protocol.

However, the luxury and abstraction provided by RPC has a cost. Invoking methods/functions remotely while passing parameters and getting results would require (a priori) that both the service provider and consumer be implemented in the same programming language. Indeed, function prototypes, as well as parameters and returned results types depend on the programming language.

Therfore, the first attempts to provide RPC implementations were language-specific. This is [a list on Wikipedia of such attempts](https://en.wikipedia.org/wiki/Remote_procedure_call#Language-specific).

### Programming Language-Agnostic Technologies
Humans always want more, right? Software developers wanted to overcome the language dependency constraint. The key was creating a service (API) definition language that positions itself at the same distance from all programming languages, with the possibility to serialize objects into a unified format and deserialize them back, no matter what their original and final language-dependent representations are.

#### CORBA (legacy) - Common Object Request Broker Architecture
- Protocol: IIOP - Internet Inter-ORB Protocol
- Service definition language: IDL - Interface Definition Language

#### XML/SOAP (XS) Web Services
- Protocol: [SOAP - Simple Object Access Protocol](https://www.w3.org/TR/soap/) by W3C - In practice, over HTTP, but using POST method only. Consider these Sample SOAP request / reply, as a result of calling `computeAll(7.0, 5.0)`:

  - `SOAP request over HTTP POST request`
    ```
    POST http://localhost:9000/calculator HTTP/1.1
    Accept-Encoding: gzip,deflate
    Content-Type: text/xml;charset=UTF-8
    SOAPAction: ""
    Content-Length: 321
    Host: localhost:9000
    Connection: Keep-Alive
    User-Agent: Apache-HttpClient/4.5.5

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:prov="http://provider.calculator.xs.integration.paradigms.sse.aui.ma/">
      <soapenv:Header/>
      <soapenv:Body>
          <prov:computeAll>
            <arg0>7.0</arg0>
            <arg1>5.0</arg1>
          </prov:computeAll>
      </soapenv:Body>
    </soapenv:Envelope>
    ```
  - `SOAP reply over HTTP reply`
    ```
    HTTP/1.1 200 OK
    Transfer-encoding: chunked
    Content-type: text/xml; charset=utf-8

    <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
      <S:Body>
          <ns0:computeAllResponse xmlns:ns0="http://provider.calculator.xs.integration.paradigms.sse.aui.ma/">
            <return>
                <sum>12.0</sum>
                <difference>2.0</difference>
                <product>35.0</product>
                <ratio>1.4</ratio>
            </return>
          </ns0:computeAllResponse>
      </S:Body>
    </S:Envelope>
    ```
- Service definition language: [WSDL - Web Service Definition Language](https://www.w3.org/TR/2001/NOTE-wsdl-20010315) (XML-based), by W3C
- [Case Study: XML/SOAP Calculator Web Service](https://github.com/oiraqi/paradigms/tree/main/P2-Integration/case-studies/xs-calculator)

#### RESTful (RS) Web Services
REST (REpresentational State Transfer) is an *architectural style* with the following architectural properties / non-functional requirements:
- Performance
- Scalability
- Simplicity
- Modifiability
- Portability
- Reliability

To fulfill these requirements, REST defines a set of constraints / design principles:
- Client/Server architecture
- Statelessness
- Cacheability
- Self-descriptive messages
- HATEOAS: Hypermedia As The Engine Of Application State

Web services that follow the REST architectural style are called RESTful web services. *It is worth mentioning that REST style diverges from RPC style.* While in RPC function calls are statically built into the consumer code, RESTful clients should be able to discover and call new functionality at runtime, through hyperlinks returned as part of responses (HATEOAS principle).

There are strong ties between REST and HTTP. As such, RESTful web services use the HTTP protocol, in particular: GET, POST, PATCH, PUT and DELETE methods:
- GET: To retrieve resources from server side, specifying some input (criteria) in *the request URI* - should have no side effect on server side (safe method)
  - e.g., GET `http://www.example.com/courses?category=cs`
- POST: To create a resource on server side, specifying its data in *the request body*
- PUT: To fully update a resource on server side, specifying its id in *the request URI* and new version in *the request body*
- PATCH: To partially update a resource on server side, specifying its id in *the request URI* and the attributes to be updated in *the request body*
- DELETE: To delete a resource on server side, specifying its id in *the request URI*

These methods provide a natural mapping to CRUD operations. So, RESTful web services are naturally suitable for data-oriented use cases, but can also be adapted for service-oriented use cases.

As opposed to XML/SOAP, RESTful web services don't specify a service definition language. However, some popular languages have emerged as de-facto standards, such as [RAML](https://raml.org/) (YAML-based) and [OAS - OpenAPI Specification](https://www.openapis.org/).

- [Case Study (Data-oriented / CRUD): RESTful XCommerce Web Service](https://github.com/oiraqi/paradigms/tree/main/P2-Integration/case-studies/rs-xcommerce)

#### Other Web Services (Non XML/SOAP, RESTless)
People have tendency to call any web service that is not based on XML/SOAP, a RESTful web service. This isn't correct. As we saw earlier, REST defines strict constraints. If a web service misses just one of them, then you can call it whatever you want, but RESTful.

This case study is about a calculator web service based on OpenAPI. Yet, it does not fulfill all REST constraints, especially HATEOAS. So, we shouldn't call it a RESTful web service.
- [Case Study (Service-oriented): OpenAPI Calculator Web Service](https://github.com/oiraqi/paradigms/tree/main/P2-Integration/case-studies/ws-calculator)