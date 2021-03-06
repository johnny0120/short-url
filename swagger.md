---
title: Url shortener services v0.0.1-SNAPSHOT
language_tabs:
    - shell: Shell
    - http: HTTP
    - javascript: JavaScript
    - ruby: Ruby
    - python: Python
    - php: PHP
    - java: Java
    - go: Go
toc_footers: []
includes: []
search: true
highlight_theme: darkula
headingLevel: 2
---

<!-- Generator: Widdershins v4.0.1 -->

<h1 id="url-shortener-services">Url shortener services v0.0.1-SNAPSHOT</h1>

> Scroll down for code samples, example requests and responses. Select a language for code samples from the tabs above or the mobile navigation menu.

Provide simple web service to convert between short text and full url

Base URLs:

-   <a href="http://localhost:8080">http://localhost:8080</a>

<h1 id="url-shortener-services-shorten-url">Shorten Url</h1>

## shortenUrl

<a id="opIdshortenUrl"></a>

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/ \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json'

```

```http
POST http://localhost:8080/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "url": "string",
  "secret": "string"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json'
};

fetch('http://localhost:8080/',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json'
}

result = RestClient.post 'http://localhost:8080/',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json'
}

r = requests.post('http://localhost:8080/', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','http://localhost:8080/', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "http://localhost:8080/", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /`

_Create code for full url_

> Body parameter

```json
{
    "url": "string",
    "secret": "string"
}
```

<h3 id="shortenurl-parameters">Parameters</h3>

| Name | In   | Type                                    | Required | Description                              |
| ---- | ---- | --------------------------------------- | -------- | ---------------------------------------- |
| body | body | [UrlShortenSpec](#schemaurlshortenspec) | true     | Specification for creating shortened url |

> Example responses

> 200 Response

```json
{
    "url": "string",
    "secret": "string",
    "code": "string"
}
```

<h3 id="shortenurl-responses">Responses</h3>

| Status | Meaning                                                          | Description                                 | Schema                                      |
| ------ | ---------------------------------------------------------------- | ------------------------------------------- | ------------------------------------------- |
| 200    | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | The record has been created                 | [UrlShortenResult](#schemaurlshortenresult) |
| 400    | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | The url is invalid                          | None                                        |
| 409    | [Conflict](https://tools.ietf.org/html/rfc7231#section-6.5.8)    | The potential code has been taken by others | None                                        |

<aside class="success">
This operation does not require authentication
</aside>

## getUrl

<a id="opIdgetUrl"></a>

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/{code}

```

```http
GET http://localhost:8080/{code} HTTP/1.1
Host: localhost:8080

```

```javascript
fetch("http://localhost:8080/{code}", {
    method: "GET",
})
    .then(function (res) {
        return res.json();
    })
    .then(function (body) {
        console.log(body);
    });
```

```ruby
require 'rest-client'
require 'json'

result = RestClient.get 'http://localhost:8080/{code}',
  params: {
  }

p JSON.parse(result)

```

```python
import requests

r = requests.get('http://localhost:8080/{code}')

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','http://localhost:8080/{code}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/{code}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "http://localhost:8080/{code}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /{code}`

_Get full url by code_

<h3 id="geturl-parameters">Parameters</h3>

| Name | In   | Type   | Required | Description               |
| ---- | ---- | ------ | -------- | ------------------------- |
| code | path | string | true     | The code of shortened url |

<h3 id="geturl-responses">Responses</h3>

| Status | Meaning                                                          | Description                                     | Schema |
| ------ | ---------------------------------------------------------------- | ----------------------------------------------- | ------ |
| 302    | [Found](https://tools.ietf.org/html/rfc7231#section-6.4.3)       | The record is found with a redirection response | None   |
| 400    | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | The code is invalid                             | None   |
| 404    | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | The code is not found                           | None   |

<aside class="success">
This operation does not require authentication
</aside>

## deleteUrl

<a id="opIddeleteUrl"></a>

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/{code}?secret=string

```

```http
DELETE http://localhost:8080/{code}?secret=string HTTP/1.1
Host: localhost:8080

```

```javascript
fetch("http://localhost:8080/{code}?secret=string", {
    method: "DELETE",
})
    .then(function (res) {
        return res.json();
    })
    .then(function (body) {
        console.log(body);
    });
```

```ruby
require 'rest-client'
require 'json'

result = RestClient.delete 'http://localhost:8080/{code}',
  params: {
  'secret' => 'string'
}

p JSON.parse(result)

```

```python
import requests

r = requests.delete('http://localhost:8080/{code}', params={
  'secret': 'string'
})

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','http://localhost:8080/{code}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("http://localhost:8080/{code}?secret=string");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "http://localhost:8080/{code}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /{code}`

_Delete code by secret_

<h3 id="deleteurl-parameters">Parameters</h3>

| Name   | In    | Type   | Required | Description                     |
| ------ | ----- | ------ | -------- | ------------------------------- |
| code   | path  | string | true     | The code of shortened url       |
| secret | query | string | true     | The secret for delete operation |

<h3 id="deleteurl-responses">Responses</h3>

| Status | Meaning                                                          | Description                            | Schema |
| ------ | ---------------------------------------------------------------- | -------------------------------------- | ------ |
| 204    | [No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)  | The code is cleared                    | None   |
| 400    | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | The code is invalid                    | None   |
| 401    | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | The secret is not allowed for deletion | None   |

<aside class="success">
This operation does not require authentication
</aside>

# Schemas

<h2 id="tocS_UrlShortenSpec">UrlShortenSpec</h2>
<!-- backwards compatibility -->
<a id="schemaurlshortenspec"></a>
<a id="schema_UrlShortenSpec"></a>
<a id="tocSurlshortenspec"></a>
<a id="tocsurlshortenspec"></a>

```json
{
    "url": "string",
    "secret": "string"
}
```

### Properties

| Name   | Type   | Required | Restrictions | Description                                     |
| ------ | ------ | -------- | ------------ | ----------------------------------------------- |
| url    | string | true     | none         | Full url from the request                       |
| secret | string | false    | none         | Secret for delete operation, generated if empty |

<h2 id="tocS_UrlShortenResult">UrlShortenResult</h2>
<!-- backwards compatibility -->
<a id="schemaurlshortenresult"></a>
<a id="schema_UrlShortenResult"></a>
<a id="tocSurlshortenresult"></a>
<a id="tocsurlshortenresult"></a>

```json
{
    "url": "string",
    "secret": "string",
    "code": "string"
}
```

### Properties

allOf

| Name        | Type                                    | Required | Restrictions | Description |
| ----------- | --------------------------------------- | -------- | ------------ | ----------- |
| _anonymous_ | [UrlShortenSpec](#schemaurlshortenspec) | false    | none         | none        |

and

| Name        | Type   | Required | Restrictions | Description                 |
| ----------- | ------ | -------- | ------------ | --------------------------- |
| _anonymous_ | object | false    | none         | none                        |
| » code      | string | true     | none         | Result code of the full url |
