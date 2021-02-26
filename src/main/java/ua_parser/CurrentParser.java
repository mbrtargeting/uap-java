package ua_parser;

import java.io.IOException;
import java.io.InputStream;

public class CurrentParser extends Parser {
    private static final String REGEX_YAML_PATH = "/ua_parser/current/regexes.yaml";

    public CurrentParser() {
        this(CurrentParser.class.getResourceAsStream(REGEX_YAML_PATH));
    }

    public CurrentParser(InputStream regexYaml) {
        super(regexYaml, false);
    }
}
