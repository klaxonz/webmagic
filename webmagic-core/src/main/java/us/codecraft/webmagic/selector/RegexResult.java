package us.codecraft.webmagic.selector;

/**
 * Object contains regex results.<br>
 * For multi group result extension.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
class RegexResult {

    private String group;

    public static final RegexResult EMPTY_RESULT = new RegexResult();

    public RegexResult() {

    }

    public RegexResult(String group) {
        this.group = group;
    }

    public String getGroup() {
        return this.group;
    }

}
