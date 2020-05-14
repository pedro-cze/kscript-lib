# kscript-utils

## About
This lib provides basic functions for building kotlin scripts using kscript tool.
These are:
* <b>http.kt</b> - functions for handling http requests (request, header, get, post, put, patch...)
* <b>db.kt</b> - helper functions for db connection establishment and generic select query
* <b>auth.kt</b> - core authentication request and api key parsing
* <b>log.kt</b> - basic log functions (info, error, success)    

## Usage

#### Gradle

<!--- Auto-generated block -->
[//]: # (doc.usage.gradle.dependency)

> This library is not released yet. <br/>
> Use current *SNAPSHOT* version.

[//]: # (/doc.usage.gradle.dependency)

#### Maven

<!--- Auto-generated block -->
[//]: # (doc.usage.maven.dependency)

> This library is not released yet. <br/>
> Use current *SNAPSHOT* version.

[//]: # (/doc.usage.maven.dependency)

### Example: authentication + get request 
```kotlin
#!/usr/bin/env kscript
@file:CompilerOpts("-jvm-target 1.8")
@file:DependsOn("cz.my2n.libs:kscript-lib:$libVersion")
try {
    val credentials = Credentials("superAdmin@2n.cz", "secretPassword")
    val baseUrl = "https://my2n-testprod.dev.2n.cz/middleware/api/partner/v1"
    val apiKey: ApiKey  = authenticate(baseUrl, credentials)
    
    val accessToken = header("x-api-key")(apiKey.accessToken)
    val devices = parseResponse(call(get(baseUrl, "/devices", accessToken)))
} catch(e: Exception) {
    error(e.message)
}
info("Done.")
```

### Example: db connection + select
```kotlin
#!/usr/bin/env kscript
@file:CompilerOpts("-jvm-target 1.8")
@file:DependsOn("cz.my2n.libs:kscript-lib:$libVersion")
try {
    val connection: Connection? = createConnection(mvsConnString)
    connection?.let {
        val queryParams: Map<String, String> = mapOf("sipNumber", "123456")
        val rowToDevice: (Row) -> Device = { Device(it.id, it.sipNumber) }
        val devices: Set<Device> = select("select * from device where sip_number = :sipNumber", queryParams, connection, rowToDevice)
        // ...
    }
} catch(e: Exception) {
    error(e.message)
}
info("Done.")
```
