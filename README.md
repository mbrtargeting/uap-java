ua_parser Java Library
======================

This is the Java implementation of [ua-parser](https://github.com/ua-parser).
The implementation uses the shared regex patterns and overrides from [regexes.yaml](https://github.com/ua-parser/uap-core/blob/master/regexes.yaml).

Build:
------

```
#first time:
#git submodule update --init --recursive
#then:
git submodule update --recursive
mvn package
```

Usage:
--------

There are two Parser classes: `CurrentParser` and `LegacyParser` with identical interfaces.
`CurrentParser` uses a slightly newer version of `uap-core` with an updated user agent parser.
That version of `uap-core` is synced with the one `popper` uses as a part of `uap-python`.
`LegacyParser` uses a version of `uap-core` from early 2018. It's the version which was used
when the frozen DMP realtime models have been created.

Use them as following:

```java
import ua_parser.Parser;
import ua_parser.Client;

class SomeClass {

    void SomeMethod() {
        String uaString = 
                "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3";

        Parser uaParser = new CurrentParser();
        Client c = uaParser.parse(uaString);

        System.out.println(c.userAgent.family); // => "Mobile Safari"
        System.out.println(c.userAgent.major);  // => "5"
        System.out.println(c.userAgent.minor);  // => "1"

        System.out.println(c.os.family);        // => "iOS"
        System.out.println(c.os.major);         // => "5"
        System.out.println(c.os.minor);         // => "1"

        System.out.println(c.device.family);    // => "iPhone"
    }
}
```

Original Author:
-------

* Steve Jiang [@sjiang](https://twitter.com/sjiang)

Based on the python implementation by Lindsey Simon and using agent data from BrowserScope
