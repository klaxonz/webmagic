package us.codecraft.webmagic.selector;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Selector in regex.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class RegexSelector implements Selector {

    private String regexStr;

    private Pattern regex;

    public RegexSelector(String regexStr) {
        this.compileRegex(regexStr);
    }

    private void compileRegex(String regexStr) {
        if (StringUtils.isBlank(regexStr)) {
            throw new IllegalArgumentException("regex must not be empty");
        }
        try {
            this.regex = Pattern.compile(regexStr, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
            this.regexStr = regexStr;
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("invalid regex "+regexStr, e);
        }
    }

    @Override
    public String select(String text) {
        return selectGroup(text).getGroup();
    }

    @Override
    public List<String> selectList(String text) {
        List<String> strings = new ArrayList<String>();
        List<RegexResult> results = selectGroupList(text);
        for (RegexResult result : results) {
            strings.add(result.getGroup());
        }
        return strings;
    }

    public RegexResult selectGroup(String text) {
        Matcher matcher = regex.matcher(text);
        if (matcher.find()) {
            return new RegexResult(matcher.group());
        }
        return RegexResult.EMPTY_RESULT;
    }

    public List<RegexResult> selectGroupList(String text) {
        Matcher matcher = regex.matcher(text);
        List<RegexResult> resultList = new ArrayList<RegexResult>();
        while (matcher.find()) {
            resultList.add(new RegexResult(matcher.group()));
        }
        return resultList;
    }

    @Override
    public String toString() {
        return regexStr;
    }

}
