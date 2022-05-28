package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * regex ipv4("^(?:(?:25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)($|(?!\.$)\.)){4}$");
 * regex ipv6("^(?:(?:[\\da-fA-F]{1,4})($|(?!:$):)){8}$");
 *
 * @author violet.
 */
public class Day20220529 {
    public String validIPAddress(String ip) {
        if (ip.contains(".")) {
            String regexIpv4 = "^(?:(?:25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)($|(?!\\.$)\\.)){4}$";
            if (ip.matches(regexIpv4)) {
                return "IPv4";
            }
            // no regex valid
            return validIpv4(ip);
        }
        if (ip.contains(":")) {
            String regexIpv6 = "^(?:(?:[\\da-fA-F]{1,4})($|(?!:$):)){8}$";
            if (ip.matches(regexIpv6)) {
                return "IPv6";
            }
            // no regex valid
            return validIpv6(ip);
        }
        return "Neither";
    }

    private String validIpv4(String ip) {
        if (ip.startsWith(".") || ip.endsWith(".")) {
            return "Neither";
        }
        String[] ips = ip.split("\\.");
        if (ips.length != 4) {
            return "Neither";
        }
        for (String ch : ips) {
            if (ch.startsWith("0") || ch.length() == 0 || ch.length() > 3) {
                return "Neither";
            }
            for (int i = 0; i < ch.length(); i++) {
                char c = ch.charAt(i);
                if (!Character.isDigit(c)) {
                    return "Neither";
                }
            }
            if (Integer.parseInt(ch) > 255) {
                return "Neither";
            }
        }
        return "IPv4";
    }

    private String validIpv6(String ip) {
        if (ip.startsWith(":") || ip.endsWith(":")) {
            return "Neither";
        }
        String[] ips = ip.split(":");
        if (ips.length != 8) {
            return "Neither";
        }
        for (String ch : ips) {
            if (ch.length() == 0 || ch.length() > 4) {
                return "Neither";
            }
            for (int i = 0; i < ch.length(); i++) {
                if (!Character.isDigit(ch.charAt(i)) && !(ch.charAt(i) >= 'a' && ch.charAt(i) <= 'f')
                        && !(ch.charAt(i) >= 'A' && ch.charAt(i) <= 'F')) {
                    return "Neither";
                }
            }
        }
        return "IPv6";
    }

    @Test
    public void test() {
        String ch = new Day20220529().validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:");
        Assert.assertEquals("Neither", ch);
        Assert.assertEquals("IPv4", new Day20220529().validIPAddress("192.0.0.1"));
    }
}
