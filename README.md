ua_parser Java Library
======================

This is the Java implementation of [ua-parser](https://github.com/ua-parser).
The implementation uses the shared regex patterns and overrides from [regexes.yaml](https://github.com/ua-parser/uap-core/blob/master/regexes.yaml).

Update uap-core Version:
------
Clone the repository
```
git clone git@github.com:mbrtargeting/uap-java.git
```

Download submodule and set it to commit we want
```
git submodule update --init --recursive
cd uap-core
git checkout ${HASH of desired version commit}
```

Commit the changed version
```
cd ..
git add uap-core
git commit -m 'moved core to desired version'
```

Rebase top-level repository
```
git remote add upstream https://github.com/ua-parser/uap-java.git
git fetch upstream
git checkout ${YOUR BRANCH}
git rebase upstream/master
```


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
```java
import ua_parser.Parser;
import ua_parser.Client;

...

String uaString = "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3";

Parser uaParser = new Parser();
Client c = uaParser.parse(uaString);

System.out.println(c.userAgent.family); // => "Mobile Safari"
System.out.println(c.userAgent.major);  // => "5"
System.out.println(c.userAgent.minor);  // => "1"

System.out.println(c.os.family);        // => "iOS"
System.out.println(c.os.major);         // => "5"
System.out.println(c.os.minor);         // => "1"

System.out.println(c.device.family);    // => "iPhone"
```

### Instructions for Build/Deploy/Releas


Author:
-------

* Steve Jiang [@sjiang](https://twitter.com/sjiang)

Based on the python implementation by Lindsey Simon and using agent data from BrowserScope
