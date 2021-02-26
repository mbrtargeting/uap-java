package ua_parser;

import java.io.IOException;
import java.io.InputStream;

public class LegacyParser extends Parser {
    private static final String REGEX_YAML_PATH = "/ua_parser/legacy/regexes.yaml";

    public LegacyParser() {
        this(LegacyParser.class.getResourceAsStream(REGEX_YAML_PATH));
    }

    public LegacyParser(InputStream regexYaml) {
        super(regexYaml, true);
    }
}
